package com.mercy.happybaby.ui.health;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.MainActivity;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.Growth;
import com.mercy.happybaby.entities.Hospital;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddMedcheckFragment extends Fragment implements
		CalendarDatePickerDialog.OnDateSetListener {
	String LOG_TAG = AddMedcheckFragment.class.getName();

	private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

	private TextView timeMedcheck;
	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	Calendar cal = Calendar.getInstance();
	private Typeface roboto_light;
	TextView headCircLabel;
	TextView headCircSm;
	TextView weightLabel;
	TextView weightSm;
	TextView heightLabel;
	TextView heightSm;
	EditText headCirc;
	EditText weight;
	EditText height;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);

		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_medcheck, container, false);
		crtnMsg = new CroutonMessage(getActivity());

		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timeMedcheck = (TextView) root.findViewById(R.id.timeMedcheck);
		timeMedcheck.setTypeface(roboto_light);
		timeMedcheck.setText(Constants.dateFormat.format(cal.getTime()) + "");

		headCircLabel = (TextView) root.findViewById(R.id.headCircLabel);
		headCircLabel.setTypeface(roboto_light);

		headCircSm = (TextView) root.findViewById(R.id.headCircSm);
		headCircSm.setTypeface(roboto_light);

		weightLabel = (TextView) root.findViewById(R.id.weightLabel);
		weightLabel.setTypeface(roboto_light);

		weightSm = (TextView) root.findViewById(R.id.weightSm);
		weightSm.setTypeface(roboto_light);

		heightLabel = (TextView) root.findViewById(R.id.heightLabel);
		heightLabel.setTypeface(roboto_light);

		heightSm = (TextView) root.findViewById(R.id.heightSm);
		heightSm.setTypeface(roboto_light);

		timeMedcheck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "timeBreast");
				FragmentManager fm = getFragmentManager();
				Calendar c = Calendar.getInstance();
				CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
						.newInstance(AddMedcheckFragment.this,
								c.get(Calendar.YEAR), c.get(Calendar.MONTH),
								c.get(Calendar.DAY_OF_MONTH));
				calendarDatePickerDialog.show(fm, FRAG_TAG_DATE_PICKER);
			}
		});

		headCirc = (EditText) root.findViewById(R.id.headCirc);
		weight = (EditText) root.findViewById(R.id.weight);
		height = (EditText) root.findViewById(R.id.height);

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
				Log.d(LOG_TAG, "save");
				if (headCirc.getText().length() == 0 && weight.getText().length() == 0
						&& height.getText().length() == 0) {
					crtnMsg.hide();
					crtnMsg.showCrouton(Style.CONFIRM, getActivity()
							.getResources().getString(R.string.pleaseselect));
				} else {
					addMedcheck();
				}
			}

		});

		return root;
	}

	@Override
	public void onDateSet(CalendarDatePickerDialog dialog, int year,
			int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "Year: " + year + "\nMonth: " + monthOfYear + "\nDay: "
				+ dayOfMonth);
		Calendar c = Calendar.getInstance();
		c.set(year, monthOfYear, dayOfMonth);
		// dateRange.setStartDate(c.getTime());
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

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	private void addMedcheck() {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));

		Growth growth = new Growth();
		Calendar growthCal = Calendar.getInstance();
		growth.setCreatedDate(growthCal.getTime());
		growth.setHeight(Float.parseFloat(height.getText().toString()));
		growth.setWeight(Float.parseFloat(weight.getText().toString()));
		growth.setHeadCirc(Float.parseFloat(headCirc.getText().toString()));

		Timeline growthTimeline = new Timeline();
		growthTimeline.setGrowth(growth);
		growthTimeline.setCreatedDate(growthCal.getTime());

		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline);
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
}
