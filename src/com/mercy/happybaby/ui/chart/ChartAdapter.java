package com.mercy.happybaby.ui.chart;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ChartAdapter extends ArrayAdapter<ChartItem> {

	public ChartAdapter(Context context, List<ChartItem> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getItem(position).getView(position, convertView, getContext());
	}

	@Override
	public int getItemViewType(int position) {
		// return the views type
		return getItem(position).getItemType();
	}

	@Override
	public int getViewTypeCount() {
		return 4; // we have 3 different item-types
	}
}