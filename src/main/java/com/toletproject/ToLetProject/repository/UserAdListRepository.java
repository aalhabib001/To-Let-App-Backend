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

    Optional<AdvertiseModel> findAllByUpzilaAreaName(String areaName);

}
