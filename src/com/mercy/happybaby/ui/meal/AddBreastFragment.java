package com.mercy.happybaby.ui.meal;

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
import android.widget.ImageButton;
import android.widget.TextView;

import com.doomonafireball.betterpickers.timepicker.TimePickerBuilder;
import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.MainActivity;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddBreastFragment extends Fragment implements
		TimePickerDialogFragment.TimePickerDialogHandler {
	String LOG_TAG = AddBreastFragment.class.getName();

	private Button leftBreast, rightBreast;
	private TextView timeBreast;
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
		View root = inflater.inflate(R.layout.add_breast, container, false);
		crtnMsg = new CroutonMessage(getActivity());

		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timeBreast = (TextView) root.findViewById(R.id.timeBreast);
		timeBreast.setTypeface(roboto_light);
		timeBreast.setText(Constants.timeFormat.format(cal.getTime()) + "");
		timeBreast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "timeBreast");
//				TimePickerBuilder tpb = new TimePickerBuilder()
//						.setFragmentManager(getFragmentManager())
//						.setStyleResId(R.style.BetterPickersDialogFragment);
//				tpb.show();
			}
		});

		leftBreast = (Button) root.findViewById(R.id.breastLeft);
		leftBreast.setTypeface(roboto_light);
		leftBreast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "left");
				if (isSelectedLeft) {
					leftBreast.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.btn_breast_oval));
					leftBreast.setTextColor(Color.WHITE);
					isSelectedLeft = false;
				} else {
					leftBreast.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.btn_breast_oval_selected));
					leftBreast.setTextColor(getActivity().getResources()
							.getColor(R.color.entity_breast));
					isSelectedLeft = true;

					isSelectedRight = false;
					rightBreast.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.btn_breast_oval));
					rightBreast.setTextColor(Color.WHITE);
				}
				// addBreast(false);
			}
		});

		rightBreast = (Button) root.findViewById(R.id.breastRight);
		rightBreast.setTypeface(roboto_light);
		rightBreast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("AddBreast", "right");
				if (isSelectedRight) {
					rightBreast.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.btn_breast_oval));
					rightBreast.setTextColor(Color.WHITE);
					isSelectedRight = false;

				} else {
					rightBreast.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.btn_breast_oval_selected));
					rightBreast.setTextColor(getActivity().getResources()
							.getColor(R.color.entity_breast));
					isSelectedRight = true;

					isSelectedLeft = false;
					leftBreast.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.btn_breast_oval));
					leftBreast.setTextColor(Color.WHITE);

				}
				// addBreast(true);
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
		Breast breast = new Breast();
		breast.setRight(isRight);
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
