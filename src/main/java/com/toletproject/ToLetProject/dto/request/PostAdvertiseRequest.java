package com.toletproject.ToLetProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
public class PostAdvertiseRequest {

    String ownerPhone;

    String areaName;

    String adTitle;

    double areaOfProperty;

    String location;

    String propertyType;

    String view;

    int bed;

    int bath;

    int floorLevel;

    boolean generator;

    boolean gas;

    boolean bachelorAllowed;

    int rentCost;

    int serviceCharge;

    String ownerName;

    String photoLinks;
}
