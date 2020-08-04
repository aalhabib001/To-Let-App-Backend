package com.toletproject.ToLetProject.repository;

import com.toletproject.ToLetProject.model.DivisionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DivisionRepository extends JpaRepository<DivisionsModel, Integer> {

    @Query(value = "SELECT * FROM divisions ORDER BY name ASC ", nativeQuery = true)
    List<DivisionsModel> findAllSorted();
}
