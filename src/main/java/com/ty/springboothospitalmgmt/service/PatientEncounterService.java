package com.ty.springboothospitalmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.BranchDao;
import com.ty.springboothospitalmgmt.dao.EncounterDao;
import com.ty.springboothospitalmgmt.dao.PatientDao;
import com.ty.springboothospitalmgmt.dto.Branch;
import com.ty.springboothospitalmgmt.dto.Encounter;
import com.ty.springboothospitalmgmt.dto.Patient;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class PatientEncounterService {

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private EncounterDao encounterDao;

	@Autowired
	private BranchDao branchDao;

	public ResponseStructure<Patient> savePatient(Patient patient) {
		ResponseStructure<Patient> responseStructure = new ResponseStructure<Patient>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(patientDao.savePatient(patient));
		return responseStructure;
	}

	public ResponseStructure<String> deletePatientById(int id) {
		Patient patient = patientDao.getPatientById(id);
		if (patient != null) {
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Patient id" + id + " deleted");
			patientDao.deletePatientById(id);
			return responseStructure;
		} else {
			throw new NoIdFoundException("Patient id " + id + " does not found");
		}
	}

	public ResponseStructure<Patient> updatePatientById(Patient patient) {
		Patient pt = patientDao.getPatientById(patient.getId());
		if (pt != null) {
			patient.setEncounters(pt.getEncounters());
			ResponseStructure<Patient> responseStructure = new ResponseStructure<Patient>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(patientDao.updatePatientById(patient));
			return responseStructure;
		} else {
			throw new NoIdFoundException("Patient id " + patient.getId() + " does not found");
		}
	}

	public ResponseStructure<Patient> getPatientById(int id) {
		Patient pt = patientDao.getPatientById(id);
		if (pt != null) {
			ResponseStructure<Patient> responseStructure = new ResponseStructure<Patient>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(pt);
			return responseStructure;
		} else {
			throw new NoIdFoundException("Patient id " + id + " does not found");
		}
	}

	public ResponseStructure<Encounter> saveEncounter(Encounter encounter, int patientId, int branchId) {
		Patient patient = patientDao.getPatientById(patientId);
		Branch branch = branchDao.getBranchById(branchId);
		if (patient != null && branch != null) {
			patient.getEncounters().add(encounter);
			branch.getEncounters().add(encounter);
			ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(encounterDao.saveEncounter(encounter));
			return responseStructure;
		} else if (branch != null) {
			throw new NoIdFoundException("Patient id " + patientId + " does not found");
		} else {
			throw new NoIdFoundException("Branch id " + branchId + " does not found");
		}
	}

	public ResponseStructure<String> deleteEncounterById(int id) {
		/*
		 * Encounter encounter = encounterDao.getEncounterById(id); // if (encounter !=
		 * null) { ResponseStructure<String> responseStructure = new
		 * ResponseStructure<String>();
		 * responseStructure.setStatusCode(HttpStatus.OK.value());
		 * responseStructure.setMessage("Succcess");
		 * responseStructure.setData("Encounter id " + id + " does not found"); return
		 * responseStructure; } else { throw new NoIdFoundException("Encounter id " + id
		 * + " does not found"); }
		 */
		return null;
	}

	// You can not fetch only Encounter details since it is Unidirectional 1-N
	public ResponseStructure<Encounter> updateEncounterById(Encounter encounter) {
		/*
		 * Encounter enc = encounterDao.getEncounterById(encounter.getId()); if (enc !=
		 * null) { ResponseStructure<Encounter> responseStructure = new
		 * ResponseStructure<Encounter>();
		 * responseStructure.setStatusCode(HttpStatus.OK.value());
		 * responseStructure.setMessage("Success");
		 * responseStructure.setData(encounterDao.updateEncounterById(encounter));
		 * return responseStructure; } else { throw new
		 * NoIdFoundException("Encounter id " + encounter.getId() + " does not found");
		 * }
		 */
		return null;
	}

	public ResponseStructure<List<Encounter>> getAllEncounterByPatient(int id) {
		Patient patient = patientDao.getPatientById(id);
		if (patient != null) {
			ResponseStructure<List<Encounter>> responseStructure = new ResponseStructure<List<Encounter>>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(patient.getEncounters());
			return responseStructure;
		} else {
			throw new NoIdFoundException("Patient id " + id + " doesn't found");
		}
	}
}
