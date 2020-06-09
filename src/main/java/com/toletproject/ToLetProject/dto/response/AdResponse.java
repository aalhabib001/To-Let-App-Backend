package com.toletproject.ToLetProject.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class AdResponse {
    String adId;

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
