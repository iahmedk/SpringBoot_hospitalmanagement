package com.ty.springboothospitalmgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Doctor;
import com.ty.springboothospitalmgmt.repository.DoctorRepository;

@Repository
public class DoctorDao {

	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public void removeDoctorById(int id) {
		doctorRepository.deleteById(id);
	}
}
