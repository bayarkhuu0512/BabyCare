package com.mercy.happybaby.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PrefManager implements Constants {

	public String TAG = PrefManager.class.getName();

	SharedPreferences pref;
	public static PrefManager instance;
	Editor editor;
	Context _context;

	// Constructor
	public PrefManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, 0);
		editor = pref.edit();
	}

	public static PrefManager getSessionInstance() {
		return instance;
	}

	public static void setSessionInstance(PrefManager session) {
		instance = session;
	}

	/**
	 * Clear session details
	 * */
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
	}

	public boolean getHasSetup() {
		return pref.getBoolean(PREF_HASSETUP, false);
	}

	public void setHasSetup(boolean hasSetup) {
		editor.putBoolean(PREF_HASSETUP, hasSetup);
		editor.commit();
	}
}
