package com.mercy.happybaby.entities;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class Baby implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -2874694476835632257L;
	@DatabaseField(generatedId = true)
	int id;

	@DatabaseField(canBeNull = true)
	String firstName;

	@DatabaseField(canBeNull = true)
	String lastName;

	@DatabaseField(canBeNull = true)
	String gender;

	@DatabaseField(canBeNull = true)
	Date birthDate;

	@DatabaseField(canBeNull = true)
	Date birthTime;

	@DatabaseField(canBeNull = true)
	String hospitalName;

	@DatabaseField(canBeNull = true)
	String doctorName;

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

	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

}
