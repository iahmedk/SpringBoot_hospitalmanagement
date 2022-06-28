package com.ty.springboothospitalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboothospitalmgmt.dto.Encounter;
import com.ty.springboothospitalmgmt.dto.Patient;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.service.PatientEncounterService;

@RestController
public class PatientEncounterController {

	@Autowired
	private PatientEncounterService patientEncounterService;

	@PostMapping("patients")
	public ResponseStructure<Patient> savePatient(@RequestBody Patient patient) {
		return patientEncounterService.savePatient(patient);
	}

	@DeleteMapping("patients/{patientId}")
	public ResponseStructure<String> deletePatientById(@PathVariable int patientId) {
		return patientEncounterService.deletePatientById(patientId);
	}

	@PutMapping("patients")
	public ResponseStructure<Patient> upatePatientById(@RequestBody Patient patient) {
		return patientEncounterService.updatePatientById(patient);
	}

	@GetMapping("patients/{patientId}")
	public ResponseStructure<Patient> getPatientById(@PathVariable int patientId) {
		return patientEncounterService.getPatientById(patientId);
	}

	@PostMapping("encounters/{patientId}/{branchId}")
	public ResponseStructure<Encounter> saveEncounter(@RequestBody Encounter encounter, @PathVariable int patientId,
			@PathVariable int branchId) {
		return patientEncounterService.saveEncounter(encounter, patientId, branchId);
	}

	@DeleteMapping("encounters/{encounterId}")
	public ResponseStructure<String> deleteEncounterById(@PathVariable int encounterId) {
		return patientEncounterService.deleteEncounterById(encounterId);
	}

	@PutMapping("encounters")
	public ResponseStructure<Encounter> upatePatientById(@RequestBody Encounter encounter) {
		return patientEncounterService.updateEncounterById(encounter);
	}

	@GetMapping("encounters/{patientId}")
	public ResponseStructure<List<Encounter>> getAllEncounterByPatient(@PathVariable int patientId) {
		return patientEncounterService.getAllEncounterByPatient(patientId);
	}
}