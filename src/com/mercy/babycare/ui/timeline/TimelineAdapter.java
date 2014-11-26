package com.mercy.babycare.ui.timeline;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.mercy.babycare.R;
import com.mercy.babycare.entities.Timeline;
import com.mercy.babycare.ui.breast.BreastFragment;
import com.mercy.babycare.utils.Constants;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TimelineAdapter extends
		RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
	String LOG_TAG = TimelineAdapter.class.getName();
	private Context mContext;
	private List<Timeline> mList;
	private Typeface roboto_light;
	private Typeface roboto_thin;
	
	public TimelineAdapter(Context context, List<Timeline> list) {
		mContext = context;
		roboto_light= Typeface.createFromAsset(mContext.getAssets(), Constants.ROBOTO_LIGHT);
		mList = list;
		roboto_thin= Typeface.createFromAsset(mContext.getAssets(), Constants.ROBOTO_THIN);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.timeline_row, parent, false);
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
		if (dayOfWeekEN.equals("mon")) {
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

		if (timeline.getBaby() != null) {
			viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
					timeline.getBaby().getBirthDate()).toString()
					+ mContext.getResources().getString(R.string.month));
			viewHolder.calDay.setText(Constants.rowDayFormat.format(
					timeline.getBaby().getBirthDate()).toString()
					+ dayOfWeekMN);
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getBaby().getFirstName());
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(
					timeline.getBaby().getBirthTime()).toString());
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getBaby().getBirthHeight()
					+ "см");
			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getBaby().getBirthWeight()
					+ "гр");
		} else if (timeline.getBreast() != null) {
			int num = 0;
			int isRightNum = 0;
			int isLeftNum = 0;
			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i).getBreast() != null) {
					num++;
					if (mList.get(i).getBreast().isRight()) {
						isRightNum++;
					} else {
						isLeftNum++;
					}
				}
			}
			viewHolder.icon.setBackgroundDrawable(mContext
					.getResources().getDrawable(R.drawable.breast_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_breast));
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.breast));

			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(num + " "
					+ mContext.getResources().getString(R.string.num));

			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(mContext.getResources().getString(
					R.string.right)
					+ " " + isRightNum);

			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(mContext.getResources().getString(
					R.string.left)
					+ " " + isLeftNum);

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