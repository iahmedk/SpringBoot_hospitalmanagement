package com.ty.springboothospitalmgmt.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.BranchDao;
import com.ty.springboothospitalmgmt.dao.DoctorDao;
import com.ty.springboothospitalmgmt.dto.Branch;
import com.ty.springboothospitalmgmt.dto.Doctor;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private BranchDao branchDao;

	public ResponseStructure<Doctor> saveDoctor(Doctor doctor, int id) {
		Branch branch = branchDao.getBranchById(id);
		ResponseStructure<Doctor> responseStructure = new ResponseStructure<Doctor>();
		if (branch != null) {
			// add branch to doctor
			doctor.setBranch(branch);
			// add doctor to branch
			branch.getDoctors().add(doctor);

			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(doctorDao.saveDoctor(doctor));
			return responseStructure;
		} else {
			throw new NoIdFoundException("Branch id " + id + " does not exists");
		}
	}

	public ResponseStructure<String> removeDoctorById(long branchPin, int docId) {

		Branch branch = branchDao.getBranchByPin(branchPin);
		boolean isDoctorRemoved = false;

		if (branch != null) {
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();

			Iterator<Doctor> itr = branch.getDoctors().iterator();
			while (itr.hasNext()) {
				Doctor doctor = itr.next();
				if (doctor.getId() == docId) {
					itr.remove();
					isDoctorRemoved = true;
					break;
				}
			}
			if (isDoctorRemoved) {
				doctorDao.removeDoctorById(docId);
				branchDao.saveBranch(branch);
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Success");
				responseStructure.setData("Doctor id " + docId + " removed");
				return responseStructure;
			} else {
				throw new NoIdFoundException("Doctor id " + docId + " does not exists");
			}

		} else {
			throw new NoIdFoundException("Branch pin " + branchPin + " does not exists");
		}
	}
}
