package com.mercy.happybaby.ui.health;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.Hospital;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

public class AddHospitalFragment extends Fragment {
	Button leftBreast, rightBreast;
	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	DateRangeInstance dateRange = DateRangeInstance.getInstance();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_hospital, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button leftBreast = (Button) root.findViewById(R.id.breastLeft);
		leftBreast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "left");
				addHospital(false);
			}
		});

		Button rightBreast = (Button) root.findViewById(R.id.breastRight);
		rightBreast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "right");
				addHospital(true);
			}
		});

		Button close = (Button) root.findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "close");
				gotoTimeline();
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

	private void addHospital(boolean isRight) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
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
		gotoTimeline();
	}
	
	private void gotoTimeline(){
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
}