package com.mercy.babycare.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class Learn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1961741578241447195L;
	// id is generated by the database and set on the object automatically
	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField
	String feedName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedName() {
		return feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
}
