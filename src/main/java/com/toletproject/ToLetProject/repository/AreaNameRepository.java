package com.toletproject.ToLetProject.repository;

import com.toletproject.ToLetProject.jwt.model.AreaNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaNameRepository extends JpaRepository<AreaNames, String> {
}
