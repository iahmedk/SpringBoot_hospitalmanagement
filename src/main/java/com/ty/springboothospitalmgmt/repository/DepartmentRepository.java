package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
