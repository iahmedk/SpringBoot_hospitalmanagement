package com.ty.springboothospitalmgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Encounter;
import com.ty.springboothospitalmgmt.repository.EncounterRepository;

@Repository
public class EncounterDao {
	@Autowired
	private EncounterRepository encounterRepository;

	public Encounter saveEncounter(Encounter encounter) {
		return encounterRepository.save(encounter);
	}

	public void deleteEncounterById(int id) {
		encounterRepository.deleteById(id);
	}

	public Encounter updateEncounterById(Encounter encounter) {
		return encounterRepository.save(encounter);
	}
}
