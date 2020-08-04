package com.toletproject.ToLetProject.repository;

import com.toletproject.ToLetProject.model.UpzilaModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpzilaRepository extends JpaRepository<UpzilaModels, Integer> {

    @Query(value = "SELECT * FROM upazilas where district_id = :distId ORDER BY name ASC ", nativeQuery = true)
    List<UpzilaModels> findAllByDistrictId(Integer distId);
}
