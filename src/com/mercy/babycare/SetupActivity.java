package com.mercy.babycare;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.babycare.db.DatabaseHelper;
import com.mercy.babycare.entities.Baby;
import com.mercy.babycare.entities.Timeline;
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
	private TextView babyName;
	private Timeline timeline;
	private Baby baby;
	private Dao<Timeline, Integer> timelineDAO;
	private Dao<Baby, Integer> babyDAO;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefManager = new PrefManager(this);
		if (prefManager.getHasSetup()) {
			launchMainActivity();
		} else {
			setContentView(R.layout.fragment_setup);
			// Init UIs
			babyName = (TextView) findViewById(R.id.childName);
		}
	}

	public void nextOnClick(View v) {
		prefManager.setHasSetup(true);
		try {
			baby = new Baby();
			baby.setFirstName(babyName.getText().toString());
			baby.setLastName("Bayarkhuu");
//			babyDAO = getHelper().getBabyDao();
//			babyDAO.create(baby);

			timeline = new Timeline();
			timeline.setBaby(baby);
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timeline);
			
			List<Baby> babyList = babyDAO.queryForAll();
			Log.d(LOG_TAG, "babyList size " + babyList.size());
			
			List<Timeline> list = timelineDAO.queryForAll();
			Log.d(LOG_TAG, "List size " + list.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// launchMainActivity();
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
