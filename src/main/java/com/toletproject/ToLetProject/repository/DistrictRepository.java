package com.toletproject.ToLetProject.repository;

import com.toletproject.ToLetProject.model.DistrictsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictsModel, Integer> {

    @Query(value = "SELECT * FROM districts where division_id = :userId ORDER BY name ASC", nativeQuery = true)
    List<DistrictsModel> findAllByDivisionId(Integer userId);
}
