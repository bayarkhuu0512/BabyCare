package com.mercy.babycare.ui.timeline;

import com.mercy.babycare.MainActivity;
import com.mercy.babycare.R;

import dreamers.graphics.RippleDrawable;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TimelineCreateFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater
				.inflate(R.layout.timeline_create, container, false);
		Button cancelButton = (Button) root.findViewById(
				R.id.cancelButton);
		RippleDrawable.createRipple(cancelButton,
				getResources().getColor(R.color.mainColor));
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT)
						.show();
				
			}
		});
		return root;
	}
}
