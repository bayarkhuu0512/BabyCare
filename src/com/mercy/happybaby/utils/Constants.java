package com.mercy.happybaby.utils;

import java.text.SimpleDateFormat;

import android.graphics.Typeface;

public interface Constants {
	String PREF_NAME = "babycare";
	String PREF_HASSETUP = "hasSetup";
	String DATE_FORMAT = "yyyy-MM-dd";
	String TIME_FORMAT = "HH:mm";
	String ROW_MONTH_FORMAT = "MM";
	String ROW_DAY_FORMAT = "dd";
	String ROW_DAY_OF_WEEK = "E";

	SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
	SimpleDateFormat rowMonthFormat = new SimpleDateFormat(ROW_MONTH_FORMAT);
	SimpleDateFormat rowDayFormat = new SimpleDateFormat(ROW_DAY_FORMAT);
	SimpleDateFormat rowDayOfWeekFormat = new SimpleDateFormat(ROW_DAY_OF_WEEK);

	String ROBOTO_LIGHT = "fonts/Roboto-Light.ttf";
	String ROBOTO_THIN = "fonts/Roboto-Thin.ttf";
	String ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";

	public static int ACTIVE_OPERATION_BATH = 0;
	public static int ACTIVE_OPERATION_PLAY = 1;
	public static int ACTIVE_OPERATION_SLEEP = 2;
	public static int ACTIVE_OPERATION_OUT = 3;
	public static int ACTIVE_OPERATION_MASSAGE = 4;
	public static int ACTIVE_OPERATION_EAR = 5;
	public static int ACTIVE_OPERATION_NOSE = 6;

}
