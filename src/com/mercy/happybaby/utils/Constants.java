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
	
	public static int TYPE_BABY = 0;
	public static int TYPE_BREAST = 1;
	public static int TYPE_CHANGEDIAPER = 2;
	public static int TYPE_DRINK = 3;
	public static int TYPE_FEED= 4;
	public static int TYPE_FORMULA = 5;
	public static int TYPE_GROWTH = 6;
	public static int TYPE_HEALTH = 7;
	public static int TYPE_HOSPITAL = 8;
	public static int TYPE_LEARN = 9;
	public static int TYPE_PAIN = 10;
	public static int TYPE_TOOTH = 11;
	public static int TYPE_VACCINE = 12;
	public static int TYPE_VITAMIN = 13;
	public static int TYPE_PURCHASE = 14;
	public static int TYPE_ACTIVEOPERATION = 15;
}
