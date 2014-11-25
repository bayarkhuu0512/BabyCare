package com.mercy.babycare.ui.breast;

import com.mercy.babycare.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BreastAdapter extends
		RecyclerView.Adapter<BreastAdapter.ViewHolder> {
	private final Context mContext;
	private final String[] mDataset;

	public BreastAdapter(Context context, String[] dataset) {
		mContext = context;
		mDataset = dataset;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.timeline_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		String[] values = mDataset[position].split(",");
		String countryName = values[0];
		viewHolder.mTextView.setText(countryName);
	}

	@Override
	public int getItemCount() {
		return mDataset.length;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.line1);
		}
	}
}