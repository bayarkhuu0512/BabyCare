package com.mercy.happybaby.ui.health;

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

public class HealthAdapter extends
		RecyclerView.Adapter<HealthAdapter.ViewHolder> {
	String LOG_TAG = HealthAdapter.class.getName();
	private Context mContext;
	private List<Timeline> mList;
	private Typeface roboto_light;

	public HealthAdapter(Context context, List<Timeline> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.health_row, parent, false);
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

		if (timeline.getHospital() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.hospital_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_gohospital));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getHospital().getPainName());
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getHospital().getDiagnosis());
			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getHospital().getHealing());
			viewHolder.mFifth.setVisibility(View.VISIBLE);
			viewHolder.mFifth.setText(timeline.getHospital().getHospitalName());
			viewHolder.mSixth.setVisibility(View.VISIBLE);
			viewHolder.mSixth.setText(timeline.getHospital().getDoctorName());
			viewHolder.mSecond.setVisibility(View.GONE);

		} else if (timeline.getGrowth() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.medcheck_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_medcheck));
			
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getGrowth().getMonth()
					+ " сарын үзлэг");
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getGrowth().getHeight()
					+ mContext.getResources().getString(R.string.sm));
			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getGrowth().getWeight()
					+ mContext.getResources().getString(R.string.gr));
			
			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
			
		} else if (timeline.getVaccine() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.vaccine_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_vaccine));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.right));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getVaccine().getVaccineName());

			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
			
		} else if (timeline.getVaccine() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.vaccine_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_vaccine));
			
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.right));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getVaccine().getVaccineName());
			
			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
			
		} else if (timeline.getVitamin() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.vitamin_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_vitamin));
			
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.right));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getVitamin().getVitaminName());
			
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