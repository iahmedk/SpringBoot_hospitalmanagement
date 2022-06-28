package com.ty.springboothospitalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboothospitalmgmt.dto.MedOrder;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.service.MedOrderService;

@RestController
public class MedOrderController {

	@Autowired
	private MedOrderService medOrderService;

	@PostMapping("medorders/{patientId}/{encounterId}")
	public ResponseEntity<ResponseStructure<MedOrder>> saveMedOrder(@RequestBody MedOrder medOrder,
			@PathVariable int patientId, @PathVariable int encounterId) {
		return medOrderService.saveMedOrder(medOrder, patientId, encounterId);
	}

	@DeleteMapping("medorders/{patientId}/{encounterId}/{medOrderId}")
	public ResponseEntity<ResponseStructure<String>> deleteMedOrder(@PathVariable int patientId, @PathVariable int encounterId,
			@PathVariable int medOrderId) {
		return medOrderService.deleteMedOrder(patientId, encounterId, medOrderId);
	}

	@PutMapping("medorders/{patientId}/{encounterId}")
	public ResponseEntity<ResponseStructure<MedOrder>> updateMedOrder(@RequestBody MedOrder medOrder,
			@PathVariable int patientId, @PathVariable int encounterId) {
		return medOrderService.updateMedOrder(medOrder, patientId, encounterId);
	}

	@GetMapping("medorders/{patientId}")
	public ResponseEntity<ResponseStructure<List<MedOrder>>> getAllMedOrders(@PathVariable int patientId) {
		return medOrderService.getAllMedOrders(patientId);
	}
}
