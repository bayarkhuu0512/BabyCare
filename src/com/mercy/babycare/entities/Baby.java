package com.mercy.babycare.entities;

import java.io.Serializable;

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
}
