package com.toletproject.ToLetProject.jwt.controller;

import com.toletproject.ToLetProject.jwt.dto.request.*;
import com.toletproject.ToLetProject.jwt.dto.response.IdentityResponse;
import com.toletproject.ToLetProject.jwt.services.ForgetPasswordService;
import com.toletproject.ToLetProject.jwt.services.SignUpAndSignInService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private SignUpAndSignInService signUpAndSignInService;
    private final ForgetPasswordService forgetPasswordService;


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

    @PostMapping("/generateOTP")
    public String generateOTP(@RequestBody GenerateOTPRequest generateOTPRequest) throws IOException, MessagingException {
        return forgetPasswordService.generateOTP(generateOTPRequest);
    }

    @PostMapping("/verifyOTP")
    public String verifyOTP(@RequestBody GenerateOTPRequest1 generateOTPRequest) {
        return forgetPasswordService.verifyOTP(generateOTPRequest);
    }

    @PostMapping("/forgetPassChange")
    public String forgetPassChange(@RequestBody GenerateOTPRequest2 generateOTPRequest) throws IOException, MessagingException {
        return forgetPasswordService.forgetPassChange(generateOTPRequest);
    }

//    @GetMapping("/sendMail")
//    public String sendMail()throws MessagingException, IOException {
//        return forgetPasswordService.sendMail();
//    }

//    @GetMapping("/lUser")
//    public LoggedUserDetailsResponse getDashboard(Authentication authentication) {
//
//        return signUpAndSignInService.getLoggedUserDetails(authentication);
//    }
}