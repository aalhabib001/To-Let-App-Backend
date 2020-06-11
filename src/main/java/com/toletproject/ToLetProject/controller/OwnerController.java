package com.toletproject.ToLetProject.controller;

import com.toletproject.ToLetProject.dto.request.PostAdvertiseRequest;
import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/postAdd")
    public ResponseEntity<String> postAdvertise(@RequestBody PostAdvertiseRequest postAdvertiseRequest) {
        return ownerService.postAdvertise(postAdvertiseRequest);
    }

    @GetMapping("/allAds")
    public AdListResponse allAds() {
        return ownerService.allAds();
    }

    @PostMapping(value = "/image/{link}")
    public ResponseEntity<String> post(
            @RequestParam(value = "upload", required = true) MultipartFile[] aFile,
            @PathVariable String link
    ) {
        return ownerService.uploadImage(aFile, link);
    }

}
