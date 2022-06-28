package com.ty.springboothospitalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboothospitalmgmt.dto.Item;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping("items/medorders/{patientId}/{encounteId}/{medId}")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item, @PathVariable int patientId,
			@PathVariable int encounteId, @PathVariable int medId) {
		return itemService.saveItem(item, patientId, encounteId, medId);
	}

	public String deleteItem() {
		return null;
	}

	public Item updateItem() {
		return null;
	}

	@GetMapping("items/{patientId}")
	public List<Item> getAllItem(@PathVariable int patientId) {
		return itemService.getAllItem(patientId);
	}
}
