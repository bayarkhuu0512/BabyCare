package com.mercy.happybaby.ui.timeline;

import com.mercy.happybaby.MainActivity;
import com.mercy.happybaby.R;

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
		
		ImageButton extraFood = (ImageButton) root
				.findViewById(R.id.extraFood);
		RippleDrawable.createRipple(extraFood,
				getResources().getColor(R.color.mainColor));
		
		ImageButton formula = (ImageButton) root
				.findViewById(R.id.formula);
		RippleDrawable.createRipple(formula,
				getResources().getColor(R.color.mainColor));
		
		ImageButton drink = (ImageButton) root
				.findViewById(R.id.drink);
		RippleDrawable.createRipple(drink,
				getResources().getColor(R.color.mainColor));
		
		ImageButton medcheck = (ImageButton) root
				.findViewById(R.id.medcheck);
		RippleDrawable.createRipple(medcheck,
				getResources().getColor(R.color.mainColor));
		
		ImageButton hospital = (ImageButton) root
				.findViewById(R.id.hospital);
		RippleDrawable.createRipple(hospital,
				getResources().getColor(R.color.mainColor));
		
		ImageButton vitamin = (ImageButton) root
				.findViewById(R.id.vitamin);
		RippleDrawable.createRipple(vitamin,
				getResources().getColor(R.color.mainColor));
		
		ImageButton vaccine = (ImageButton) root
				.findViewById(R.id.vaccine);
		RippleDrawable.createRipple(vaccine,
				getResources().getColor(R.color.mainColor));
		
		ImageButton shower = (ImageButton) root
				.findViewById(R.id.shower);
		RippleDrawable.createRipple(shower,
				getResources().getColor(R.color.mainColor));
		
		ImageButton sleep = (ImageButton) root
				.findViewById(R.id.sleep);
		RippleDrawable.createRipple(sleep,
				getResources().getColor(R.color.mainColor));
		
		ImageButton play = (ImageButton) root
				.findViewById(R.id.play);
		RippleDrawable.createRipple(play,
				getResources().getColor(R.color.mainColor));
		
		ImageButton out = (ImageButton) root
				.findViewById(R.id.out);
		RippleDrawable.createRipple(out,
				getResources().getColor(R.color.mainColor));
		
		ImageButton massage = (ImageButton) root
				.findViewById(R.id.massage);
		RippleDrawable.createRipple(massage,
				getResources().getColor(R.color.mainColor));
		
		ImageButton ear = (ImageButton) root
				.findViewById(R.id.ear);
		RippleDrawable.createRipple(ear,
				getResources().getColor(R.color.mainColor));
		
		ImageButton nose = (ImageButton) root
				.findViewById(R.id.nose);
		RippleDrawable.createRipple(nose,
				getResources().getColor(R.color.mainColor));
	
		ImageButton changediaper = (ImageButton) root
				.findViewById(R.id.changediaper);
		RippleDrawable.createRipple(changediaper,
				getResources().getColor(R.color.mainColor));
	
		ImageButton annniversary = (ImageButton) root
				.findViewById(R.id.anniversary);
		RippleDrawable.createRipple(annniversary,
				getResources().getColor(R.color.mainColor));
	
		ImageButton learn = (ImageButton) root
				.findViewById(R.id.learn);
		RippleDrawable.createRipple(learn,
				getResources().getColor(R.color.mainColor));
	
		ImageButton tooth = (ImageButton) root
				.findViewById(R.id.tooth);
		RippleDrawable.createRipple(tooth,
				getResources().getColor(R.color.mainColor));
		
		ImageButton purchase = (ImageButton) root
				.findViewById(R.id.purchase);
		RippleDrawable.createRipple(purchase,
				getResources().getColor(R.color.mainColor));
	
		return root;
	}
}
