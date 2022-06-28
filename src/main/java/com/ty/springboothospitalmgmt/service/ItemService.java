package com.ty.springboothospitalmgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.EncounterDao;
import com.ty.springboothospitalmgmt.dao.ItemDao;
import com.ty.springboothospitalmgmt.dao.MedOrderDao;
import com.ty.springboothospitalmgmt.dao.PatientDao;
import com.ty.springboothospitalmgmt.dto.Encounter;
import com.ty.springboothospitalmgmt.dto.Item;
import com.ty.springboothospitalmgmt.dto.MedOrder;
import com.ty.springboothospitalmgmt.dto.Patient;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private EncounterDao encounterDao;

	@Autowired
	private MedOrderDao medOrderDao;

	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item, int patientId, int encounteId, int medId) {
		ResponseStructure<Item> responseStructure = new ResponseStructure<Item>();
		double totalCost = item.getCost() * item.getQuantity();
		item.setTotalCost(totalCost);

		Patient patient = patientDao.getPatientById(patientId);
		if (patient != null) {
			System.out.println("Patient Id Found !!");
			List<Encounter> encounters = patient.getEncounters();
			for (Encounter ec : encounters) {
				if (ec.getId() == encounteId) {
					System.out.println("Encounter Id Found !!");
					List<MedOrder> medOrders = ec.getMedOrders();
					for (MedOrder medOrder : medOrders) {
						if (medOrder.getId() == medId) {
							System.out.println("MedOrder Id found !!");
							medOrder.getItems().add(item);
							responseStructure.setMessage("Success");
							responseStructure.setStatusCode(HttpStatus.OK.value());
							responseStructure.setData(itemDao.saveItem(item));
							return new ResponseEntity<ResponseStructure<Item>>(responseStructure, HttpStatus.OK);
						}
					}
					// med order id not found
					throw new NoIdFoundException("MedOrder id " + medId + " does not found");
				}
				// ec id did not found
				throw new NoIdFoundException("Encounter id " + encounteId + " does not found");
			}
			// ec not found for given patient id
			throw new NoIdFoundException("No encounter found for the patient id " + patientId);
		} else {
			throw new NoIdFoundException("Patient id " + patientId + " does not found");
		}
	}

	public void deleteItemById(int id) {
		itemDao.deleteItemById(id);
	}

	public Item updateItemById(Item item) {
		return itemDao.updateItemById(item);
	}

	public List<Item> getAllItem(int patientId) {
		Patient patient = patientDao.getPatientById(patientId);
		if (patient != null) {
			List<Item> items = new ArrayList<Item>();
			List<Encounter> encounters = patient.getEncounters();
			for (Encounter encounter : encounters) {
				List<MedOrder> medOrders = encounter.getMedOrders();
				for (MedOrder medOrder : medOrders) {
					// TBD
					return null;
				}
			}
			return null;
		} else {
			// specified patient id did not found
			return null;
		}
	}
}
