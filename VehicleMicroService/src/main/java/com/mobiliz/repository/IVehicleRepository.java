package com.mobiliz.repository;

import com.mobiliz.repository.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleRepository extends JpaRepository<Vehicle,Long> {
}
