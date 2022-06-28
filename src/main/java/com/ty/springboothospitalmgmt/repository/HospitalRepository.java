package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
	
}
