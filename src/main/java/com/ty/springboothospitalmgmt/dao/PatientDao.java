package com.ty.springboothospitalmgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Patient;
import com.ty.springboothospitalmgmt.repository.PatientRepository;

@Repository
public class PatientDao {
	@Autowired
	private PatientRepository patientRepository;

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public void deletePatientById(int id) {
		patientRepository.deleteById(id);
	}

	public Patient updatePatientById(Patient patient) {
		return patientRepository.save(patient);
	}

	public Patient getPatientById(int id) {
		Optional<Patient> opt = patientRepository.findById(id);
		if (!opt.isEmpty()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
	}
}
