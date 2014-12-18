package com.mercy.happybaby.ui.meal;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.Feed;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.changediaper.AddChangeDiaperFragment;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddFeedFragment extends Fragment  implements
TimePickerDialogFragment.TimePickerDialogHandler {
	String LOG_TAG = AddFeedFragment.class.getName();

	private TextView timeFeed;
	private EditText feedML;
	private ListView feedList;
	private String[] feedNames;

	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	Calendar cal = Calendar.getInstance();
	private Typeface roboto_light;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_feed, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeFeed = (TextView) root.findViewById(R.id.timeFeed);
		timeFeed.setTypeface(roboto_light);
		timeFeed.setText(Constants.timeFormat.format(cal.getTime()) + "");
		timeFeed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddFeed", "timeFeed");
			}
		});

		feedML = (EditText) root.findViewById(R.id.feedML);
		feedML.setTypeface(roboto_light);
		feedML.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddFeed", "feedML");
			}
		});

		feedNames = getResources().getStringArray(R.array.feed_array);
		feedList = (ListView) root.findViewById(R.id.feedList);
		feedList.setAdapter(new FeedListAdapter(getActivity(), feedNames));

		ImageButton close = (ImageButton) root.findViewById(R.id.close);
		RippleDrawable.createRipple(close,
				getResources().getColor(android.R.color.white));
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "close");
				gotoTimeline();
			}
		});
		ImageButton save = (ImageButton) root.findViewById(R.id.save);
		RippleDrawable.createRipple(save,
				getResources().getColor(android.R.color.white));
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddFeed", "save");
				// if (isSelectedRight) {
				// addBreast(true);
				// } else if (isSelectedLeft) {
				// addBreast(false);
				// } else {
				// crtnMsg.hide();
				// crtnMsg.showCrouton(Style.CONFIRM, getActivity()
				// .getResources().getString(R.string.pleaseselect));
				// }
			}
		});

		return root;
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	private void addFeed() {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
		Feed feed2 = new Feed();
		Calendar feedCal2 = Calendar.getInstance();
		feed2.setCreatedDate(feedCal2.getTime());
		feed2.setAmount(3);
		feed2.setFeedName("Жимс");

		Timeline timelineFeed2 = new Timeline();
		timelineFeed2.setFeed(feed2);
		timelineFeed2.setCreatedDate(feedCal2.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineFeed2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateRangeInstance.getInstance().setEndDate(feedCal2.getTime());
		gotoTimeline();
	}

	private void gotoTimeline() {
		TimelineFragment fragment = new TimelineFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
		getActivity().setTitle(getActivity().getTitle());
		getActivity().getActionBar().show();
	}

	public class FeedListAdapter extends ArrayAdapter<String> {

		public FeedListAdapter(Context context, String[] feedNames) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.add_feed_item, feedNames);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.add_feed_item, parent,
					false);
			TextView feedName = (TextView) convertView
					.findViewById(R.id.feedName);
			ImageView feedIcon = (ImageView) convertView
					.findViewById(R.id.feedIcon);
			feedName.setText(feedNames[position] + "");
			return convertView;

		}
	}
	   @Override
	    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
			Log.d(LOG_TAG, hourOfDay + ":" + minute);
	    }
}
