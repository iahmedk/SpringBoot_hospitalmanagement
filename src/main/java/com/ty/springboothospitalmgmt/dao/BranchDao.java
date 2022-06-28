package com.ty.springboothospitalmgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Branch;
import com.ty.springboothospitalmgmt.repository.BranchRepository;

@Repository
public class BranchDao {

	@Autowired
	private BranchRepository branchRepository;

	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	public List<Branch> getAllBranch() {
		return branchRepository.findAll();
	}

	public void removeBranchById(int id) {
		branchRepository.deleteById(id);
	}

	public Branch getBranchById(int id) {
		Optional<Branch> opt = branchRepository.findById(id);
		if (!opt.isEmpty()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public List<Branch> getBranchByState(String name) {
		return branchRepository.findByState(name);
	}

	public Branch getBranchByPin(long pin) {
		return branchRepository.findByPin(pin);
	}
}
