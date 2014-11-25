package com.mercy.babycare;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.babycare.db.DatabaseHelper;
import com.mercy.babycare.entities.Baby;
import com.mercy.babycare.utils.PrefManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SetupActivity extends FragmentActivity {
	String LOG_TAG = SetupActivity.class.getName();

	private PrefManager prefManager;
	private DatabaseHelper databaseHelper = null;
	private Intent intent;
	private TextView childName;
	private Baby child;
	private Dao<Baby, Integer> childDAO;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefManager = new PrefManager(this);
		if (prefManager.getHasSetup()) {
			launchMainActivity();
		} else {
			setContentView(R.layout.fragment_setup);
			// Init UIs
			childName = (TextView) findViewById(R.id.childName);
		}
	}

	public void nextOnClick(View v) {
		prefManager.setHasSetup(true);
		child = new Baby();
		child.setFirstName(childName.getText().toString());
		try {
			childDAO = getHelper().getChildDao();
			childDAO.create(child);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		launchMainActivity();
	}

	private void launchMainActivity() {
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}
