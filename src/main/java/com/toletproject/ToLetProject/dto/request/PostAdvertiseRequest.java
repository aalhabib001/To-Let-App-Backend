package com.toletproject.ToLetProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostAdvertiseRequest {

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

}
