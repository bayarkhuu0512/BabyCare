package com.mercy.babycare.entities;

import java.io.Serializable;
import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;

public class Baby implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1520211660202532355L;
	@DatabaseField(generatedId = true)
	int id;

	@DatabaseField
	String firstName;

	@DatabaseField
	String lastName;

	@DatabaseField
	String gender;

	@DatabaseField
	Date birthDate;

	@DatabaseField
	String hospitalName;

	@DatabaseField
	String doctorName;

	@DatabaseField
	float birthHeight;

	@DatabaseField
	float birthWeight;
	
	@DatabaseField
	float birthHeadCirc;

	@DatabaseField
	float currentHeight;

	@DatabaseField
	float currentWeight;

	@DatabaseField
	float currentHeadCirc;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public float getBirthHeight() {
		return birthHeight;
	}

	public void setBirthHeight(float birthHeight) {
		this.birthHeight = birthHeight;
	}

	public float getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(float birthWeight) {
		this.birthWeight = birthWeight;
	}

	public float getBirthHeadCirc() {
		return birthHeadCirc;
	}

	public void setBirthHeadCirc(float birthHeadCirc) {
		this.birthHeadCirc = birthHeadCirc;
	}

	public float getCurrentHeight() {
		return currentHeight;
	}

	public void setCurrentHeight(float currentHeight) {
		this.currentHeight = currentHeight;
	}

	public float getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(float currentWeight) {
		this.currentWeight = currentWeight;
	}

	public float getCurrentHeadCirc() {
		return currentHeadCirc;
	}

	public void setCurrentHeadCirc(float currentHeadCirc) {
		this.currentHeadCirc = currentHeadCirc;
	}
	
	
}
