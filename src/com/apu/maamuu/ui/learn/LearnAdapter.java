package com.apu.maamuu.ui.learn;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apu.maamuu.entities.Timeline;
import com.apu.maamuu.utils.Constants;
import com.apu.maamuu.R;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.ViewHolder> {
	String LOG_TAG = LearnAdapter.class.getName();
	private Context mContext;
	private List<Timeline> mList;
	private Typeface roboto_light;

	public LearnAdapter(Context context, List<Timeline> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.learn_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		Timeline timeline = mList.get(position);
		viewHolder.calMonth.setTypeface(roboto_light);
		viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
				timeline.getCreatedDate()).toString()
				+ mContext.getResources().getString(R.string.month));
		String dayOfWeekEN = Constants.rowDayOfWeekFormat.format(
				timeline.getCreatedDate()).toString();
		String dayOfWeekMN = "";
		if (dayOfWeekEN.equals("Mon")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.mon);
		} else if (dayOfWeekEN.equals("Tue")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.tue);
		} else if (dayOfWeekEN.equals("Wed")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.wed);
		} else if (dayOfWeekEN.equals("Thu")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.thu);
		} else if (dayOfWeekEN.equals("Fri")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.fri);
		} else if (dayOfWeekEN.equals("Sat")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.sat);
		} else if (dayOfWeekEN.equals("Sun")) {
			dayOfWeekMN = mContext.getResources().getString(R.string.sun);
		}

		viewHolder.calDay.setTypeface(roboto_light);
		viewHolder.calDay.setText(Constants.rowDayFormat.format(
				timeline.getCreatedDate()).toString()
				+ dayOfWeekMN);

		viewHolder.mFirst.setTypeface(roboto_light);
		viewHolder.mSecond.setTypeface(roboto_light);
		viewHolder.mThird.setTypeface(roboto_light);
		viewHolder.mFourth.setTypeface(roboto_light);
		viewHolder.mFifth.setTypeface(roboto_light);
		viewHolder.mSixth.setTypeface(roboto_light);

		if (timeline.getLearn() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.learn_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_learn));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getLearn().getLearnName());
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(mContext.getResources().getString(
					R.string.congrats));

			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		}
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mFirst;
		public TextView mSecond;
		public TextView mThird;
		public TextView mFourth;
		public TextView mFifth;
		public TextView mSixth;
		public TextView calMonth;
		public TextView calDay;
		public ImageView icon;

		public ViewHolder(View v) {
			super(v);
			mFirst = (TextView) v.findViewById(R.id.first);
			mSecond = (TextView) v.findViewById(R.id.second);
			mThird = (TextView) v.findViewById(R.id.third);
			mFourth = (TextView) v.findViewById(R.id.fourth);
			mFifth = (TextView) v.findViewById(R.id.fifth);
			mSixth = (TextView) v.findViewById(R.id.sixth);
			calMonth = (TextView) v.findViewById(R.id.calMonth);
			calDay = (TextView) v.findViewById(R.id.calDay);
			icon = (ImageView) v.findViewById(R.id.icon);
		}
	}
}