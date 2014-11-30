package com.mercy.happybaby.ui.meal;

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

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
	String LOG_TAG = MealAdapter.class.getName();
	private Context mContext;
	private List<Timeline> mList;
	private Typeface roboto_light;

	public MealAdapter(Context context, List<Timeline> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.meal_row, parent, false);
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

		// Baby
		if (timeline.getBreast() != null) {
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			if (timeline.getBreast().isRight()) {
				viewHolder.mFirst.setText(mContext.getResources().getString(
						R.string.right));
				viewHolder.icon.setBackgroundDrawable(mContext.getResources()
						.getDrawable(R.drawable.breast_oval));
			} else {
				viewHolder.mFirst.setText(mContext.getResources().getString(
						R.string.left));
				viewHolder.icon.setBackgroundDrawable(mContext.getResources()
						.getDrawable(R.drawable.formula_oval));
			}
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_breast));
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(timeline
					.getBreast().getBreastTime()));

			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(mContext.getResources().getString(
					R.string.breast));

			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getFeed() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.feed_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_extrafood));
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getFeed().getFeedName());
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(timeline
					.getFeed().getCreatedDate()));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setVisibility(View.VISIBLE);

			if (timeline.getFeed().getAmount() != null) {
				viewHolder.mThird.setText(timeline.getFeed().getAmount() + "");
				viewHolder.mFourth.setText(mContext.getResources().getString(
						R.string.amount));
			} else if (timeline.getFeed().getMl() != null) {
				viewHolder.mThird.setText(timeline.getFeed().getMl() + "");
				viewHolder.mFourth.setText(mContext.getResources().getString(
						R.string.ml));
			}

			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);

		} else if (timeline.getFormula() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.formula_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_formula));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.formula));
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(timeline
					.getFormula().getCreatedDate()));

			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getFormula().getFormulaName()
					+ "");

			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getFormula().getMl() + "");

			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getDrink() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.drink_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_drink));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.drink));
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(timeline
					.getDrink().getCreatedDate()));

			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getDrink().getDrinkName() + "");

			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getDrink().getMl() + "");

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