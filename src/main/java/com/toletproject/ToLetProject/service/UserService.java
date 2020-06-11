package com.toletproject.ToLetProject.service;

import com.toletproject.ToLetProject.dto.response.AdListResponse;
import com.toletproject.ToLetProject.dto.response.AdResponse;
import com.toletproject.ToLetProject.dto.response.PhotoLinkDTO;
import com.toletproject.ToLetProject.model.AdvertiseModel;
import com.toletproject.ToLetProject.model.PhotoLink;
import com.toletproject.ToLetProject.repository.AdRepository;
import com.toletproject.ToLetProject.repository.UserAdListRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Service
public class UserService {

    private final UserAdListRepository userAdListRepository;
    private final AdRepository adRepository;

    public AdListResponse getUserAdList(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<AdvertiseModel> advertiseModels = userAdListRepository.findAll(pageable);



        AdListResponse adListResponse = new AdListResponse();

        adListResponse.setAdRespons(setResponseFromAll(advertiseModels));

        return adListResponse;

    }

    List<AdResponse> setResponseFromAll(Page<AdvertiseModel> advertiseModelList) {

        List<AdResponse> adRespons = new ArrayList<>();

        for(AdvertiseModel advertiseModel:advertiseModelList) {
            List<PhotoLinkDTO> photoLinkDTOList = new ArrayList<>();

            for (PhotoLink photoLink : advertiseModel.getPhotoLinksCollection()) {
                PhotoLinkDTO photoLinkDTO = new PhotoLinkDTO();
                photoLink.setPhotoLink(photoLink.getPhotoLink());
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
                    .generator(advertiseModel.isGenerator())
                    .gas(advertiseModel.isGas())
                    .bachelorAllowed(advertiseModel.isBachelorAllowed())
                    .rentCost(advertiseModel.getRentCost())
                    .serviceCharge(advertiseModel.getServiceCharge())
                    .ownerName(advertiseModel.getOwnerName())
                    .photoLinkDTOS(photoLinkDTOList)
                    .build();

            adRespons.add(adResponse);
        }
        return adRespons;
    }

}
