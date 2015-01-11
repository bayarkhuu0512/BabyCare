package com.apu.maamuu.ui.health;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.apu.maamuu.db.DatabaseHelper;
import com.apu.maamuu.entities.Drink;
import com.apu.maamuu.entities.Feed;
import com.apu.maamuu.entities.Timeline;
import com.apu.maamuu.entities.Vitamin;
import com.apu.maamuu.ui.timeline.TimelineFragment;
import com.apu.maamuu.utils.Constants;
import com.apu.maamuu.utils.DateRangeInstance;
import com.apu.maamuu.utils.crouton.CroutonMessage;
import com.apu.maamuu.utils.crouton.Style;
import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.apu.maamuu.R;

import dreamers.graphics.RippleDrawable;

public class AddVitaminFragment extends Fragment  implements
TimePickerDialogFragment.TimePickerDialogHandler {
	String LOG_TAG = AddVitaminFragment.class.getName();

	private TextView timeDrink;
	private ListView vitaminList;
	private String[] vitaminNames;

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
		View root = inflater.inflate(R.layout.add_vitamin, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeDrink = (TextView) root.findViewById(R.id.timeVitamin);
		timeDrink.setTypeface(roboto_light);
		timeDrink.setText(Constants.timeFormat.format(cal.getTime()) + "");
		timeDrink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "timeFeed");
			}
		});

		vitaminNames = getResources().getStringArray(R.array.vitamin_array);
		vitaminList = (ListView) root.findViewById(R.id.vitaminList);
		vitaminList.setAdapter(new VitaminListAdapter(getActivity(), vitaminNames));

		ImageButton close = (ImageButton) root.findViewById(R.id.close);
		RippleDrawable.createRipple(close,
				getResources().getColor(android.R.color.white));
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
				getResources().getColor(android.R.color.white));
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
		//dateRange.setEndDate(vitaminCal1.getTime());
		getActivity().setTitle(getActivity().getTitle());
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

	public class VitaminListAdapter extends ArrayAdapter<String> {

		public VitaminListAdapter(Context context, String[] vitaminNames) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.add_vitamin_item, vitaminNames);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.add_vitamin_item, parent,
					false);
			TextView vitaminName = (TextView) convertView
					.findViewById(R.id.vitaminName);
			ImageView vitaminIcon = (ImageView) convertView
					.findViewById(R.id.vitaminIcon);
			vitaminName.setText(vitaminNames[position] + "");
			return convertView;
		}
	}
	@Override
    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
		Log.d(LOG_TAG, hourOfDay + ":" + minute);
    }
}
