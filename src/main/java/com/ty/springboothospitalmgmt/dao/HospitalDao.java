package com.ty.springboothospitalmgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Hospital;
import com.ty.springboothospitalmgmt.repository.HospitalRepository;

@Repository
public class HospitalDao {
	@Autowired
	private HospitalRepository hospitalRepository;

	public Hospital saveHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}

	public Hospital getHospitalById(int id) {
		return hospitalRepository.findById(id).get();
	}

	public void removeHospitalById(int id) {
		hospitalRepository.deleteById(id);
	}
}
