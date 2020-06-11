package com.toletproject.ToLetProject.controller;

import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAds")
    public AdListResponse getUserAdList(@RequestParam int pageNo, @RequestParam int pageSize) {
        System.out.println("HI");
        return userService.getUserAdList(pageNo, pageSize);
    }

}
