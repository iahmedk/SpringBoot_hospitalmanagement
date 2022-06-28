package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.MedOrder;

public interface MedOrderRepository extends JpaRepository<MedOrder, Integer> {
	
}
