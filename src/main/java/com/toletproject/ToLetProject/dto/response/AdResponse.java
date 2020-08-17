package com.toletproject.ToLetProject.dto.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class AdResponse {
    String adId;

    String ownerPhone;

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

    String ownerName;

    List<PhotoLinkDTO> photoLinkDTOS;
}
