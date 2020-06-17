package com.toletproject.ToLetProject.jwt.services;

import com.toletproject.ToLetProject.jwt.dto.AreaNameRequestsResponse;
import com.toletproject.ToLetProject.jwt.dto.request.EditProfile;
import com.toletproject.ToLetProject.jwt.dto.request.LoginForm;
import com.toletproject.ToLetProject.jwt.dto.request.SignUpForm;
import com.toletproject.ToLetProject.jwt.dto.response.JwtResponse;
import com.toletproject.ToLetProject.jwt.dto.response.TestResponse;
import com.toletproject.ToLetProject.jwt.model.AreaNames;
import com.toletproject.ToLetProject.jwt.model.Role;
import com.toletproject.ToLetProject.jwt.model.RoleName;
import com.toletproject.ToLetProject.jwt.model.User;
import com.toletproject.ToLetProject.jwt.repository.RoleRepository;
import com.toletproject.ToLetProject.jwt.repository.UserRepository;
import com.toletproject.ToLetProject.jwt.security.jwt.JwtProvider;
import com.toletproject.ToLetProject.repository.AreaNameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.*;

@AllArgsConstructor
@Service
public class SignUpAndSignInService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final AreaNameRepository areaNameRepository;

    public Object signUp(SignUpForm signUpRequest) {


        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return true;
        }


        User user = new User();
        UUID id = UUID.randomUUID();
        String uuid = id.toString();
        user.setId(uuid);
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getEmail() + signUpRequest.getPhoneNo());
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNo(signUpRequest.getPhoneNo());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(getRolesOrThrow(signUpRequest.getRole()));
        userRepository.saveAndFlush(user);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        signUpRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return new JwtResponse(jwt, signUpRequest.getRole());
    }


    public JwtResponse signIn(LoginForm loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        String userName;
        if (userOptional.isPresent()) {
            userName = userOptional.get().getUsername();
        } else {
            userName = "";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return new JwtResponse(jwt, getRolesToString(userOptional.get().getRoles()));
    }

    public TestResponse getLoggedAuthUser() {

        Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<String> loggedInAuthUserId = null;

        TestResponse response = new TestResponse();
        if (authUser instanceof UserDetails) {

            String username = ((UserDetails) authUser).getUsername();
            loggedInAuthUserId = userRepository.findAuthUsersById(username);
            response.setUsername(userRepository.findByUsername(username).get().getUsername());
            response.setEmail(userRepository.findByUsername(username).get().getEmail());
            response.setName(userRepository.findByUsername(username).get().getName());
            response.setPhoneNo(userRepository.findByUsername(username).get().getPhoneNo());
            response.setRole(getRolesToString(userRepository.findByUsername(username).get().getRoles()));
            System.out.println(username + " username");
            System.out.println(authUser);
            return response;

        } else if (authUser instanceof UserDetails == false) {
            System.out.println(response.getEmail()+ " Email");
            System.out.println(authUser);
            throw new RuntimeException("LoggedIn user does not  account.");

        } else {
            String username = authUser.toString();

            System.out.println(username);
            return null;
        }

    }

    public String getLoggedAuthUserName() {

        Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<String> loggedInAuthUserId = null;

        TestResponse response = new TestResponse();
        if (authUser instanceof UserDetails) {

            String username = ((UserDetails) authUser).getUsername();
            loggedInAuthUserId = userRepository.findAuthUsersById(username);
            response.setUsername(userRepository.findByUsername(username).get().getUsername());
            response.setName(userRepository.findByUsername(username).get().getName());
            return response.getUsername();

        } else if (authUser instanceof UserDetails == false) {
            throw new RuntimeException("LoggedIn user does not  account.");

        } else {
            String username = authUser.toString();

            System.out.println(username);
        }
        return loggedInAuthUserId.get();

    }

    public String getLoggedAuthUserId() {

        Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<String> loggedInAuthUserId = null;

        TestResponse response = new TestResponse();
        if (authUser instanceof UserDetails) {

            String username = ((UserDetails) authUser).getUsername();
            loggedInAuthUserId = userRepository.findAuthUsersById(username);
            response.setUsername(userRepository.findByUsername(username).get().getUsername());
            return response.getUsername();

        } else if (authUser instanceof UserDetails == false) {
            throw new RuntimeException("LoggedIn user does not  account.");

        } else {
            String username = authUser.toString();

            System.out.println(username);
        }
        return loggedInAuthUserId.get();

    }

    private Set<Role> getRolesOrThrow(Set<String> roles2) {
        Set<Role> roles = new HashSet<>();
        for (String role : roles2) {
            Optional<Role> roleOptional = roleRepository.findByName(RoleName.valueOf(role));
            System.out.println(roleOptional.get());
            if (!roleOptional.isPresent()) {
                throw new ValidationException("Role '" + role + "' does not exist.");
            }
            roles.add(roleOptional.get());
        }
        return roles;
    }

    private Set<String> getRolesToString(Set<Role> roles2) {
        Set<String> roles = new HashSet<>();
        for (Role role : roles2) {

            roles.add(role.getName().toString());
        }
        return roles;
    }

    public String deleteUser(String email) {

        if (userRepository.findByEmail(email).isPresent()) {

            userRepository.deleteById(userRepository.findByEmail(email).get().getId());
            return "Deleted";
        } else {
            return "Not Found";
        }

    }

    public String editProfile(EditProfile editProfile) {
        String username = getLoggedAuthUserName();

        if (!username.isEmpty()) {
            System.out.println(username);
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setName(editProfile.getName());
                user.setPhoneNo(editProfile.getPhoneNo());
                user.setPassword(encoder.encode(editProfile.getPassword()));
                userRepository.save(user);
                return "Saved Successfully";
            } else {
                return "User Not Found";
            }

        } else {
            return "Unsuccessful";
        }


    }

    public String addAreaList(AreaNameRequestsResponse areaNameRequestsResponse) {
        for (String names : areaNameRequestsResponse.getAreaNames()) {
            AreaNames areaNames = new AreaNames(names);
            areaNameRepository.save(areaNames);
        }
        return "Saved";
    }

    public AreaNameRequestsResponse getAreaList() {
        List<AreaNames> areaNamesOptional = areaNameRepository.findAll();

        AreaNameRequestsResponse areaNameRequestsResponse = new AreaNameRequestsResponse();
        List<String> areaNamesList = new ArrayList<>();
        for (AreaNames areaNames : areaNamesOptional) {
            areaNamesList.add(areaNames.getAreaName());
        }
        areaNameRequestsResponse.setAreaNames(areaNamesList);
        return areaNameRequestsResponse;
    }

//    public LoggedUserDetailsResponse getLoggedUserDetails(Authentication authentication) {
//
//        System.out.println(authentication.toString());
//        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
//        List<String> userRoleList = new ArrayList<>();
//        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//            userRoleList.add(grantedAuthority.getAuthority());
//        }
//        LoggedUserDetailsResponse loggedUserDetailsResponse = new LoggedUserDetailsResponse();
//        loggedUserDetailsResponse.setUserName(authentication.getName());
//        loggedUserDetailsResponse.setUserRole(userRoleList);
//        loggedUserDetailsResponse.setIsAuthenticated(authentication.isAuthenticated());
//        return loggedUserDetailsResponse;
//    }
}
