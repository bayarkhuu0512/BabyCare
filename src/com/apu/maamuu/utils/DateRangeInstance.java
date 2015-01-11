package com.apu.maamuu.utils;

import java.util.Date;

public class DateRangeInstance {
	private static DateRangeInstance instance = null;

	Date startDate;
	Date endDate;

	protected DateRangeInstance() {
		// Exists only to defeat instantiation.
	}

	public static DateRangeInstance getInstance() {
		if (instance == null) {
			instance = new DateRangeInstance();
		}
		return instance;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
