package com.mercy.happybaby.ui.others;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mercy.happybaby.R;
import com.mercy.happybaby.utils.Constants;

public class SettingsFragment extends Fragment {
	private TextView monthReminder;
	private TextView vaccineReminder;
	private TextView monthlyAdvise;
	private TextView isReminderSet;

	private Button monthReminderToggle;
	private Button vaccineReminderToggle;
	private Button monthlyToggle;
	private Button isReminderToggle;

	private Typeface roboto_light;

	private boolean isMonthReminderOn = false;
	private boolean isVaccineReminderOn = false;
	private boolean isMonthlyOn = false;
	private boolean isReminderOn = false;

	public SettingsFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		View root = inflater.inflate(R.layout.settings_view, container, false);
		monthReminder = (TextView) root.findViewById(R.id.monthReminder);
		monthReminder.setTypeface(roboto_light);

		vaccineReminder = (TextView) root.findViewById(R.id.vaccineReminder);
		vaccineReminder.setTypeface(roboto_light);

		monthlyAdvise = (TextView) root.findViewById(R.id.monthlyAdvise);
		monthlyAdvise.setTypeface(roboto_light);

		isReminderSet = (TextView) root.findViewById(R.id.isReminderSet);
		isReminderSet.setTypeface(roboto_light);

		monthReminderToggle = (Button) root
				.findViewById(R.id.monthReminderToggle);
		vaccineReminderToggle = (Button) root
				.findViewById(R.id.vaccineReminderToggle);
		monthlyToggle = (Button) root.findViewById(R.id.monthlyToggle);
		isReminderToggle = (Button) root.findViewById(R.id.isReminderToggle);

		monthReminderToggle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isMonthReminderOn) {
					monthReminderToggle.setBackground(getActivity()
							.getResources().getDrawable(R.drawable.toggle_off));
					monthReminder.setTextColor(getActivity().getResources()
							.getColor(R.color.fontSecond));
					isMonthReminderOn = false;
				} else {
					monthReminderToggle.setBackground(getActivity()
							.getResources().getDrawable(R.drawable.toggle_on));
					monthReminder.setTextColor(getActivity().getResources()
							.getColor(R.color.fontFirst));
					isMonthReminderOn = true;
				}
			}
		});

		vaccineReminderToggle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isVaccineReminderOn) {
					vaccineReminderToggle.setBackground(getActivity()
							.getResources().getDrawable(R.drawable.toggle_off));
					vaccineReminder.setTextColor(getActivity().getResources()
							.getColor(R.color.fontSecond));
					isVaccineReminderOn = false;
				} else {
					vaccineReminderToggle.setBackground(getActivity()
							.getResources().getDrawable(R.drawable.toggle_on));
					vaccineReminder.setTextColor(getActivity().getResources()
							.getColor(R.color.fontFirst));
					isVaccineReminderOn = true;
				}
			}
		});

		monthlyToggle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isMonthlyOn) {
					monthlyToggle.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.toggle_off));
					monthlyAdvise.setTextColor(getActivity().getResources()
							.getColor(R.color.fontSecond));
					isMonthlyOn = false;
				} else {
					monthlyToggle.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.toggle_on));
					monthlyAdvise.setTextColor(getActivity().getResources()
							.getColor(R.color.fontFirst));
					isMonthlyOn = true;
				}
			}
		});

		isReminderToggle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (isReminderOn) {
					isReminderToggle.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.toggle_off));
					isReminderSet.setTextColor(getActivity().getResources()
							.getColor(R.color.fontSecond));
					isReminderOn = false;
				} else {
					isReminderToggle.setBackground(getActivity().getResources()
							.getDrawable(R.drawable.toggle_on));
					isReminderSet.setTextColor(getActivity().getResources()
							.getColor(R.color.fontFirst));
					isReminderOn = true;
				}
			}
		});
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
