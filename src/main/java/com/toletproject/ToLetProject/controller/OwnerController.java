package com.toletproject.ToLetProject.controller;

import com.toletproject.ToLetProject.dto.request.PostAdvertiseRequest;
import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/postAdd")
    public ResponseEntity<String> postAdvertise(@RequestBody PostAdvertiseRequest postAdvertiseRequest){
        return ownerService.postAdvertise(postAdvertiseRequest);
    }

    @GetMapping("/allAds")
    public AdListResponse allAds(){
        return ownerService.allAds();
    }
}
