package com.mercy.happybaby;

import com.mercy.happybaby.R;
import com.mercy.happybaby.utils.Constants;

import dreamers.graphics.RippleDrawable;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeftNavAdapter extends BaseAdapter {
	private Context mContext;
	private String[] mMenus;
	private LayoutInflater mLayoutInflater = null;
	private Typeface roboto_medium;

	public LeftNavAdapter(Context context, String[] menus) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mMenus = menus;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		roboto_medium = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_MEDIUM);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMenus.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mMenus[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		CompleteListViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.nav_list_item, null);
			viewHolder = new CompleteListViewHolder(v);
			v.setTag(viewHolder);
		} else {
			viewHolder = (CompleteListViewHolder) v.getTag();
		}

		viewHolder.mMenu.setTypeface(roboto_medium);
		viewHolder.mMenu.setText(mMenus[position]);
		viewHolder.container.setId(position);

		if (position == 1 || position == 9 || position == 7) {
			viewHolder.underline.setVisibility(View.VISIBLE);
		} else {
			viewHolder.underline.setVisibility(View.GONE);
		}
		viewHolder.rightChevron.setVisibility(View.VISIBLE);

		if (position == 0 || position == 1) {
			viewHolder.mMenu.setTextColor(mContext.getResources().getColor(
					R.color.mainColor));
		} else {
			viewHolder.mMenu.setTextColor(mContext.getResources().getColor(
					R.color.fontSecond));

		}

		return v;
	}

	class CompleteListViewHolder {
		public TextView mMenu;
		public View underline;
		public ImageView rightChevron;
		public LinearLayout container;

		public CompleteListViewHolder(View base) {
			mMenu = (TextView) base.findViewById(R.id.first);
			underline = (View) base.findViewById(R.id.underline);
			rightChevron = (ImageView) base.findViewById(R.id.rightChevron);
			container = (LinearLayout) base.findViewById(R.id.container);
		}
	}
}
