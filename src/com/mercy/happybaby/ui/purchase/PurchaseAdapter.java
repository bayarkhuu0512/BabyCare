package com.mercy.happybaby.ui.purchase;

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

public class PurchaseAdapter extends
		RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {
	String LOG_TAG = PurchaseAdapter.class.getName();
	private Context mContext;
	private List<Timeline> mList;
	private Typeface roboto_light;

	public PurchaseAdapter(Context context, List<Timeline> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.purchase_row, parent, false);
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

		viewHolder.calDay.setTypeface(roboto_light);
		viewHolder.calDay.setText(Constants.rowDayFormat.format(
				timeline.getCreatedDate()).toString()
				+ WeekDayTranslateToMon.translate(mContext, dayOfWeekEN));

		viewHolder.mFirst.setTypeface(roboto_light);
		viewHolder.mSecond.setTypeface(roboto_light);
		viewHolder.mThird.setTypeface(roboto_light);
		viewHolder.mFourth.setTypeface(roboto_light);
		viewHolder.mFifth.setTypeface(roboto_light);
		viewHolder.mSixth.setTypeface(roboto_light);

		if (timeline.getPurchase() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.purchase_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_purchase));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getPurchase().getPurchaseName()
					+ " "
					+ mContext.getResources().getString(R.string.purchase));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getPurchase()
					.getPurchaseAmount()
					+ " "
					+ mContext.getResources().getString(R.string.amount));

			viewHolder.mFifth.setVisibility(View.VISIBLE);
			viewHolder.mFifth.setText(timeline.getPurchase().getPurchasePrice()
					+ " " + mContext.getResources().getString(R.string.tugrug));

			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
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