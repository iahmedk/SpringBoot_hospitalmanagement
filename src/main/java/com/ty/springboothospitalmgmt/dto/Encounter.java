package com.ty.springboothospitalmgmt.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDate DOA;
	private LocalDate DOD;
	private String diseaseType;
	private String treatedBy;
	private String description;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "encounter_id")
	private List<MedOrder> medOrders;

	public List<MedOrder> getMedOrders() {
		return medOrders;
	}

	public void setMedOrders(List<MedOrder> medOrders) {
		this.medOrders = medOrders;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDOA() {
		return DOA;
	}

	public void setDOA(LocalDate dOA) {
		DOA = dOA;
	}

	public LocalDate getDOD() {
		return DOD;
	}

	public void setDOD(LocalDate dOD) {
		DOD = dOD;
	}

	public String getDiseaseType() {
		return diseaseType;
	}

	public void setDiseaseType(String diseaseType) {
		this.diseaseType = diseaseType;
	}

	public String getTreatedBy() {
		return treatedBy;
	}

	public void setTreatedBy(String treatedBy) {
		this.treatedBy = treatedBy;
	}

}
