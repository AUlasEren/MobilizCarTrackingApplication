package com.mobiliz.repository;

import com.mobiliz.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegionRepository extends JpaRepository<Region,Long> {
}
