package com.mercy.babycare;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.utils.Utils;
import com.idunnololz.widgets.AnimatedExpandableListView;
import com.idunnololz.widgets.AnimatedExpandableListView.AnimatedExpandableListAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.babycare.db.DatabaseHelper;
import com.mercy.babycare.entities.Breast;
import com.mercy.babycare.entities.Timeline;
import com.mercy.babycare.ui.activeoperation.ActiveOperationFragment;
import com.mercy.babycare.ui.breast.BreastFragment;
import com.mercy.babycare.ui.changediaper.ChangeDiaperFragment;
import com.mercy.babycare.ui.chart.ChartFragment;
import com.mercy.babycare.ui.feed.FeedFragment;
import com.mercy.babycare.ui.health.HealthFragment;
import com.mercy.babycare.ui.helpcenter.HelpCenterFragment;
import com.mercy.babycare.ui.learn.LearnFragment;
import com.mercy.babycare.ui.others.AboutFragment;
import com.mercy.babycare.ui.others.ProfileFragment;
import com.mercy.babycare.ui.purchase.PurchaseFragment;
import com.mercy.babycare.ui.timeline.TimelineCreateFragment;
import com.mercy.babycare.ui.timeline.TimelineFragment;
import com.mercy.babycare.ui.tooth.ToothFragment;
import com.mercy.babycare.utils.crouton.Crouton;
import com.mercy.babycare.utils.crouton.CroutonMessage;
import com.mercy.babycare.utils.crouton.Style;

public class MainActivity extends Activity {
	String LOG_TAG = MainActivity.class.getName();

	private DrawerLayout mDrawerLayout;
	private AnimatedExpandableListView listView;

	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private LeftNavMenuAdapter adapter;

	private DatabaseHelper databaseHelper = null;
	private Dao<Timeline, Integer> timelineDAO;

	private CroutonMessage crtnMsg = null;

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		Utils.init(getResources());
		crtnMsg = new CroutonMessage(this);

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
		listView = (AnimatedExpandableListView) findViewById(R.id.listView);

		// enable ActionBar app icon to behave as action to toggle nav drawer
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

		adapter = new LeftNavMenuAdapter(this);
		adapter.setData(fillLeftMenuItems());

