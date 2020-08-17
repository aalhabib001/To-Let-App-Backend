package com.toletproject.ToLetProject.jwt.controller;


import com.toletproject.ToLetProject.jwt.dto.request.*;
import com.toletproject.ToLetProject.jwt.services.ForgetPasswordService;
import com.toletproject.ToLetProject.jwt.services.SignUpAndSignInService;
import com.toletproject.ToLetProject.model.DistrictsModel;
import com.toletproject.ToLetProject.model.DivisionsModel;
import com.toletproject.ToLetProject.model.UpzilaModels;
import com.toletproject.ToLetProject.service.UserService;
import javassist.bytecode.DuplicateMemberException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private SignUpAndSignInService signUpAndSignInService;
    private final ForgetPasswordService forgetPasswordService;
    private final UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        return ResponseEntity.ok(signUpAndSignInService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public Object registerUser(@RequestBody SignUpForm signUpRequest) throws DuplicateMemberException {
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


    @DeleteMapping("/user/delete/")
    public String deleteUser(@RequestParam String email) {
        return signUpAndSignInService.deleteUser(email);
    }

    @PutMapping("/user/edit")
    public String editUserDetails(@RequestBody EditProfile editProfile) {
        return signUpAndSignInService.editProfile(editProfile);

    }

//    @PostMapping("/areas")
//    public String setAreaNames(@RequestBody AreaNameRequestsResponse areaNameRequestsResponse) {
//        return signUpAndSignInService.addAreaList(areaNameRequestsResponse);
//    }
//
//    @GetMapping("/areas")
//    public AreaNameRequestsResponse getAreaNames() {
//        return signUpAndSignInService.getAreaList();
//    }

    @GetMapping("/serverCheck")
    public String getServerStatStatus() {
        return "The Server is Running";
    }

//    TempService tempService;

    @GetMapping("/test")
    public void test() {
        userService.printAllDistrict();
    }

    @GetMapping("/divisions")
    public List<DivisionsModel> getDivisions() {
        return userService.getAllDivision();
    }

    @GetMapping("/districts")
    public List<DistrictsModel> getDistricts(@RequestParam Integer id) {
        return userService.getDistrictsById(id);
    }

    @GetMapping("/upzilas")
    public List<UpzilaModels> getUpzilas(@RequestParam Integer id) {
        return userService.getUpzilaaById(id);
    }

//    @PostMapping("/tempSave")
//    public String tempSave(@RequestBody TempDTOList tempDTOList) {
//        return tempService.save(tempDTOList);
//    }

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
