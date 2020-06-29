package com.toletproject.ToLetProject.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.toletproject.ToLetProject.dto.request.PostAdvertiseRequest;
import com.toletproject.ToLetProject.dto.response.AdIdResponse;
import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.dto.response.AdResponse;
import com.toletproject.ToLetProject.dto.response.PhotoLinkDTO;
import com.toletproject.ToLetProject.jwt.services.SignUpAndSignInService;
import com.toletproject.ToLetProject.model.AdvertiseModel;
import com.toletproject.ToLetProject.model.PhotoLink;
import com.toletproject.ToLetProject.repository.AdRepository;
import lombok.AllArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

@Service
@AllArgsConstructor
public class OwnerService {
    private final AdRepository adRepository;
    private final SignUpAndSignInService signUpAndSignInService;

    public ResponseEntity<AdIdResponse> postAdvertise(PostAdvertiseRequest postAdvertiseRequest) {


        AdvertiseModel advertiseModel = new AdvertiseModel();
        UUID id = UUID.randomUUID();
        advertiseModel.setAdId(id.toString());
        advertiseModel.setOwnerPhone(signUpAndSignInService.getLoggedAuthUser().getPhoneNo());
        advertiseModel.setAreaName(postAdvertiseRequest.getAreaName());
        advertiseModel.setAdTitle(postAdvertiseRequest.getAdTitle());
        advertiseModel.setAreaOfProperty(postAdvertiseRequest.getAreaOfProperty());
        advertiseModel.setLocation(postAdvertiseRequest.getLocation());
        advertiseModel.setPropertyType(postAdvertiseRequest.getPropertyType());
        advertiseModel.setView(postAdvertiseRequest.getView());
        advertiseModel.setBed(postAdvertiseRequest.getBed());
        advertiseModel.setFloorLevel(postAdvertiseRequest.getFloorLevel());
        advertiseModel.setBath(postAdvertiseRequest.getBath());
        advertiseModel.setGenerator(postAdvertiseRequest.getGenerator());
        advertiseModel.setGas(postAdvertiseRequest.getGas());
        advertiseModel.setBachelorAllowed(postAdvertiseRequest.getBachelorAllowed());
        advertiseModel.setRentCost(postAdvertiseRequest.getRentCost());
        advertiseModel.setLift(postAdvertiseRequest.getLift());
        advertiseModel.setOwnerName(signUpAndSignInService.getLoggedAuthUser().getName());

        AdIdResponse adIdResponse = new AdIdResponse(id.toString());
        adRepository.save(advertiseModel);

        return new ResponseEntity<>(adIdResponse, HttpStatus.CREATED);
    }


    public AdListResponse allAds() {
        List<AdvertiseModel> advertiseModelList = adRepository.findAllByOwnerPhone(signUpAndSignInService.getLoggedAuthUser().getPhoneNo());

        AdListResponse adListResponse = new AdListResponse();

        if (!advertiseModelList.isEmpty()) {
            adListResponse.setFound(true);
        } else {
            adListResponse.setFound(false);
        }
        adListResponse.setAdRespons(setResponseFromAll(advertiseModelList));

        return adListResponse;
    }

    List<AdResponse> setResponseFromAll(List<AdvertiseModel> advertiseModelList) {
        List<AdResponse> adRespons = new ArrayList<>();

        for(AdvertiseModel advertiseModel:advertiseModelList) {
            List<PhotoLinkDTO> photoLinkDTOList = new ArrayList<>();

            for (PhotoLink photoLink : advertiseModel.getPhotoLinksCollection()) {
                PhotoLinkDTO photoLinkDTO = new PhotoLinkDTO();
                photoLinkDTO.setPhotoLink(photoLink.getPhotoLink());
                photoLinkDTOList.add(photoLinkDTO);
            }

            AdResponse adResponse = AdResponse.builder()
                    .adId(advertiseModel.getAdId())
                    .ownerPhone(advertiseModel.getOwnerPhone())
                    .areaName(advertiseModel.getAreaName())
                    .adTitle(advertiseModel.getAdTitle())
                    .areaOfProperty(advertiseModel.getAreaOfProperty())
                    .location(advertiseModel.getLocation())
                    .propertyType(advertiseModel.getPropertyType())
                    .view(advertiseModel.getView())
                    .bed(advertiseModel.getBed())
                    .floorLevel(advertiseModel.getFloorLevel())
                    .bath(advertiseModel.getBath())
                    .generator(advertiseModel.getGenerator())
                    .gas(advertiseModel.getGas())
                    .bachelorAllowed(advertiseModel.getBachelorAllowed())
                    .rentCost(advertiseModel.getRentCost())
                    .lift(advertiseModel.getLift())
                    .ownerName(advertiseModel.getOwnerName())
                    .photoLinkDTOS(photoLinkDTOList)
                    .build();

            adRespons.add(adResponse);
        }
        return adRespons;
    }

    public ResponseEntity<String> uploadImage(MultipartFile[] aFile, String imageUploadRequest) {

        List<PhotoLink> photoLinksList = new LinkedList<>();

        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "to-let-app",
                "api_key", "111257839862595",
                "api_secret", "7H1QY2G1W6FVQQ3envantRuJz4c"));

        try {
            Optional<AdvertiseModel> advertiseModelOptional = adRepository.findById(imageUploadRequest);

            for (MultipartFile mpFile : aFile) {
                File f = Files.createTempFile("temp", mpFile.getOriginalFilename()).toFile();
                mpFile.transferTo(f);

                Map response = c.uploader().upload(f, ObjectUtils.emptyMap());
                JSONObject json = new JSONObject(response);
                String url = json.getString("url");

                PhotoLink photoLink = new PhotoLink();
                photoLink.setPhotoLink(url);

                photoLinksList.add(photoLink);
            }

            AdvertiseModel advertiseModel = advertiseModelOptional.get();

            advertiseModel.setPhotoLinksCollection(photoLinksList);
            adRepository.save(advertiseModel);

            return new ResponseEntity<String>("{\"status\":\"OK\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