		listView = (AnimatedExpandableListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		listView.setOnChildClickListener(new DrawerItemClickListener());
		listView.expandGroupWithAnimation(1);
		listView.expandGroupWithAnimation(2);

		listView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (groupPosition == 2 || groupPosition == 4) {
					if (listView.isGroupExpanded(groupPosition)) {
						listView.collapseGroupWithAnimation(groupPosition);
					} else {
						listView.expandGroupWithAnimation(groupPosition);
					}
				} else {
					selectItem(groupPosition);
				}
				return true;
			}
		});

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(listView);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_websearch:
			// create intent to perform web search for this planet
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
			// catch event that there's no activity to handle intent
			if (intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			} else {
				Toast.makeText(this, R.string.app_not_available,
						Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private List<GroupItem> fillLeftMenuItems() {
		List<GroupItem> items = new ArrayList<GroupItem>();
		GroupItem timeline_menu = new GroupItem();
		timeline_menu.title = getResources().getString(R.string.timeline_menu);
		items.add(timeline_menu);

		// Chart
		GroupItem chart_menu = new GroupItem();
		chart_menu.title = getResources().getString(R.string.chart_menu);
		items.add(chart_menu);

		// Feed
		GroupItem feed_menu = new GroupItem();
		feed_menu.title = getResources().getString(R.string.feed_menu);
		items.add(feed_menu);

		Item feed_menu_breast = new Item();
		feed_menu_breast.title = getResources().getString(
				R.string.feed_menu_breast);
		feed_menu.items.add(feed_menu_breast);

		Item feed_menu_drink = new Item();
		feed_menu_drink.title = getResources().getString(
				R.string.feed_menu_drink);
		feed_menu.items.add(feed_menu_drink);

		Item feed_menu_formula = new Item();
		feed_menu_formula.title = getResources().getString(
				R.string.feed_menu_formula);
		feed_menu.items.add(feed_menu_formula);

		Item feed_menu_extrafood = new Item();
		feed_menu_extrafood.title = getResources().getString(
				R.string.feed_menu_extrafood);
		feed_menu.items.add(feed_menu_extrafood);

		// ChangeDiaper
		GroupItem diaperchange_menu = new GroupItem();
		diaperchange_menu.title = getResources().getString(
				R.string.diaperchange_menu);
		items.add(diaperchange_menu);

		// Health
		GroupItem health_menu = new GroupItem();
		health_menu.title = getResources().getString(R.string.health_menu);
		items.add(health_menu);

		Item health_menu_vaccine = new Item();
		health_menu_vaccine.title = getResources().getString(
				R.string.health_menu_vaccine);
		health_menu.items.add(health_menu_vaccine);

		Item health_menu_vitamin = new Item();
		health_menu_vitamin.title = getResources().getString(
				R.string.health_menu_vitamin);
		health_menu.items.add(health_menu_vitamin);

		Item health_menu_medcheck = new Item();
		health_menu_medcheck.title = getResources().getString(
				R.string.health_menu_medcheck);
		health_menu.items.add(health_menu_medcheck);

		Item health_menu_takemedicine = new Item();
		health_menu_takemedicine.title = getResources().getString(
				R.string.health_menu_takemedicine);
		health_menu.items.add(health_menu_takemedicine);

		GroupItem activeoperation_menu = new GroupItem();
		activeoperation_menu.title = getResources().getString(
				R.string.activeoperation_menu);
		items.add(activeoperation_menu);

		GroupItem learn_menu = new GroupItem();
		learn_menu.title = getResources().getString(R.string.learn_menu);
		items.add(learn_menu);

		GroupItem purchase_menu = new GroupItem();
		purchase_menu.title = getResources().getString(R.string.purchase_menu);
		items.add(purchase_menu);

		GroupItem tooth_menu = new GroupItem();
		tooth_menu.title = getResources().getString(R.string.tooth_menu);
		items.add(tooth_menu);

		GroupItem helpcenter_menu = new GroupItem();
		helpcenter_menu.title = getResources().getString(
				R.string.helpcenter_menu);
		items.add(helpcenter_menu);

		GroupItem profile_menu = new GroupItem();
		profile_menu.title = getResources().getString(R.string.profile_menu);
		items.add(profile_menu);

		GroupItem settings_menu = new GroupItem();
		settings_menu.title = getResources().getString(R.string.settings_menu);
		items.add(settings_menu);

		GroupItem about_menu = new GroupItem();
		about_menu.title = getResources().getString(R.string.about_menu);
		items.add(about_menu);

		return items;
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements OnChildClickListener {
		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			selectItem(groupPosition, childPosition);
			return false;
		}
	}

	private void selectItem(int groupPos) {
		Fragment fragment = null;
		switch (groupPos) {
		case 0:
			fragment = new TimelineFragment();
			setTitle(getResources().getString(R.string.timeline_menu));
			listView.setItemChecked(0, true);
			break;

		case 1:
			fragment = new ChartFragment();
			setTitle(getResources().getString(R.string.chart_menu));
			listView.setItemChecked(1, true);
			break;

		case 3:
			fragment = new ChangeDiaperFragment();
			setTitle(getResources().getString(R.string.diaperchange_menu));
			listView.setItemChecked(3, true);
			break;

		case 5:
			fragment = new ActiveOperationFragment();
			setTitle(getResources().getString(R.string.activeoperation_menu));
			listView.setItemChecked(5, true);
			break;
		case 6:
			fragment = new LearnFragment();
			setTitle(getResources().getString(R.string.learn_menu));
			listView.setItemChecked(6, true);
			break;
		case 7:
			fragment = new PurchaseFragment();
			setTitle(getResources().getString(R.string.purchase_menu));
			listView.setItemChecked(7, true);
			break;
		case 8:
			fragment = new ToothFragment();
			setTitle(getResources().getString(R.string.tooth_menu));
			listView.setItemChecked(8, true);
			break;
		case 9:
			fragment = new HelpCenterFragment();
			setTitle(getResources().getString(R.string.helpcenter_menu));
			listView.setItemChecked(9, true);
			break;
		case 10:
			fragment = new ProfileFragment();
			setTitle(getResources().getString(R.string.profile_menu));
			listView.setItemChecked(10, true);
			break;
		case 11:
			fragment = new AboutFragment();
			setTitle(getResources().getString(R.string.about_menu));
			listView.setItemChecked(11, true);
			break;
		default:
			fragment = new TimelineFragment();
			setTitle(getResources().getString(R.string.timeline_menu));
			listView.setItemChecked(0, true);
			break;
		}
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		mDrawerLayout.closeDrawer(listView);

	}

	private void selectItem(int groupPos, int childPos) {
		Fragment fragment = null;
		if (groupPos == 2) {
			if (childPos == 0) {
				fragment = new BreastFragment();
				setTitle(getResources().getString(R.string.feed_menu_breast));
				listView.setItemChecked(1, true);
			}
			if (childPos == 1) {
				fragment = new FeedFragment();
			}
			if (childPos == 2) {
				fragment = new FeedFragment();
			}
			if (childPos == 3) {
				fragment = new FeedFragment();
			}
		} else if (groupPos == 4) {
			if (childPos == 0) {
				fragment = new HealthFragment();
			}
			if (childPos == 1) {
				fragment = new HealthFragment();
			}
			if (childPos == 2) {
				fragment = new HealthFragment();
			}
			if (childPos == 3) {
				fragment = new HealthFragment();
			}
		}
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		mDrawerLayout.closeDrawer(listView);
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

	private static class GroupItem {
		String title;
		List<Item> items = new ArrayList<Item>();
	}

	private static class Item {
		String title;
		String hint;
	}

	private static class ItemHolder {
		TextView title;
	}

	private static class Group {
		TextView title;
	}

	/**
	 * Adapter for our list of {@link GroupItem}s.
	 */
	private class LeftNavMenuAdapter extends AnimatedExpandableListAdapter {
		private LayoutInflater inflater;

		private List<GroupItem> items;

		public LeftNavMenuAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void setData(List<GroupItem> items) {
			this.items = items;
		}

		@Override
		public Item getChild(int groupPosition, int childPosition) {
			return items.get(groupPosition).items.get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getRealChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ItemHolder holder;
			Item item = getChild(groupPosition, childPosition);
			if (convertView == null) {
				holder = new ItemHolder();
				convertView = inflater.inflate(R.layout.nav_list_item, parent,
						false);
				holder.title = (TextView) convertView
						.findViewById(R.id.textTitle);
				convertView.setTag(holder);
			} else {
				holder = (ItemHolder) convertView.getTag();
			}

			holder.title.setText(item.title);

			return convertView;
		}

		@Override
		public int getRealChildrenCount(int groupPosition) {
			return items.get(groupPosition).items.size();
		}

		@Override
		public GroupItem getGroup(int groupPosition) {
			return items.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return items.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			Group holder;
			GroupItem item = getGroup(groupPosition);
			if (convertView == null) {
				holder = new Group();
				convertView = inflater.inflate(R.layout.nav_group_item, parent,
						false);
				holder.title = (TextView) convertView
						.findViewById(R.id.textTitle);
				convertView.setTag(holder);
			} else {
				holder = (Group) convertView.getTag();
			}

			holder.title.setText(item.title);

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}
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

		gotoTimeine();

	}

	public void addBreastOnClick(View v) {
		crtnMsg.showCrouton(Style.INFO, context.getResources().getString(R.string.success));
		Breast breast = new Breast();
		breast.setRight(getRandomBoolean());
		Calendar cal = Calendar.getInstance();
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
		gotoTimeine();
	}

	// tmp use for Breast left or right;
	private Random rnd = new Random();

	public boolean getRandomBoolean() {
		return rnd.nextBoolean();
	}

	private void gotoTimeine() {
		Fragment fragment = new TimelineFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
		setTitle(getResources().getString(R.string.timeline_menu));
		listView.setItemChecked(0, true);
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