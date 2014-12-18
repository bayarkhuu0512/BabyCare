package com.mercy.happybaby.ui.health;

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

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.entities.Vaccine;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddVaccineFragment extends Fragment implements
		CalendarDatePickerDialog.OnDateSetListener {
	String LOG_TAG = AddVaccineFragment.class.getName();

	private TextView dateVaccine;
	private ListView vaccineList;
	private String[] vaccineNames;

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
		View root = inflater.inflate(R.layout.add_vaccine, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateVaccine = (TextView) root.findViewById(R.id.dateVaccine);
		dateVaccine.setTypeface(roboto_light);
		dateVaccine.setText(Constants.dateFormat.format(cal.getTime()) + "");
		dateVaccine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "timeFeed");
			}
		});

		vaccineNames = getResources().getStringArray(R.array.vaccine_array);
		vaccineList = (ListView) root.findViewById(R.id.vaccineList);
		vaccineList.setAdapter(new VaccineListAdapter(getActivity(),
				vaccineNames));

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

	private void addVaccine(boolean isRight) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
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

		// dateRange.setEndDate(vaccineCal1.getTime());
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

	public class VaccineListAdapter extends ArrayAdapter<String> {

		public VaccineListAdapter(Context context, String[] vaccineNames) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.add_vaccine_item, vaccineNames);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.add_vaccine_item, parent,
					false);
			TextView vaccineName = (TextView) convertView
					.findViewById(R.id.vaccineName);
			ImageView vaccineIcon = (ImageView) convertView
					.findViewById(R.id.vaccineIcon);
			vaccineName.setText(vaccineNames[position] + "");
			return convertView;

		}
	}

	@Override
	public void onDateSet(CalendarDatePickerDialog dialog, int year,
			int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "Year: " + year + "\nMonth: " + monthOfYear + "\nDay: "
				+ dayOfMonth);
	}
}
