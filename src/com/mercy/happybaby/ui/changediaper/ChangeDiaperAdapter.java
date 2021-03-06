package com.mercy.happybaby.ui.changediaper;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercy.happybaby.R;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.WeekDayTranslateToMon;

public class ChangeDiaperAdapter extends
		RecyclerView.Adapter<ChangeDiaperAdapter.ViewHolder> {
	private final Context mContext;
	List<Timeline> mList;
	private Typeface roboto_light;

	public ChangeDiaperAdapter(Context context, List<Timeline> list) {
		mContext = context;
		mList = list;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.changediaper_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		Timeline timeline = mList.get(position);
		viewHolder.calMonth.setTypeface(roboto_light);
		viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
				mList.get(position).getCreatedDate()).toString()
				+ mContext.getResources().getString(R.string.month));
		String dayOfWeekEN = Constants.rowDayOfWeekFormat.format(
				mList.get(position).getCreatedDate()).toString();
		viewHolder.calDay.setTypeface(roboto_light);
		viewHolder.calDay.setText(Constants.rowDayFormat.format(
				mList.get(position).getCreatedDate()).toString()
				+ WeekDayTranslateToMon.translate(mContext, dayOfWeekEN));

		viewHolder.mFirst.setTypeface(roboto_light);
		viewHolder.mSecond.setTypeface(roboto_light);
		viewHolder.mThird.setTypeface(roboto_light);
		viewHolder.mFourth.setTypeface(roboto_light);
		viewHolder.mFifth.setTypeface(roboto_light);
		viewHolder.mSixth.setTypeface(roboto_light);

		if (timeline.getChangeDiaper() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.changediaper_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_diaperchange));
			viewHolder.mFirst.setVisibility(View.VISIBLE);

			viewHolder.mFirst.setText(timeline.getChangeDiaper()
					.getDiaperType());
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(timeline
					.getChangeDiaper().getCreatedDate()));

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