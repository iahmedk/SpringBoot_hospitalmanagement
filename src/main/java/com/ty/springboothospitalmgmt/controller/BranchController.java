package com.ty.springboothospitalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboothospitalmgmt.dto.Branch;
import com.ty.springboothospitalmgmt.dto.Department;
import com.ty.springboothospitalmgmt.dto.Doctor;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.service.BranchService;
import com.ty.springboothospitalmgmt.service.DepartmentService;
import com.ty.springboothospitalmgmt.service.DoctorService;

@RestController
public class BranchController {

	@Autowired
	private BranchService branchService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/branches")
	public ResponseStructure<Branch> saveBranch(@RequestBody Branch branch) {
		return branchService.saveBranch(branch);
	}

	@GetMapping("/branches")
	public ResponseStructure<List<Branch>> getAllBranch() {
		return branchService.getAllBranch();
	}

	@GetMapping("/branches/{id}")
	public ResponseStructure<Branch> getBranchById(@PathVariable int id) {
		return branchService.getBranchById(id);
	}

	@DeleteMapping("/branches/{id}")
	public ResponseStructure<String> removeBranchById(@PathVariable int id) {
		return branchService.removeBranchById(id);
	}

	@GetMapping("branches/state/{name}")
	public ResponseStructure<List<Branch>> getBranchByState(@PathVariable String name) {
		return branchService.getBranchByState(name);
	}

	// To add dept to hospital
	@PostMapping("/branches/department/{id}")
	public ResponseStructure<Department> saveDepartment(@RequestBody Department department, @PathVariable int id) {
		return departmentService.saveDepartment(department, id);
	}

	// To remove dept from branch with help of branch pin
	@DeleteMapping("/branches/department")
	public ResponseStructure<String> removeDepartmentByName(@RequestParam long branchPin,
			@RequestParam String deptName) {
		return departmentService.removeDepartmentByName(branchPin, deptName);
	}

	// To add doctor to hospital
	@PostMapping("/branches/doctor/{id}")
	public ResponseStructure<Doctor> saveDoctor(@RequestBody Doctor doctor, @PathVariable int id) {
		return doctorService.saveDoctor(doctor, id);
	}

	@DeleteMapping("/branches/doctor")
	public ResponseStructure<String> removeDoctorById(@RequestParam long branchPin, @RequestParam int id) {
		return doctorService.removeDoctorById(branchPin, id);
	}
}
