package com.toletproject.ToLetProject.controller;

import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAds/{pageNo}/{pageSize}")
    public AdListResponse getUserAdList(@PathVariable int pageNo, @PathVariable int pageSize){
        return userService.getUserAdList(pageNo,pageSize);
    }

}
