package com.mercy.happybaby;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.github.mikephil.charting.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.ActiveOperation;
import com.mercy.happybaby.entities.Baby;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.ChangeDiaper;
import com.mercy.happybaby.entities.Drink;
import com.mercy.happybaby.entities.Feed;
import com.mercy.happybaby.entities.Formula;
import com.mercy.happybaby.entities.Growth;
import com.mercy.happybaby.entities.Hospital;
import com.mercy.happybaby.entities.Learn;
import com.mercy.happybaby.entities.Purchase;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.entities.Tooth;
import com.mercy.happybaby.entities.Vaccine;
import com.mercy.happybaby.entities.Vitamin;
import com.mercy.happybaby.ui.activeoperation.ActiveOperationFragment;
import com.mercy.happybaby.ui.changediaper.ChangeDiaperFragment;
import com.mercy.happybaby.ui.chart.ChartFragment;
import com.mercy.happybaby.ui.databook.DatabookFragment;
import com.mercy.happybaby.ui.health.HealthFragment;
import com.mercy.happybaby.ui.helpcenter.HelpCenterFragment;
import com.mercy.happybaby.ui.learn.LearnFragment;
import com.mercy.happybaby.ui.meal.AddBreastFragment;
import com.mercy.happybaby.ui.meal.AddFeedFragment;
import com.mercy.happybaby.ui.meal.AddFormulaFragment;
import com.mercy.happybaby.ui.meal.MealFragment;
import com.mercy.happybaby.ui.others.AboutFragment;
import com.mercy.happybaby.ui.others.ProfileFragment;
import com.mercy.happybaby.ui.others.SettingsFragment;
import com.mercy.happybaby.ui.purchase.PurchaseFragment;
import com.mercy.happybaby.ui.timeline.TimelineCreateFragment;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.ui.tooth.ToothFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.Crouton;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class MainActivity extends Activity implements
		CalendarDatePickerDialog.OnDateSetListener {
	String LOG_TAG = MainActivity.class.getName();
	private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private LinearLayout drawerll;
	private TextView profileName;
	private TextView profileAge;
	private ImageView profileImage;

	private Typeface roboto_light;

	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mTitles;

	private DatabaseHelper databaseHelper = null;
	private Dao<Timeline, Integer> timelineDAO;
	private Dao<Baby, Integer> babyDAO;
	private List<Baby> babyList = null;

	private CroutonMessage crtnMsg = null;

	private Context context;

	private int defaultFragment = 0;

	private int selecteCalendarPicker = 0;
	DateRangeInstance dateRange = DateRangeInstance.getInstance();
	Calendar dateRangeStart, dateRangeEnd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		Utils.init(getResources());
		crtnMsg = new CroutonMessage(this);
		setDates();
		mTitles = getResources().getStringArray(R.array.nav_array);

		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		drawerll = (LinearLayout) findViewById(R.id.drawerll);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setIcon(getResources().getDrawable(R.drawable.baby67));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		LeftNavAdapter leftNavAdapter = new LeftNavAdapter(this, mTitles);
		mDrawerList.setAdapter(leftNavAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		if (savedInstanceState == null) {
			selectItem(0);
		}

		roboto_light = Typeface.createFromAsset(getAssets(),
				Constants.ROBOTO_LIGHT);
		profileImage = (ImageView) findViewById(R.id.profileImage);
		profileName = (TextView) findViewById(R.id.profileName);
		profileName.setTypeface(roboto_light);
		profileAge = (TextView) findViewById(R.id.profileAge);
		profileAge.setTypeface(roboto_light);

		if (babyList.get(0) != null) {
			profileName.setText(babyList.get(0).getLastName().substring(0, 1)
					+ "." + babyList.get(0).getFirstName());
			profileAge.setText(Constants.dateFormat.format(babyList.get(0)
					.getBirthDate()) + "");
		}

	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void setDates() {

		try {
			babyDAO = getHelper().getBabyDao();
			babyList = babyDAO.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setStartDate(babyList.get(0).getBirthDate());
		dateRange.setEndDate(Calendar.getInstance().getTime());

		dateRangeStart = Calendar.getInstance();
		dateRangeStart.setTime(babyList.get(0).getBirthDate());
		dateRangeEnd = Calendar.getInstance();
	}

	public void leftMenuClicked(View v) {
		Log.d(LOG_TAG, "v " + v.getId());
		selectItem(v.getId());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		return super.onOptionsItemSelected(item);

	}

	private void selectItem(int groupPos) {
		Fragment fragment = null;
		switch (groupPos) {
		case 0:
			fragment = new TimelineFragment();
			break;
		case 1:
			fragment = new ChartFragment();
			break;
		case 2:
			fragment = new MealFragment();
			break;
		case 3:
			fragment = new ChangeDiaperFragment();
			break;
		case 4:
			fragment = new HealthFragment();
			break;
		case 5:
			fragment = new ActiveOperationFragment();
			break;
		case 6:
			fragment = new LearnFragment();
			break;
		case 7:
			fragment = new ToothFragment();
			break;
		case 8:
			fragment = new PurchaseFragment();
			break;
		case 9:
			fragment = new DatabookFragment();
			break;
		case 10:
			fragment = new HelpCenterFragment();
			break;
		case 11:
			fragment = new SettingsFragment();
			break;
		case 12:
			fragment = new AboutFragment();
			break;
		default:
			fragment = new TimelineFragment();
			break;
		}
		if (fragment != null) {
			setTitle(mTitles[groupPos]);
			defaultFragment = groupPos;
			mDrawerList.setItemChecked(groupPos, true);
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
			mDrawerLayout.closeDrawer(drawerll);
		} else {
			crtnMsg.hide();
			crtnMsg.showCrouton(Style.CONFIRM, context.getResources()
					.getString(R.string.notavailable));
		}

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void floatingActionButtonOnClick(View v) {

		Fragment fragment = new TimelineCreateFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
	}

	public void cancelButtonOnClick(View v) {
		gotoFragment();
	}

	// tmp use for Breast left or right;
	private Random rnd = new Random();

	public boolean getRandomBoolean() {
		return rnd.nextBoolean();
	}

	private void gotoFragment() {
		Fragment fragment = null;
		switch (defaultFragment) {
		case 0:
			fragment = new TimelineFragment();
			break;
		case 1:
			fragment = new ChartFragment();
			break;
		case 2:
			fragment = new MealFragment();
			break;
		case 3:
			fragment = new ChangeDiaperFragment();
			break;
		case 4:
			fragment = new HealthFragment();
			break;
		case 5:
			fragment = new ActiveOperationFragment();
			break;
		case 6:
			fragment = new LearnFragment();
			break;
		case 7:
			fragment = new ToothFragment();
			break;
		case 8:
			fragment = new PurchaseFragment();
			break;
		case 9:
			fragment = new HelpCenterFragment();
			break;
		case 10:
			fragment = new DatabookFragment();
			break;
		case 11:
			fragment = new SettingsFragment();
			break;
		case 12:
			fragment = new AboutFragment();
			break;
		default:
			fragment = new TimelineFragment();
			break;
		}

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
		setTitle(mTitles[defaultFragment]);
		mDrawerList.setItemChecked(defaultFragment, true);
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	public void onDestroy() {
		Log.d(LOG_TAG, "OnDestroy");
		crtnMsg = null;
		Crouton.clearCroutonsForActivity(this);
		// Must always call the super method at the end.
		super.onDestroy();
	}

	@Override
	public void onResume() {
		// Example of reattaching to the fragment
		super.onResume();
		CalendarDatePickerDialog calendarDatePickerDialog = (CalendarDatePickerDialog) getFragmentManager()
				.findFragmentByTag(FRAG_TAG_DATE_PICKER);
		if (calendarDatePickerDialog != null) {
			calendarDatePickerDialog.setOnDateSetListener(this);
		}
	}

	public void startDateOnClick(View v) {
		Log.d(LOG_TAG, "OnClick");
		FragmentManager fm = getFragmentManager();
		Calendar c = Calendar.getInstance();
		c.setTime(dateRange.getStartDate());
		CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
				.newInstance(MainActivity.this, c.get(Calendar.YEAR),
						c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		calendarDatePickerDialog.setYearRange(
				dateRangeStart.get(Calendar.YEAR),
				dateRangeEnd.get(Calendar.YEAR) + 1);
		calendarDatePickerDialog.show(fm, FRAG_TAG_DATE_PICKER);
		selecteCalendarPicker = 0;
	}

	public void endDateOnClick(View v) {
		Log.d(LOG_TAG, "OnClick");
		FragmentManager fm = getFragmentManager();
		Calendar c = Calendar.getInstance();
		c.setTime(dateRange.getEndDate());
		CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
				.newInstance(MainActivity.this, c.get(Calendar.YEAR),
						c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		calendarDatePickerDialog.setYearRange(
				dateRangeStart.get(Calendar.YEAR),
				dateRangeEnd.get(Calendar.YEAR) + 1);
		calendarDatePickerDialog.show(fm, FRAG_TAG_DATE_PICKER);
		selecteCalendarPicker = 1;
	}

	@Override
	public void onDateSet(CalendarDatePickerDialog dialog, int year,
			int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "Year: " + year + "\nMonth: " + monthOfYear + "\nDay: "
				+ dayOfMonth);
		if (selecteCalendarPicker == 0) {
			Calendar c = Calendar.getInstance();
			c.set(year, monthOfYear, dayOfMonth);
			dateRange.setStartDate(c.getTime());

		} else {
			Calendar c = Calendar.getInstance();
			c.set(year, monthOfYear, dayOfMonth);
			dateRange.setEndDate(c.getTime());
		}
		gotoFragment();
	}

	public void profileOnClick(View v) {
		Log.d(LOG_TAG, "OnClick");
		Fragment fragment = new ProfileFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
		setTitle("Миний тухай");
		mDrawerList.setItemChecked(0, true);
		mDrawerLayout.closeDrawer(drawerll);
	}

	// Add values
	public void addBreastOnClick(View v) {
		gotoAddFragment(new AddBreastFragment());
	}

	public void extraFoodOnClick(View v) {
		gotoAddFragment(new AddFeedFragment());
	}
	
	public void formulaOnClick(View v) {
		gotoAddFragment(new AddFormulaFragment());
	}

	public void drinkOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Drink drink1 = new Drink();
		Calendar drinkCal1 = Calendar.getInstance();
		drink1.setCreatedDate(drinkCal1.getTime());
		drink1.setDrinkName("Ус");
		drink1.setMl(250);

		Timeline timelineDrink1 = new Timeline();
		timelineDrink1.setDrink(drink1);
		timelineDrink1.setCreatedDate(drinkCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineDrink1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(drinkCal1.getTime());

		gotoFragment();
	}

	public void medcheckOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Growth growth10 = new Growth();
		Calendar growthCal9 = Calendar.getInstance();
		growth10.setCreatedDate(growthCal9.getTime());
		growth10.setBabyHeight(70);
		growth10.setBabyWeight(9000);
		growth10.setBabyMonth(10);
		Timeline growthTimeline9 = new Timeline();
		growthTimeline9.setGrowth(growth10);
		growthTimeline9.setCreatedDate(growthCal9.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline9);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(growthCal9.getTime());

		gotoFragment();

	}

	public void hospitalOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Hospital hospital1 = new Hospital();
		Calendar hospitalCal1 = Calendar.getInstance();
		hospital1.setCreatedDate(hospitalCal1.getTime());
		hospital1.setDiagnosis("Хоолой улайсан");
		hospital1.setPainName("Халуурсан");
		hospital1.setHealing("Вит С");
		hospital1.setDoctorName("Ганаа");
		hospital1.setHospitalName("Өрхийн эмнэлэг");

		Timeline timelineHospital1 = new Timeline();
		timelineHospital1.setHospital(hospital1);
		timelineHospital1.setCreatedDate(hospitalCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineHospital1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateRange.setEndDate(hospitalCal1.getTime());

		gotoFragment();

	}

	public void vitaminOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Vitamin vitamin1 = new Vitamin();
		Calendar vitaminCal1 = Calendar.getInstance();
		vitamin1.setCreatedDate(vitaminCal1.getTime());
		vitamin1.setVitaminName("Д Витамин");

		Timeline timelineVitamin1 = new Timeline();
		timelineVitamin1.setVitamin(vitamin1);
		timelineVitamin1.setCreatedDate(vitaminCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineVitamin1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(vitaminCal1.getTime());

		gotoFragment();
	}

	public void vaccineOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Vaccine vaccine1 = new Vaccine();
		Calendar vaccineCal1 = Calendar.getInstance();
		vaccine1.setCreatedDate(vaccineCal1.getTime());
		vaccine1.setVaccineName("Сүрьэний эсрэг вакцин");

		Timeline timelineVaccine1 = new Timeline();
		timelineVaccine1.setVaccine(vaccine1);
		timelineVaccine1.setCreatedDate(vaccineCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineVaccine1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateRange.setEndDate(vaccineCal1.getTime());

		gotoFragment();

	}

	public void showerOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_BATH);
		ao1.setActiveName("Усанд орлоо");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void sleepOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_SLEEP);
		ao1.setActiveName("Унтлаа");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void playOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_PLAY);
		ao1.setActiveName("Бөмбөгөөр тоголлоо");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void outOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_OUT);
		ao1.setActiveName("Гадаа гарлаа");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void massageOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_MASSAGE);
		ao1.setActiveName("Хөлөндөө массаж хийлгэв");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void earOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));

		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_EAR);
		ao1.setActiveName("Чихээ цэвэрлүүлэв");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void noseOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ActiveOperation ao1 = new ActiveOperation();
		Calendar aoCal1 = Calendar.getInstance();
		ao1.setCreatedDate(aoCal1.getTime());
		ao1.setType(Constants.ACTIVE_OPERATION_NOSE);
		ao1.setActiveName("Хамраа цэвэрлүүлэв");

		Timeline timelineAo1 = new Timeline();
		timelineAo1.setActiveOperation(ao1);
		timelineAo1.setCreatedDate(aoCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(aoCal1.getTime());

		gotoFragment();

	}

	public void changeDiaperOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		ChangeDiaper changeDiaper1 = new ChangeDiaper();
		Calendar changeDiaperCal1 = Calendar.getInstance();
		changeDiaper1.setCreatedDate(changeDiaperCal1.getTime());
		changeDiaper1.setDirty(1);
		changeDiaper1.setDry(0);
		changeDiaper1.setMixed(0);
		changeDiaper1.setWet(0);
		changeDiaper1.setDiaperType("Хуурай");

		Timeline timelineChangeDiaper1 = new Timeline();
		timelineChangeDiaper1.setChangeDiaper(changeDiaper1);
		timelineChangeDiaper1.setCreatedDate(changeDiaperCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineChangeDiaper1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(changeDiaperCal1.getTime());

		gotoFragment();

	}

	public void anniversaryOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Growth growth10 = new Growth();
		Calendar growthCal9 = Calendar.getInstance();
		growth10.setCreatedDate(growthCal9.getTime());
		growth10.setBabyHeight(72);
		growth10.setBabyWeight(9300);
		growth10.setBabyMonth(12);
		Timeline growthTimeline9 = new Timeline();
		growthTimeline9.setGrowth(growth10);
		growthTimeline9.setCreatedDate(growthCal9.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline9);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(growthCal9.getTime());

		gotoFragment();

	}

	public void learnOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Learn learn4 = new Learn();
		Calendar learnCal4 = Calendar.getInstance();
		learn4.setCreatedDate(learnCal4.getTime());
		learn4.setLearnName("Мөлхдөг боллоо");

		Timeline timelineLearn1 = new Timeline();
		timelineLearn1.setLearn(learn4);
		timelineLearn1.setCreatedDate(learnCal4.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineLearn1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(learnCal4.getTime());

		gotoFragment();

	}

	public void toothOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Tooth tooth2 = new Tooth();
		Calendar cal2 = Calendar.getInstance();
		tooth2.setCreatedDate(cal2.getTime());
		tooth2.setToothNum(5);

		Timeline timelineTooth2 = new Timeline();
		timelineTooth2.setTooth(tooth2);
		timelineTooth2.setCreatedDate(cal2.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(cal2.getTime());

		gotoFragment();

	}

	public void purchaseOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Purchase purchase1 = new Purchase();
		Calendar purchaseCal1 = Calendar.getInstance();
		purchase1.setCreatedDate(purchaseCal1.getTime());
		purchase1.setPurchaseName("Wakodo сүү");
		purchase1.setPurchaseAmount(1);
		purchase1.setPurchasePrice(46000);

		Timeline timelinePurchase1 = new Timeline();
		timelinePurchase1.setPurchase(purchase1);
		timelinePurchase1.setCreatedDate(purchaseCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelinePurchase1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateRange.setEndDate(purchaseCal1.getTime());

		gotoFragment();

	}
	
	private void gotoAddFragment(Fragment fragment){
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
		getActionBar().hide();

	}


}