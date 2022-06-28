package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
