package com.ty.springboothospitalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.springboothospitalmgmt.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
	@Query(value = "SELECT b FROM Branch b where b.address.state = ?1")
	List<Branch> findByState(String name);

	@Query(value = "SELECT b FROM Branch b where b.address.pin = ?1")
	Branch findByPin(long pin);
}
