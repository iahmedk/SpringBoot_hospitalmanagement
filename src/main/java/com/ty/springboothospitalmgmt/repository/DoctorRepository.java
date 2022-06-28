package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
