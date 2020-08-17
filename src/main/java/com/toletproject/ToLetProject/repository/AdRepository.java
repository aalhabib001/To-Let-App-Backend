package com.toletproject.ToLetProject.repository;

import com.toletproject.ToLetProject.model.AdvertiseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdvertiseModel,String> {
    List<AdvertiseModel> findAllByOwnerPhone(String phoneNo);

    List<AdvertiseModel> findAllByUpzilaAreaName(String area);
}
