package com.ty.springboothospitalmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboothospitalmgmt.dto.Hospital;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.service.HospitalService;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@PostMapping("/hospitals")
	public ResponseStructure<Hospital> saveHospital(@RequestBody Hospital hospital) {
		return hospitalService.saveHospital(hospital);
	}

	@GetMapping("/hospitals/{id}")
	public ResponseStructure<Hospital> getHospitalById(@PathVariable int id) {
		return hospitalService.getHospitalById(id);
	}

	@DeleteMapping("/hospitals/{id}")
	public ResponseStructure<String> removeHospitalById(@PathVariable int id) {
		return hospitalService.removeHospitalById(id);
	}
}
