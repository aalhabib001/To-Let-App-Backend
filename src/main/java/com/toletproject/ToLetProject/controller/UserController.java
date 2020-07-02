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

//    @GetMapping("/ads")
//    public AdListResponse getUserAdList(@RequestParam int pageNo, @RequestParam int pageSize) {
//        //System.out.println("HI");
//        return userService.getUserAdList(pageNo, pageSize);
//    }

    @GetMapping("/ads")
    public AdListResponse getAdsByLocation(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(value = "area", required = false, defaultValue = "") String location) {

        if (!location.isEmpty()) {
            return userService.getAdsByLocation(pageNo, pageSize, location);
        } else {
            return userService.getUserAdList(pageNo, pageSize);
        }

    }
//    @GetMapping("/ads")
//    public AdListResponse getAdsByLocation(@RequestParam int pageNo, @RequestParam int pageSize){
//
//    }

}
