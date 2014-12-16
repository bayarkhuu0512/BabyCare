package com.mercy.happybaby.ui.learn;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Learn;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddLearnFragment extends Fragment {
	String LOG_TAG = AddLearnFragment.class.getName();

	private TextView dateLearn;
	private ListView learnList;
	private String[] learnNames;

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
		View root = inflater.inflate(R.layout.add_learn, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateLearn = (TextView) root.findViewById(R.id.dateLearn);
		dateLearn.setTypeface(roboto_light);
		dateLearn.setText(Constants.timeFormat.format(cal.getTime()) + "");
		dateLearn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "timeFeed");
			}
		});

		learnNames = getResources().getStringArray(R.array.learn_array);
		learnList = (ListView) root.findViewById(R.id.learnList);
		learnList.setAdapter(new LearnListAdapter(getActivity(), learnNames));

		ImageButton close = (ImageButton) root.findViewById(R.id.close);
		RippleDrawable.createRipple(close,
				getResources().getColor(R.color.mainColor));
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "close");
				gotoTimeline();
			}
		});
		ImageButton save = (ImageButton) root.findViewById(R.id.save);
		RippleDrawable.createRipple(save,
				getResources().getColor(R.color.mainColor));
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "save");
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
	
	private void addVitamin(boolean isRight) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
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
	//	dateRange.setEndDate(learnCal4.getTime());
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
		getActivity().getActionBar().show();
	}

	public class LearnListAdapter extends ArrayAdapter<String> {

		public LearnListAdapter(Context context, String[] learnNames) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.add_learn_item, learnNames);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.add_learn_item, parent,
					false);
			TextView learnName = (TextView) convertView
					.findViewById(R.id.learnName);
			ImageView learnIcon = (ImageView) convertView
					.findViewById(R.id.learnIcon);
			learnName.setText(learnNames[position] + "");
			return convertView;

		}
	}
}
