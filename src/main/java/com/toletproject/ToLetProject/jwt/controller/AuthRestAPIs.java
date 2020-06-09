package com.toletproject.ToLetProject.jwt.controller;

import com.toletproject.ToLetProject.jwt.dto.request.LoginForm;
import com.toletproject.ToLetProject.jwt.dto.request.SignUpForm;
import com.toletproject.ToLetProject.jwt.dto.response.IdentityResponse;
import com.toletproject.ToLetProject.jwt.dto.response.LoggedUserDetailsResponse;
import com.toletproject.ToLetProject.jwt.services.SignUpAndSignInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    private SignUpAndSignInService signUpAndSignInService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        return ResponseEntity.ok(signUpAndSignInService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public IdentityResponse registerUser(@RequestBody SignUpForm signUpRequest) {
        return signUpAndSignInService.signUp(signUpRequest);
    }

    @GetMapping("/users")
    public Object getLoggedAuthId() {
        return signUpAndSignInService.getLoggedAuthUser();
    }

//    @GetMapping("/lUser")
//    public LoggedUserDetailsResponse getDashboard(Authentication authentication) {
//
//        return signUpAndSignInService.getLoggedUserDetails(authentication);
//    }
}