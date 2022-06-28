package com.ty.springboothospitalmgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.BranchDao;
import com.ty.springboothospitalmgmt.dao.HospitalDao;
import com.ty.springboothospitalmgmt.dto.Branch;
import com.ty.springboothospitalmgmt.dto.Hospital;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private HospitalDao hospitalDao;

	public ResponseStructure<Branch> saveBranch(Branch branch) {
		// Assign branch to main branch
		List<Branch> list = new ArrayList<Branch>();
		list.add(branch);
		Hospital hospital = hospitalDao.getHospitalById(1);
		hospital.setBranchs(list);

		// Assign main branch to branch
		branch.setHospital(hospital);

		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(branchDao.saveBranch(branch));
		return responseStructure;
	}

	public ResponseStructure<List<Branch>> getAllBranch() {
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
		List<Branch> list = branchDao.getAllBranch();

		if (list.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(list);
		} else {
			throw new NoIdFoundException("No branches found");
		}
		return responseStructure;
	}

	public ResponseStructure<Branch> getBranchById(int id) {
		Branch branch = branchDao.getBranchById(id);
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();

		if (branch != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(branch);
		} else {
			throw new NoIdFoundException("Branch id " + id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<String> removeBranchById(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		if (branchDao.getBranchById(id) != null) {
			branchDao.removeBranchById(id);

			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Branch id " + id + " removed");
		} else {
			throw new NoIdFoundException("Branch id " + id + " removed");
		}
		return responseStructure;
	}

	public ResponseStructure<List<Branch>> getBranchByState(String name) {
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
		List<Branch> list = branchDao.getBranchByState(name);

		if (list.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(list);
		} else {
			throw new NoIdFoundException(name + " has no branches");
		}
		return responseStructure;
	}
}