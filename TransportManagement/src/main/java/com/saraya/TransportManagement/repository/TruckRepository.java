package com.saraya.TransportManagement.repository;

import com.saraya.TransportManagement.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Long> {

    List<Truck> findByBranchBranchId(Long branchId);
}
