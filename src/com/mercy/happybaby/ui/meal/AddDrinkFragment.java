package com.mercy.happybaby.ui.meal;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Drink;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddDrinkFragment extends Fragment implements
		TimePickerDialogFragment.TimePickerDialogHandler, Constants {
	String LOG_TAG = AddDrinkFragment.class.getName();

	private TextView timeDrink;
	private EditText drinkML;
	private ListView drinkList;
	private String[] drinkNames;

	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	Calendar cal = Calendar.getInstance();
	private Typeface roboto_light;
	private DrinkListAdapter drinkListAdapter;
	private int selectedRow = ITEMNOTSELECTED;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_drink, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeDrink = (TextView) root.findViewById(R.id.timeDrink);
		timeDrink.setTypeface(roboto_light);
		timeDrink.setText(Constants.timeFormat.format(cal.getTime()) + "");
		timeDrink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "timeFeed");
			}
		});

		drinkML = (EditText) root.findViewById(R.id.drinkML);
		drinkML.setTypeface(roboto_light);

		drinkNames = getResources().getStringArray(R.array.drink_array);
		drinkList = (ListView) root.findViewById(R.id.drinkList);
		drinkListAdapter = new DrinkListAdapter(getActivity(), drinkNames);
		drinkList.setAdapter(drinkListAdapter);
		drinkList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "OnItemClickListener " + position);
				selectedRow = position;
				drinkListAdapter.notifyDataSetChanged();
			}

		});

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
				if (selectedRow != ITEMNOTSELECTED) {
					addDrink();
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

	private void addDrink() {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
		Drink drink1 = new Drink();
		Calendar drinkCal1 = Calendar.getInstance();
		drink1.setCreatedDate(drinkCal1.getTime());
		drink1.setDrinkName(drinkNames[selectedRow]);
		drink1.setMl(Integer.parseInt(drinkML.getText().toString()));

		Timeline timelineDrink1 = new Timeline();
		timelineDrink1.setDrink(drink1);
		timelineDrink1.setCreatedDate(drinkCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineDrink1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateRangeInstance.getInstance().setEndDate(drinkCal1.getTime());
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

	public class DrinkListAdapter extends ArrayAdapter<String> {

		public DrinkListAdapter(Context context, String[] feedNames) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.add_drink_item, feedNames);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.add_drink_item, parent,
					false);
			TextView drinkName = (TextView) convertView
					.findViewById(R.id.drinkName);
			drinkName.setId(position);
			drinkName.setText(drinkNames[position] + "");
			drinkName.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					selectedRow = v.getId();
				}
			});
			ImageView drinkIcon = (ImageView) convertView
					.findViewById(R.id.drinkIcon);
			drinkIcon.setVisibility(View.INVISIBLE);
			if (selectedRow == position) {
				Log.d(LOG_TAG, "selected Row " + selectedRow);
				drinkIcon.setVisibility(View.VISIBLE);
			}
			return convertView;

		}
	}

	@Override
	public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
		Log.d(LOG_TAG, hourOfDay + ":" + minute);
	}
}
