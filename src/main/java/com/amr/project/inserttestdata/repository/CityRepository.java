package com.amr.project.inserttestdata.repository;

import com.amr.project.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
    boolean existsByName(String name);
}
