package com.mercy.happybaby.utils.crouton;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class CroutonMessage implements OnClickListener {
	Activity activity;
	Crouton crouton = null;
	private static final Style INFINITE = new Style.Builder()
			.setBackgroundColorValue(Style.holoGreenLight).build();
	private static final Configuration CONFIGURATION_INFINITE = new Configuration.Builder()
			.setDuration(Configuration.DURATION_SHORT).build();

	public CroutonMessage(Activity a) {
		activity = a;
	}

	// Show Crouton
	public void showCrouton(Style stl, String str) {
		Style croutonStyle = Style.INFO;
		showBuiltInCrouton(croutonStyle, str);
	}

	private void showBuiltInCrouton(final Style croutonStyle, final String str) {
		String croutonText = str;
		showCrouton(croutonText, croutonStyle, Configuration.DEFAULT);
	}

	private void showCrouton(String croutonText, Style croutonStyle,
			Configuration configuration) {
		crouton = Crouton.makeText(activity, croutonText, croutonStyle);
		crouton.setOnClickListener(this)
				.setConfiguration(true ? CONFIGURATION_INFINITE : configuration)
				.show();
	}

	public Crouton isShowing() {
		return crouton;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (crouton != null) {
			Crouton.hide(crouton);
			crouton = null;
		}
	}

	public void hide() {
		if (crouton != null) {
			Crouton.hide(crouton);
			crouton = null;
		}
	}

}
