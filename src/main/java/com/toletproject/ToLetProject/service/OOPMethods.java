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
                    .upzilaOrAreaName(advertiseModel.getUpzilaAreaName())
                    .districtName(advertiseModel.getDistrictName())
                    .divisionName(advertiseModel.getDivisionName())
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
                    .build();

            adRespons.add(adResponse);
        }
        return adRespons;
    }
}
