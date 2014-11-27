package com.mercy.babycare;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.babycare.db.DatabaseHelper;
import com.mercy.babycare.entities.ActiveOperation;
import com.mercy.babycare.entities.Baby;
import com.mercy.babycare.entities.Breast;
import com.mercy.babycare.entities.Growth;
import com.mercy.babycare.entities.Timeline;
import com.mercy.babycare.entities.Tooth;
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
	private Tooth tooth1, tooth2, tooth3, tooth4;

	private Dao<Timeline, Integer> timelineDAO;
	private Dao<Growth, Integer> growthDAO;

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
			setDateData();
		}
	}

	public void nextOnClick(View v) {

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

	private void setDateData() {
		prefManager.setHasSetup(true);
		try {
			// Baby
			baby = new Baby();
			baby.setFirstName(babyName.getText().toString());
			baby.setLastName("Bayarkhuu");

			Calendar cal = Calendar.getInstance();
			cal.set(2014, 1, 5, 8, 38);
			baby.setBirthDate(cal.getTime());
			baby.setBirthTime(cal.getTime());
			baby.setBirthHeadCirc(35f);
			baby.setBirthHeight(51f);
			baby.setBirthWeight(3500f);
			baby.setDoctorName("Oyungerel");
			baby.setGender(getResources().getString(R.string.gender_girl));
			baby.setHospitalName(getResources().getString(
					R.string.hospital_name));
			timeline = new Timeline();
			timeline.setBaby(baby);
			timeline.setCreatedDate(cal.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timeline);

			// Tooth
			tooth1 = new Tooth();
			Calendar cal1 = Calendar.getInstance();
			cal1.set(2014, 8, 11, 8, 38);
			tooth1.setCreatedDate(cal1.getTime());
			tooth1.setToothNum(1);
			Timeline timelineTooth1 = new Timeline();
			timelineTooth1.setTooth(tooth1);
			timelineTooth1.setCreatedDate(cal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth1);

			tooth2 = new Tooth();
			Calendar cal2 = Calendar.getInstance();
			cal1.set(2014, 7, 20, 8, 38);
			tooth2.setCreatedDate(cal2.getTime());
			tooth2.setToothNum(2);

			Timeline timelineTooth2 = new Timeline();
			timelineTooth2.setTooth(tooth2);
			timelineTooth2.setCreatedDate(cal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth2);

			tooth3 = new Tooth();
			Calendar cal3 = Calendar.getInstance();
			cal3.set(2014, 10, 16, 8, 38);
			tooth3.setCreatedDate(cal3.getTime());
			tooth3.setToothNum(3);

			Timeline timelineTooth3 = new Timeline();
			timelineTooth3.setTooth(tooth3);
			timelineTooth3.setCreatedDate(cal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth3);

			tooth4 = new Tooth();
			Calendar cal4 = Calendar.getInstance();
			cal4.set(2014, 10, 21, 8, 38);
			tooth4.setCreatedDate(cal4.getTime());
			tooth4.setToothNum(4);

			Timeline timelineTooth4 = new Timeline();
			timelineTooth4.setTooth(tooth4);
			timelineTooth4.setCreatedDate(cal4.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth4);

			ActiveOperation activeOperation1 = new ActiveOperation();
			Calendar aoCal1 = Calendar.getInstance();
			aoCal1.set(2014, 01, 06, 8, 38);
			activeOperation1.setCreatedDate(aoCal1.getTime());
			activeOperation1.setActiveName("Гэртээ ирлээ");

			ActiveOperation activeOperation2 = new ActiveOperation();
			Calendar aoCal2 = Calendar.getInstance();
			aoCal2.set(2014, 03, 21, 8, 38);
			activeOperation2.setCreatedDate(aoCal2.getTime());
			activeOperation2.setActiveName("Анх толгойгоо өргөлөө");

			ActiveOperation activeOperation3 = new ActiveOperation();
			Calendar aoCal3 = Calendar.getInstance();
			aoCal3.set(2014, 04, 12, 8, 38);
			activeOperation3.setCreatedDate(aoCal3.getTime());
			activeOperation3.setActiveName("Эргэж хөрвөөлөө");

			ActiveOperation activeOperation4 = new ActiveOperation();
			Calendar aoCal4 = Calendar.getInstance();
			aoCal4.set(2014, 8, 20, 8, 38);
			activeOperation4.setCreatedDate(aoCal4.getTime());
			activeOperation4.setActiveName("Суудаг боллоо");

			Timeline timelineAo1 = new Timeline();
			timelineAo1.setActiveOperation(activeOperation1);
			timelineAo1.setCreatedDate(aoCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);

			Timeline timelineAo2 = new Timeline();
			timelineAo2.setActiveOperation(activeOperation2);
			timelineAo2.setCreatedDate(aoCal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo2);

			Timeline timelineAo3 = new Timeline();
			timelineAo3.setActiveOperation(activeOperation3);
			timelineAo3.setCreatedDate(aoCal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo3);

			Timeline timelineAo4 = new Timeline();
			timelineAo4.setActiveOperation(activeOperation4);
			timelineAo4.setCreatedDate(aoCal4.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo4);

			Growth growth1 = new Growth();
			Calendar growthCal1 = Calendar.getInstance();
			growthCal1.set(2014, 2, 05, 8, 38);
			growth1.setCreatedDate(growthCal1.getTime());
			growth1.setBabyHeight(54);
			growth1.setBabyWeight(4100);
			growth1.setBabyMonth(1);

			Growth growth2 = new Growth();
			Calendar growthCal2 = Calendar.getInstance();
			growthCal2.set(2014, 3, 05, 8, 38);
			growth2.setCreatedDate(growthCal2.getTime());
			growth2.setBabyHeight(58);
			growth2.setBabyWeight(5400);
			growth2.setBabyMonth(2);

			Growth growth3 = new Growth();
			Calendar growthCal3 = Calendar.getInstance();
			growthCal3.set(2014, 4, 05, 8, 38);
			growth3.setCreatedDate(growthCal3.getTime());
			growth3.setBabyHeight(60);
			growth3.setBabyWeight(6500);
			growth3.setBabyMonth(3);

			Growth growth4 = new Growth();
			Calendar growthCal4 = Calendar.getInstance();
			growthCal4.set(2014, 5, 05, 8, 38);
			growth4.setCreatedDate(growthCal4.getTime());
			growth4.setBabyHeight(62);
			growth4.setBabyWeight(7200);
			growth4.setBabyMonth(4);

			Growth growth5 = new Growth();
			Calendar growthCal5 = Calendar.getInstance();
			growthCal5.set(2014, 6, 05, 8, 38);
			growth5.setCreatedDate(growthCal5.getTime());
			growth5.setBabyHeight(63);
			growth5.setBabyWeight(7600);
			growth5.setBabyMonth(5);

			Growth growth6 = new Growth();
			Calendar growthCal6 = Calendar.getInstance();
			growthCal6.set(2014, 7, 05, 8, 38);
			growth6.setCreatedDate(growthCal6.getTime());
			growth6.setBabyHeight(65);
			growth6.setBabyWeight(7800);
			growth6.setBabyMonth(6);

			Growth growth7 = new Growth();
			Calendar growthCal7 = Calendar.getInstance();
			growthCal7.set(2014, 8, 05, 8, 38);
			growth7.setCreatedDate(growthCal7.getTime());
			growth7.setBabyHeight(66);
			growth7.setBabyWeight(8100);
			growth7.setBabyMonth(7);

			Growth growth8 = new Growth();
			Calendar growthCal8 = Calendar.getInstance();
			growthCal8.set(2014, 9, 05, 8, 38);
			growth8.setCreatedDate(growthCal8.getTime());
			growth8.setBabyHeight(67);
			growth8.setBabyWeight(8200);
			growth8.setBabyMonth(8);

			Growth growth9 = new Growth();
			Calendar growthCal9 = Calendar.getInstance();
			growthCal9.set(2014, 10, 05, 8, 38);
			growth9.setCreatedDate(growthCal9.getTime());
			growth9.setBabyHeight(69);
			growth9.setBabyWeight(8700);
			growth9.setBabyMonth(9);

			growthDAO = getHelper().getGrowthDao();
			growthDAO.create(growth1);
			growthDAO.create(growth2);
			growthDAO.create(growth3);
			growthDAO.create(growth4);
			growthDAO.create(growth5);
			growthDAO.create(growth6);
			growthDAO.create(growth7);
			growthDAO.create(growth8);
			growthDAO.create(growth9);

			Breast breast1 = new Breast();
			Calendar breastCal1 = Calendar.getInstance();
			breastCal1.set(2014, 3, 27, 12, 31);
			breast1.setCreatedDate(breastCal1.getTime());
			breast1.setBreastTime(breastCal1.getTime());
			breast1.setRight(true);

			Breast breast2 = new Breast();
			Calendar breastCal2 = Calendar.getInstance();
			breastCal2.set(2014, 11, 27, 14, 31);
			breast2.setCreatedDate(breastCal2.getTime());
			breast2.setBreastTime(breastCal2.getTime());
			breast2.setRight(false);

			Breast breast3 = new Breast();
			Calendar breastCal3 = Calendar.getInstance();
			breastCal3.set(2014, 11, 27, 18, 31);
			breast3.setCreatedDate(breastCal3.getTime());
			breast3.setBreastTime(breastCal3.getTime());
			breast3.setRight(true);

			Timeline timelineBreast1 = new Timeline();
			timelineBreast1.setBreast(breast1);
			timelineBreast1.setCreatedDate(breastCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineBreast1);
			
			Timeline timelineBreast2 = new Timeline();
			timelineBreast2.setBreast(breast2);
			timelineBreast2.setCreatedDate(breastCal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineBreast2);

			Timeline timelineBreast3 = new Timeline();
			timelineBreast3.setBreast(breast3);
			timelineBreast3.setCreatedDate(breastCal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineBreast3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		launchMainActivity();
	}
}
