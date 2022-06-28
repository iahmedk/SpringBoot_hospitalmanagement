package com.ty.springboothospitalmgmt.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Department;
import com.ty.springboothospitalmgmt.repository.DepartmentRepository;

@Repository
public class DepartmentDao {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public void removeDepartmentById(int id) {
		departmentRepository.deleteById(id);
	}

	public Department getDepartmentById(int id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if (!opt.isEmpty()) {
			return opt.get();
		} else {
			return null;
		}
	}
}
