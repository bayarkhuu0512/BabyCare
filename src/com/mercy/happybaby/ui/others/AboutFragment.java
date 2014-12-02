package com.mercy.happybaby.ui.others;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercy.happybaby.R;
import com.mercy.happybaby.utils.Constants;

public class AboutFragment extends Fragment {
	TextView developerAltanchimeg;
	TextView developerBayarkhuu;
	TextView appDetail;
	TextView appName;
	private Typeface roboto_light;

	public AboutFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.about_view, container, false);
		appName = (TextView) root.findViewById(R.id.appName);
		appName.setTypeface(roboto_light);
		appName.setText(getActivity().getResources().getString(
				R.string.app_name)
				+ " v1.0");
		appDetail = (TextView) root.findViewById(R.id.appDetail);
		appDetail.setTypeface(roboto_light);
		appDetail.setText(getActivity().getResources().getString(
				R.string.app_detail));
		developerBayarkhuu = (TextView) root
				.findViewById(R.id.developerBayarkhuu);
		developerBayarkhuu.setTypeface(roboto_light);
		developerBayarkhuu.setText("@bayarkhuu");
		developerAltanchimeg = (TextView) root
				.findViewById(R.id.developerAltanchimeg);
		developerAltanchimeg.setText("@altanchimegG");
		developerAltanchimeg.setTypeface(roboto_light);

		return root;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
