package com.ty.springboothospitalmgmt.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.EncounterDao;
import com.ty.springboothospitalmgmt.dao.MedOrderDao;
import com.ty.springboothospitalmgmt.dao.PatientDao;
import com.ty.springboothospitalmgmt.dto.Encounter;
import com.ty.springboothospitalmgmt.dto.MedOrder;
import com.ty.springboothospitalmgmt.dto.Patient;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;

@Service
public class MedOrderService {
	@Autowired
	private MedOrderDao medOrderDao;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private EncounterDao encounterDao;

	public ResponseEntity<ResponseStructure<MedOrder>> saveMedOrder(MedOrder medOrder, int patientId, int encounterId) {
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		boolean encFound = false;

		Patient patient = patientDao.getPatientById(patientId);
		if (patient != null) {
			List<Encounter> encounters = patient.getEncounters();
			for (Encounter encounter : encounters) {
				if (encounter.getId() == encounterId) {
					encounter.getMedOrders().add(medOrder);

					responseStructure.setStatusCode(HttpStatus.CREATED.value());
					responseStructure.setMessage("Success");
					responseStructure.setData(medOrderDao.saveMedOrder(medOrder));
					encFound = true;
					break;
				}
			}

			if (encFound) {
				return new ResponseEntity<ResponseStructure<MedOrder>>(responseStructure, HttpStatus.CREATED);
			} else {
				throw new NoIdFoundException("Encounter Id " + encounterId + " does not found");
			}
		} else {
			throw new NoIdFoundException("Patient Id " + patientId + " does not found");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteMedOrder(int patientId, int encounterId, int medOrderId) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean medOrderRemoved = false;

		Patient patient = patientDao.getPatientById(patientId);
		if (patient != null) {
			List<Encounter> encounters = patient.getEncounters();
			for (Encounter enc : encounters) {
				List<MedOrder> medOrders = enc.getMedOrders();
				Iterator<MedOrder> itr = medOrders.iterator();
				while (itr.hasNext()) {
					MedOrder medOrder = itr.next();
					if (medOrder.getId() == medOrderId) {
						itr.remove();
						medOrderRemoved = true;
						break;
					}
				}
				if (medOrderRemoved) {
					medOrderDao.deleteMedOrderBy(medOrderId);
					encounterDao.saveEncounter(enc);
					responseStructure.setStatusCode(HttpStatus.OK.value());
					responseStructure.setMessage("Success");
					responseStructure.setData("Medorder " + medOrderId + " deleted");
					break;
				} else {
					throw new NoIdFoundException("MedOrder id " + medOrderId + " does not found");
				}
			}
			if (medOrderRemoved) {
				return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
			} else {
				throw new NoIdFoundException("Encounter id " + encounterId + " does not found");
			}
		} else {
			throw new NoIdFoundException("Patient id " + patientId + " does not found");
		}
	}

	public ResponseEntity<ResponseStructure<MedOrder>> updateMedOrder(MedOrder medOrder, int patientId,
			int encounterId) {
		Patient patient = patientDao.getPatientById(patientId);
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();

		if (patient != null) {
			List<Encounter> encounters = patient.getEncounters();
			for (Encounter enc : encounters) {
				if (enc.getId() == encounterId) {
					List<MedOrder> list = enc.getMedOrders();
					for (MedOrder med : list) {
						if (med.getId() == medOrder.getId()) {
							responseStructure.setStatusCode(HttpStatus.OK.value());
							responseStructure.setMessage("Success");
							responseStructure.setData(medOrderDao.updateMedOrder(medOrder));
							return new ResponseEntity<ResponseStructure<MedOrder>>(responseStructure, HttpStatus.OK);
						}
					}
					throw new NoIdFoundException("MedOrder id " + medOrder.getId() + " does not found");
				}
				throw new NoIdFoundException("Encounter id " + encounterId + " does not found");
			}
			throw new NoIdFoundException("Patient id " + patientId + " has No Encounters");
		} else {
			throw new NoIdFoundException("Patient id " + patientId + " does not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<MedOrder>>> getAllMedOrders(int patientId) {
		ResponseStructure<List<MedOrder>> responseStructure = new ResponseStructure<List<MedOrder>>();
		Patient patient = patientDao.getPatientById(patientId);
		List<MedOrder> medOrders = new ArrayList<MedOrder>();
		if (patient != null) {
			List<Encounter> encounters = patient.getEncounters();
			for (Encounter enc : encounters) {
				List<MedOrder> list = enc.getMedOrders();
				for (MedOrder med : list) {
					medOrders.add(med);
				}
			}
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(medOrders);
			return new ResponseEntity<ResponseStructure<List<MedOrder>>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NoIdFoundException("Patient id " + patientId + " does not found");
		}
	}
}
