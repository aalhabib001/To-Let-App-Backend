package com.toletproject.ToLetProject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class AdvertiseModel {
    @Id
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<PhotoLink> photoLinksCollection;
}
