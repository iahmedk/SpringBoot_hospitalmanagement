package com.ty.springboothospitalmgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.MedOrder;
import com.ty.springboothospitalmgmt.repository.MedOrderRepository;

@Repository
public class MedOrderDao {

	@Autowired
	private MedOrderRepository medOrderRepository;

	public MedOrder saveMedOrder(MedOrder medOrder) {
		return medOrderRepository.save(medOrder);
	}

	public void deleteMedOrderBy(int id) {
		medOrderRepository.deleteById(id);
	}

	public MedOrder updateMedOrder(MedOrder medOrder) {
		return medOrderRepository.save(medOrder);
	}
}
