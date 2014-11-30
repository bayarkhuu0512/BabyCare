package com.mercy.happybaby.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class ChangeDiaper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1961741578241447195L;
	// id is generated by the database and set on the object automatically
	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField
	String diaperType;

	@DatabaseField(canBeNull = false)
	Date createdDate;

	@DatabaseField
	int dirty;

	@DatabaseField
	int dry;

	@DatabaseField
	int wet;

	@DatabaseField
	int mixed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiaperType() {
		return diaperType;
	}

	public void setDiaperType(String diaperType) {
		this.diaperType = diaperType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getDirty() {
		return dirty;
	}

	public void setDirty(int dirty) {
		this.dirty = dirty;
	}

	public int getDry() {
		return dry;
	}

	public void setDry(int dry) {
		this.dry = dry;
	}

	public int getWet() {
		return wet;
	}

	public void setWet(int wet) {
		this.wet = wet;
	}

	public int getMixed() {
		return mixed;
	}

	public void setMixed(int mixed) {
		this.mixed = mixed;
	}

}