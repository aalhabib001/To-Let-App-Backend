package com.toletproject.ToLetProject.service;

import com.toletproject.ToLetProject.dto.request.PostAdvertiseRequest;
import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.dto.response.AdResponse;
import com.toletproject.ToLetProject.jwt.services.SignUpAndSignInService;
import com.toletproject.ToLetProject.model.AdvertiseModel;
import com.toletproject.ToLetProject.repository.AdRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OwnerService {
    private final AdRepository adRepository;
    private final SignUpAndSignInService signUpAndSignInService;

    public ResponseEntity<String> postAdvertise(PostAdvertiseRequest postAdvertiseRequest) {


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
        advertiseModel.setGenerator(postAdvertiseRequest.isGenerator());
        advertiseModel.setGas(postAdvertiseRequest.isGas());
        advertiseModel.setBachelorAllowed(postAdvertiseRequest.isBachelorAllowed());
        advertiseModel.setRentCost(postAdvertiseRequest.getRentCost());
        advertiseModel.setServiceCharge(postAdvertiseRequest.getServiceCharge());
        advertiseModel.setOwnerName(signUpAndSignInService.getLoggedAuthUser().getName());

        adRepository.save(advertiseModel);

        return new ResponseEntity<>("Data Saved Successfully & The Ad id is: "+id.toString(), HttpStatus.CREATED);
    }


    public AdListResponse allAds() {
        List<AdvertiseModel> advertiseModelList = adRepository.findAllByOwnerPhone(signUpAndSignInService.getLoggedAuthUser().getPhoneNo());

        AdListResponse adListResponse = new AdListResponse();

        adListResponse.setAdRespons(setResponseFromAll(advertiseModelList));

        return adListResponse;
    }

    List<AdResponse> setResponseFromAll(List<AdvertiseModel> advertiseModelList) {
        List<AdResponse> adRespons = new ArrayList<>();

        for(AdvertiseModel advertiseModel:advertiseModelList){
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
                    .generator(advertiseModel.isGenerator())
                    .gas(advertiseModel.isGas())
                    .bachelorAllowed(advertiseModel.isBachelorAllowed())
                    .rentCost(advertiseModel.getRentCost())
                    .serviceCharge(advertiseModel.getServiceCharge())
                    .ownerName(advertiseModel.getOwnerName())
                    .photoLinks(advertiseModel.getPhotoLinks())
                    .build();

            adRespons.add(adResponse);
        }
        return adRespons;
    }
}
