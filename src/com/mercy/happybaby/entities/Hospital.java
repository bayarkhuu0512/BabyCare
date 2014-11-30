package com.mercy.happybaby.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class Hospital implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1961741578241447195L;
	// id is generated by the database and set on the object automatically
	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField
	String hospitalName;

	@DatabaseField
	String doctorName;

	@DatabaseField
	String painName;
	
	@DatabaseField
	String diagnosis;
	
	@DatabaseField
	String healing;
	
	@DatabaseField(canBeNull = false)
	Date createdDate;

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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPainName() {
		return painName;
	}

	public void setPainName(String painName) {
		this.painName = painName;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getHealing() {
		return healing;
	}

	public void setHealing(String healing) {
		this.healing = healing;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
}