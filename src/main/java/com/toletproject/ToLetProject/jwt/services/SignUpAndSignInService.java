package com.toletproject.ToLetProject.jwt.services;

import com.toletproject.ToLetProject.jwt.dto.request.LoginForm;
import com.toletproject.ToLetProject.jwt.dto.request.SignUpForm;
import com.toletproject.ToLetProject.jwt.dto.response.IdentityResponse;
import com.toletproject.ToLetProject.jwt.dto.response.JwtResponse;
import com.toletproject.ToLetProject.jwt.dto.response.LoggedUserDetailsResponse;
import com.toletproject.ToLetProject.jwt.dto.response.TestResponse;
import com.toletproject.ToLetProject.jwt.model.Role;
import com.toletproject.ToLetProject.jwt.model.RoleName;
import com.toletproject.ToLetProject.jwt.model.User;
import com.toletproject.ToLetProject.jwt.repository.RoleRepository;
import com.toletproject.ToLetProject.jwt.repository.UserRepository;
import com.toletproject.ToLetProject.jwt.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    public IdentityResponse signUp(SignUpForm signUpRequest) {


        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            //TODO
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            //TODO
        }

        User user = new User();
        UUID id = UUID.randomUUID();
        String uuid = id.toString();
        user.setId(uuid);
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNo(signUpRequest.getPhoneNo());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(getRolesOrThrow(signUpRequest.getRole()));
        userRepository.saveAndFlush(user);

        return new IdentityResponse(uuid);
    }


    public JwtResponse signIn(LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return new JwtResponse(jwt);
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
            return response.getName();

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
