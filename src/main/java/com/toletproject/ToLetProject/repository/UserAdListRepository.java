package com.toletproject.ToLetProject.repository;

import com.toletproject.ToLetProject.model.AdvertiseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAdListRepository extends PagingAndSortingRepository<AdvertiseModel, String> {

    Page<AdvertiseModel> findAll(Pageable pageable);

    Page<AdvertiseModel> findAllByUpzilaAreaName(Pageable pageable, String areaName);

    Page<AdvertiseModel> findAllByBedAndUpzilaAreaName(Pageable pageable, int bed, String location);

    Page<AdvertiseModel> findAllByBed(Pageable pageable, int bed);

    Page<AdvertiseModel> findAllByBachelorAllowed(Pageable pageable, String bachelor);

    Page<AdvertiseModel> findAllByBachelorAllowedAndBed(Pageable pageable, String bachelor, int bed);

    Page<AdvertiseModel> findAllByBachelorAllowedAndUpzilaAreaName(Pageable pageable, String bachelor, String location);

    Page<AdvertiseModel> findAllByBachelorAllowedAndUpzilaAreaNameAndBed(Pageable pageable, String bachelor, String location, int bed);

    Optional<AdvertiseModel> findAllByUpzilaAreaName(String areaName);

}
