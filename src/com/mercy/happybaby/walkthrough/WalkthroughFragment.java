package com.mercy.happybaby.walkthrough;

import com.mercy.happybaby.R;
import com.mercy.happybaby.utils.Constants;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public final class WalkthroughFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	private Typeface roboto_light;

	public static WalkthroughFragment newInstance(String title, String desc) {
		WalkthroughFragment fragment = new WalkthroughFragment();
		fragment.mTitle = title;
		fragment.mDesc = desc;

		return fragment;
	}

	private String mTitle = "";
	private String mDesc = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mTitle = savedInstanceState.getString(KEY_CONTENT);
		}
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.walkthrough, container, false);
		TextView title = (TextView) root.findViewById(R.id.title);
		title.setTypeface(roboto_light);
		title.setText(mTitle);

		TextView desc = (TextView) root.findViewById(R.id.desc);
		desc.setTypeface(roboto_light);
		desc.setText(mDesc);

		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mTitle);
	}
}
