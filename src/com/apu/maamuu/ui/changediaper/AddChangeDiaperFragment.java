package com.apu.maamuu.ui.changediaper;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apu.maamuu.db.DatabaseHelper;
import com.apu.maamuu.entities.ChangeDiaper;
import com.apu.maamuu.entities.Timeline;
import com.apu.maamuu.ui.timeline.TimelineFragment;
import com.apu.maamuu.utils.Constants;
import com.apu.maamuu.utils.DateRangeInstance;
import com.apu.maamuu.utils.crouton.CroutonMessage;
import com.apu.maamuu.utils.crouton.Style;
import com.doomonafireball.betterpickers.timepicker.TimePickerBuilder;
import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.apu.maamuu.R;

import dreamers.graphics.RippleDrawable;

public class AddChangeDiaperFragment extends Fragment implements
		TimePickerDialogFragment.TimePickerDialogHandler {
	String LOG_TAG = AddChangeDiaperFragment.class.getName();

	private TextView timeChangeDiaper;
	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	private boolean isSelectedLeft = false, isSelectedRight = false;
	Calendar cal = Calendar.getInstance();
	private Typeface roboto_light;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);

		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_changediaper, container,
				false);
		crtnMsg = new CroutonMessage(getActivity());

		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timeChangeDiaper = (TextView) root.findViewById(R.id.timeChangeDiaper);
		timeChangeDiaper.setTypeface(roboto_light);
		timeChangeDiaper.setText(Constants.timeFormat.format(cal.getTime())
				+ "");
		timeChangeDiaper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "timeBreast");
				Calendar c = Calendar.getInstance();
				TimePickerBuilder tpb = new TimePickerBuilder()
						.setFragmentManager(getFragmentManager()).setStyleResId(
								R.style.BetterPickersDialogFragment);
				tpb.show();
			}
		});

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
				Log.d("AddBreast", "save");
				if (isSelectedRight) {
					addBreast(true);
				} else if (isSelectedLeft) {
					addBreast(false);
				} else {
					crtnMsg.hide();
					crtnMsg.showCrouton(Style.CONFIRM, getActivity()
							.getResources().getString(R.string.pleaseselect));
				}
			}
		});

		return root;
	}

	// public void onDateSet(CalendarDatePickerDialog dialog, int year,
	// int monthOfYear, int dayOfMonth) {
	// // TODO Auto-generated method stub
	// Log.d(LOG_TAG, "Year: " + year + "\nMonth: " + monthOfYear + "\nDay: "
	// + dayOfMonth);
	// Calendar c = Calendar.getInstance();
	// c.set(year, monthOfYear, dayOfMonth);
	// // dateRange.setStartDate(c.getTime());
	// }

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	private void addBreast(boolean isRight) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
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
		DateRangeInstance.getInstance().setEndDate(cal.getTime());
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

	@Override
	public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
		Log.d(LOG_TAG, hourOfDay + ":" + minute);
	}
}
