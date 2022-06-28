package com.ty.springboothospitalmgmt.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.BranchDao;
import com.ty.springboothospitalmgmt.dao.DepartmentDao;
import com.ty.springboothospitalmgmt.dto.Branch;
import com.ty.springboothospitalmgmt.dto.Department;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private BranchDao branchDao;

	public ResponseStructure<Department> saveDepartment(Department department, int id) {
		Branch branch = branchDao.getBranchById(id);
		ResponseStructure<Department> responseStructure = new ResponseStructure<Department>();
		if (branch != null) {
			branch.getDepartments().add(department);
			// assign branch to dept
			department.setBranch(branch);

			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(departmentDao.saveDepartment(department));
			return responseStructure;

		} else {
			throw new NoIdFoundException("Branch id " + id + " does not exists");
		}
	}

	/*
	 * In OneToMany bi-directional, when we wann to remove specific dept of specific
	 * branch, we can not mention CASCADE.ALL in the child side as it deletes entire
	 * branch if we delete one dept from it. Here we need to do following 1. Get the
	 * branch to delete dept 2. remove dept from that branch 3. Delete that dept 4.
	 * Save the branch again. Dept will become orphan hence it gets deleted
	 * automatically
	 */
	public ResponseStructure<String> removeDepartmentByName(long branchPin, String deptName) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		// get the branch of specified pin
		Branch branch = branchDao.getBranchByPin(branchPin);
		if (branch != null) {
			List<Department> depts = branch.getDepartments();
			boolean isDeptRemoved = false;
			int deptId = 0;

			Iterator<Department> itr = depts.iterator();
			while (itr.hasNext()) {
				Department dept = itr.next();
				if (dept.getDeptName().equalsIgnoreCase(deptName)) {
					deptId = dept.getId();
					itr.remove();// remove that dept
					isDeptRemoved = true;
					break;
				}
			}

			if (isDeptRemoved) {
				departmentDao.removeDepartmentById(deptId);// delete that dept
				branchDao.saveBranch(branch);// save the branch again
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Success");
				responseStructure.setData("Department id " + deptId + " removed");
				return responseStructure;
			} else {
				throw new NoIdFoundException(deptName + " department not found");
			}
		} else {
			throw new NoIdFoundException("No branch found with the pin " + branchPin);
		}

	}

}
