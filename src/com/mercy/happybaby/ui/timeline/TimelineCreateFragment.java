package com.mercy.happybaby.ui.timeline;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mercy.happybaby.R;
import com.mercy.happybaby.utils.Constants;

import dreamers.graphics.RippleDrawable;

public class TimelineCreateFragment extends Fragment {
	private TextView breastDir;
	private TextView breastTime;
	private TextView extraFoodName;
	private TextView extraFoodTime;
	private TextView formulaName;
	private TextView drinkName;
	private TextView drinkTime;
	private TextView hospitalName;
	private TextView medcheckName;
	private TextView medcheckTime;
	private TextView vitaminName;
	private TextView vaccineName;
	private TextView learnName;
	private TextView learnValue;
	private TextView toothName;
	private TextView toothValue;
	private TextView changediaperName;
	private TextView changediaperLabel;
	private TextView activeOperationName;
	private Typeface roboto_light;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		
		View root = inflater
				.inflate(R.layout.timeline_create, container, false);
		
		ImageButton cancelButton = (ImageButton) root
				.findViewById(R.id.cancelButton);
		// RippleDrawable.createRipple(cancelButton,
		// getResources().getColor(R.color.accent));
		// cancelButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Fragment fragment = new TimelineFragment();
		// FragmentManager fragmentManager = getFragmentManager();
		// fragmentManager
		// .beginTransaction()
		// .setCustomAnimations(R.animator.slide_up,
		// R.animator.slide_down, R.animator.slide_up,
		// R.animator.slide_down)
		// .replace(R.id.content_frame, fragment).commit();
		// }
		// });

		ImageButton addBreast = (ImageButton) root.findViewById(R.id.addBreast);
		RippleDrawable.createRipple(addBreast,
				getResources().getColor(R.color.mainColor));
		
		breastDir= (TextView) root.findViewById(R.id.breastDir);
		breastDir.setTypeface(roboto_light);
		
		breastTime= (TextView) root.findViewById(R.id.breastTime);
		breastTime.setTypeface(roboto_light);

		extraFoodName= (TextView) root.findViewById(R.id.extraFoodName);
		extraFoodName.setTypeface(roboto_light);

		extraFoodTime= (TextView) root.findViewById(R.id.extraFoodTime);
		extraFoodTime.setTypeface(roboto_light);

		formulaName= (TextView) root.findViewById(R.id.formulaName);
		formulaName.setTypeface(roboto_light);

		drinkName= (TextView) root.findViewById(R.id.drinkName);
		drinkName.setTypeface(roboto_light);

		drinkTime= (TextView) root.findViewById(R.id.drinkTime);
		drinkTime.setTypeface(roboto_light);

		hospitalName= (TextView) root.findViewById(R.id.hospitalName);
		hospitalName.setTypeface(roboto_light);

		medcheckName= (TextView) root.findViewById(R.id.medcheckName);
		medcheckName.setTypeface(roboto_light);

		medcheckTime= (TextView) root.findViewById(R.id.medcheckTime);
		medcheckTime.setTypeface(roboto_light);

		vitaminName= (TextView) root.findViewById(R.id.vitaminName);
		vitaminName.setTypeface(roboto_light);

		vaccineName= (TextView) root.findViewById(R.id.vaccineName);
		vaccineName.setTypeface(roboto_light);

		learnName= (TextView) root.findViewById(R.id.learnName);
		learnName.setTypeface(roboto_light);

		learnValue= (TextView) root.findViewById(R.id.learnValue);
		learnValue.setTypeface(roboto_light);

		toothName= (TextView) root.findViewById(R.id.toothName);
		toothName.setTypeface(roboto_light);

		toothValue= (TextView) root.findViewById(R.id.toothValue);
		toothValue.setTypeface(roboto_light);

		changediaperName= (TextView) root.findViewById(R.id.changediaperName);
		changediaperName.setTypeface(roboto_light);

		changediaperLabel= (TextView) root.findViewById(R.id.changediaperLabel);
		changediaperLabel.setTypeface(roboto_light);

		activeOperationName= (TextView) root.findViewById(R.id.activeOperationName);
		activeOperationName.setTypeface(roboto_light);



		return root;
	}
}
