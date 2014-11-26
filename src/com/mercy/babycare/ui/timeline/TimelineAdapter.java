package com.mercy.babycare.ui.timeline;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.mercy.babycare.R;
import com.mercy.babycare.entities.Timeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TimelineAdapter extends
		RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
	private final Context mContext;
	List<Timeline> mList;

	public TimelineAdapter(Context context, List<Timeline> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.timeline_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		viewHolder.mTextView.setText(mList.get(position).getBaby()
				.getFirstName());
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.line1);
		}
	}
}