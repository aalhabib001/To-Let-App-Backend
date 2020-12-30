package com.toletproject.ToLetProject.controller;

import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/ads")
    public AdListResponse getAds(@RequestParam int pageNo, @RequestParam int pageSize,
                                 @RequestParam(value = "area", required = false) String location) {

        return userService.getAds(pageNo, pageSize, location);
    }



}
