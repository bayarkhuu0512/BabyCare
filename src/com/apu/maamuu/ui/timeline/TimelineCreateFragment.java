package com.apu.maamuu.ui.timeline;

import com.apu.maamuu.MainActivity;
import com.apu.maamuu.R;

import dreamers.graphics.RippleDrawable;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class TimelineCreateFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater
				.inflate(R.layout.timeline_create, container, false);
		ImageButton cancelButton = (ImageButton) root
				.findViewById(R.id.cancelButton);
		RippleDrawable.createRipple(cancelButton,
				getResources().getColor(R.color.accent));
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fragment = new TimelineFragment();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager
						.beginTransaction()
						.setCustomAnimations(R.animator.slide_up,
								R.animator.slide_down, R.animator.slide_up,
								R.animator.slide_down)
						.replace(R.id.content_frame, fragment).commit();
			}
		});
		
		ImageButton addBreast = (ImageButton) root
				.findViewById(R.id.addBreast);
		RippleDrawable.createRipple(addBreast,
				getResources().getColor(R.color.mainColor));
	
		ImageButton learn = (ImageButton) root
				.findViewById(R.id.learn);
		RippleDrawable.createRipple(learn,
				getResources().getColor(R.color.mainColor));
	
	
		return root;
	}
}
