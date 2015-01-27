package com.mercy.happybaby.walkthrough;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mercy.happybaby.R;
import com.viewpagerindicator.IconPagerAdapter;

class WalkthroughFragmentAdapter extends FragmentPagerAdapter {
	protected static String[] title_array = null;
	protected static String[] desc_array = null;

	private int mCount;

	public WalkthroughFragmentAdapter(Context context, FragmentManager fm) {
		super(fm);
		title_array = context.getResources().getStringArray(
				R.array.intro_title_array);
		desc_array = context.getResources().getStringArray(
				R.array.intro_desc_array);
		mCount = title_array.length;
	}

	@Override
	public Fragment getItem(int position) {
		return WalkthroughFragment.newInstance(title_array[position],desc_array[position]);
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return WalkthroughFragmentAdapter.title_array[position
				% title_array.length];
	}

	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			mCount = count;
			notifyDataSetChanged();
		}
	}
}