package com.mercy.happybaby.ui.health;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Hospital;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddHospitalFragment extends Fragment implements
		CalendarDatePickerDialog.OnDateSetListener {
	String LOG_TAG = AddHospitalFragment.class.getName();

	private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

	private TextView timeHospital;
	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	private Calendar cal = Calendar.getInstance();
	private Typeface roboto_light;
	private TextView hospitalNameLabel;
	private TextView doctorNameLabel;
	private TextView painLabel;
	private TextView diagnosisLabel;
	private TextView healingLabel;

	private EditText hospitalName;
	private EditText doctorName;
	private EditText pain;
	private EditText diagnosis;
	private EditText healing;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);

		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_hospital, container, false);
		crtnMsg = new CroutonMessage(getActivity());

		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timeHospital = (TextView) root.findViewById(R.id.timeHospital);
		timeHospital.setTypeface(roboto_light);
		timeHospital.setText(Constants.dateFormat.format(cal.getTime()) + "");
		timeHospital.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "timeBreast");
				FragmentManager fm = getFragmentManager();
				Calendar c = Calendar.getInstance();
				CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
						.newInstance(AddHospitalFragment.this,
								c.get(Calendar.YEAR), c.get(Calendar.MONTH),
								c.get(Calendar.DAY_OF_MONTH));
				calendarDatePickerDialog.show(fm, FRAG_TAG_DATE_PICKER);
			}
		});

		hospitalNameLabel = (TextView) root
				.findViewById(R.id.hospitalNameLabel);
		hospitalNameLabel.setTypeface(roboto_light);

		doctorNameLabel = (TextView) root.findViewById(R.id.doctorNameLabel);
		doctorNameLabel.setTypeface(roboto_light);

		painLabel = (TextView) root.findViewById(R.id.painLabel);
		painLabel.setTypeface(roboto_light);

		diagnosisLabel = (TextView) root.findViewById(R.id.diagnosisLabel);
		diagnosisLabel.setTypeface(roboto_light);

		healingLabel = (TextView) root.findViewById(R.id.healingLabel);
		healingLabel.setTypeface(roboto_light);

		hospitalName = (EditText) root.findViewById(R.id.hospitalName);
		hospitalName.setTypeface(roboto_light);

		doctorName = (EditText) root.findViewById(R.id.doctorName);
		doctorName.setTypeface(roboto_light);

		pain = (EditText) root.findViewById(R.id.pain);
		pain.setTypeface(roboto_light);

		diagnosis = (EditText) root.findViewById(R.id.diagnosis);
		diagnosis.setTypeface(roboto_light);

		healing = (EditText) root.findViewById(R.id.healing);
		healing.setTypeface(roboto_light);

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
				if (hospitalName.getText().length() == 0
						&& doctorName.getText().length() == 0
						&& pain.getText().length() == 0
						&& healing.getText().length() == 0) {
					crtnMsg.hide();
					crtnMsg.showCrouton(Style.CONFIRM, getActivity()
							.getResources().getString(R.string.pleaseselect));
				} else {
					addHospital();
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

	private void addHospital() {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
		Hospital hospital1 = new Hospital();
		Calendar hospitalCal1 = Calendar.getInstance();
		hospital1.setCreatedDate(hospitalCal1.getTime());
		hospital1.setDiagnosis(diagnosis.getText().toString());
		hospital1.setPainName(pain.getText().toString());
		hospital1.setHealing(healing.getText().toString());
		hospital1.setDoctorName(doctorName.getText().toString());
		hospital1.setHospitalName(hospitalName.getText().toString());

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
