package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.Encounter;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {

}