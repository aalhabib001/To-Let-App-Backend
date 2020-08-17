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

    String upzilaOrAreaName;

    String districtName;

    String divisionName;

    String propertyType;

    String view;

    int bed;

    int bath;

    int floorLevel;

    String generator;

    String gas;

    String bachelorAllowed;

    String lift;

    int rentCost;

}
