package com.mercy.happybaby;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mikephil.charting.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.activeoperation.ActiveOperationFragment;
import com.mercy.happybaby.ui.changediaper.ChangeDiaperFragment;
import com.mercy.happybaby.ui.chart.ChartFragment;
import com.mercy.happybaby.ui.health.HealthFragment;
import com.mercy.happybaby.ui.helpcenter.HelpCenterFragment;
import com.mercy.happybaby.ui.learn.LearnFragment;
import com.mercy.happybaby.ui.meal.MealFragment;
import com.mercy.happybaby.ui.others.AboutFragment;
import com.mercy.happybaby.ui.others.ProfileFragment;
import com.mercy.happybaby.ui.purchase.PurchaseFragment;
import com.mercy.happybaby.ui.timeline.TimelineCreateFragment;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.ui.tooth.ToothFragment;
import com.mercy.happybaby.utils.crouton.Crouton;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

public class MainActivity extends Activity {
	String LOG_TAG = MainActivity.class.getName();

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mTitles;

	private DatabaseHelper databaseHelper = null;
	private Dao<Timeline, Integer> timelineDAO;

	private CroutonMessage crtnMsg = null;

	private Context context;

	private int defaultFragment = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		Utils.init(getResources());
		crtnMsg = new CroutonMessage(this);
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
			fragment = new HelpCenterFragment();
			break;
		case 10:
			fragment = new ProfileFragment();
			break;
		case 11:
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
			mDrawerLayout.closeDrawer(mDrawerList);
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

	public void addBreastOnClick(View v) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				context.getResources().getString(R.string.success));
		Breast breast = new Breast();
		breast.setRight(getRandomBoolean());
		Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.MONTH, cal.getTime().getMonth()-1);
		breast.setBreastTime(cal.getTime());
		breast.setCreatedDate(cal.getTime());
		Timeline timeline = new Timeline();
		timeline.setCreatedDate(Calendar.getInstance().getTime());
		timeline.setBreast(breast);
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timeline);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			fragment = new ProfileFragment();
			break;
		case 11:
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
		setTitle(mTitles[0]);
		mDrawerList.setItemChecked(0, true);
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
}