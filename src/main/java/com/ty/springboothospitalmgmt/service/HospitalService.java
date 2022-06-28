package com.ty.springboothospitalmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.HospitalDao;
import com.ty.springboothospitalmgmt.dto.Hospital;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	public ResponseStructure<Hospital> saveHospital(Hospital hospital) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(hospitalDao.saveHospital(hospital));

		return responseStructure;
	}

	public ResponseStructure<Hospital> getHospitalById(int id) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();

		Hospital hospital = hospitalDao.getHospitalById(id);
		if (hospital != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(hospital);
		} else {
			throw new NoIdFoundException("Hospital id " + id + " does not exists");
		}
		return responseStructure;
	}

	public ResponseStructure<String> removeHospitalById(int id) {
		Hospital hospital = hospitalDao.getHospitalById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		if (hospital != null) {
			hospitalDao.removeHospitalById(id);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Hospital id " + id + " is removed");
		} else {
			throw new NoIdFoundException("Hospital id " + id + " does not exists");
		}
		return responseStructure;
	}
}
