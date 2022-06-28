package com.ty.springboothospitalmgmt.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hospitalName;
	private String mainBranchEmail;
	private String gstNum;
	private String mainPhone;
	private LocalDate YOE;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
	List<Branch> branchs;

	public LocalDate getYOE() {
		return YOE;
	}

	public void setYOE(LocalDate yOE) {
		YOE = yOE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getMainBranchEmail() {
		return mainBranchEmail;
	}

	public void setMainBranchEmail(String mainBranchEmail) {
		this.mainBranchEmail = mainBranchEmail;
	}

	public String getGstNum() {
		return gstNum;
	}

	public void setGstNum(String gstNum) {
		this.gstNum = gstNum;
	}

	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public List<Branch> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}

}
