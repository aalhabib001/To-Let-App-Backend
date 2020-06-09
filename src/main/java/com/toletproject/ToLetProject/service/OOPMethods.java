package com.toletproject.ToLetProject.service;

import com.toletproject.ToLetProject.dto.response.AdResponse;
import com.toletproject.ToLetProject.model.AdvertiseModel;

import java.util.ArrayList;
import java.util.List;

public class OOPMethods {


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
