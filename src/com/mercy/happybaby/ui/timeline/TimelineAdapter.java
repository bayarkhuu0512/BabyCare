package com.mercy.happybaby.ui.timeline;

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

public class TimelineAdapter extends
		RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
	String LOG_TAG = TimelineAdapter.class.getName();
	private Context mContext;
	private List<Timeline> mList;
	private Typeface roboto_light;

	public TimelineAdapter(Context context, List<Timeline> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
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
		if (timeline.getBaby() != null) {
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.baby67));
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.formula_oval));
			viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
					timeline.getBaby().getBirthDate()).toString()
					+ mContext.getResources().getString(R.string.month));
			viewHolder.calDay.setText(Constants.rowDayFormat.format(
					timeline.getBaby().getBirthDate()).toString()
					+ dayOfWeekMN);
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getBaby().getLastName()
					.substring(0, 1)
					+ "." + timeline.getBaby().getFirstName());
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(Constants.timeFormat.format(
					timeline.getBaby().getBirthTime()).toString());
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getBaby().getBirthHeight()
					+ "см");
			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getBaby().getBirthWeight()
					+ "гр");

			viewHolder.mFifth.setVisibility(View.VISIBLE);
			viewHolder.mFifth.setText(timeline.getBaby().getGender());

			viewHolder.mSixth.setVisibility(View.VISIBLE);
			viewHolder.mSixth.setText("төрлөө");

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
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.breast_oval));
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

			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);

		} else if (timeline.getFeed() != null) {
			int num = 0;
			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i).getFeed() != null) {
					num++;
				}
			}
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.feed_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_extrafood));
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.feed));
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(num + " "
					+ mContext.getResources().getString(R.string.num));

			viewHolder.mThird.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getFormula() != null) {
			int num = 0;
			int total = 0;
			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i).getFormula() != null) {
					num++;
					total = total + timeline.getFormula().getMl();
				}
			}
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.formula_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_formula));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.formula));
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(num + " "
					+ mContext.getResources().getString(R.string.num));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(mContext.getResources().getString(
					R.string.total)
					+ " "
					+ total
					+ mContext.getResources().getString(R.string.ml));

			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getDrink() != null) {
			int num = 0;
			int total = 0;
			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i).getDrink() != null) {
					num++;
					total = total + timeline.getDrink().getMl();
				}
			}
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.drink_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_drink));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.drink));
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(num + " "
					+ mContext.getResources().getString(R.string.num));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(mContext.getResources().getString(
					R.string.total)
					+ " "
					+ total
					+ mContext.getResources().getString(R.string.ml));

			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getHospital() != null) {
			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.hospital_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_gohospital));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.hospital));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getHospital().getHospitalName());

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
		} else if (timeline.getActiveOperation() != null) {
			int num = 0;
			for (int i = 0; i < mList.size(); i++) {
				if (timeline.getActiveOperation() != null) {
					num++;
				}
			}

			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.ao_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_bath));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getActiveOperation()
					.getActiveName());
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(num + " "
					+ mContext.getResources().getString(R.string.num));

			viewHolder.mThird.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);

		} else if (timeline.getChangeDiaper() != null) {
			int num = 0;
			int dirty = 0;
			int dry = 0;
			int wet = 0;
			int mixed = 0;

			for (int i = 0; i < mList.size(); i++) {
				if (mList.get(i).getDrink() != null) {
					num++;
					dirty = dirty + timeline.getChangeDiaper().getDirty();
					dry = dry + timeline.getChangeDiaper().getDry();
					wet = wet + timeline.getChangeDiaper().getWet();
					mixed = mixed + timeline.getChangeDiaper().getMixed();

				}
			}

			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.changediaper_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_diaperchange));
			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText("Живхээ солилоо");
			viewHolder.mSecond.setVisibility(View.VISIBLE);
			viewHolder.mSecond.setText(num + " "
					+ mContext.getResources().getString(R.string.num));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText("Баастай " + dirty);
			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText("Шээстэй " + wet);
			viewHolder.mFifth.setVisibility(View.VISIBLE);
			viewHolder.mFifth.setText("Холимог " + mixed);
			viewHolder.mSixth.setVisibility(View.VISIBLE);
			viewHolder.mSixth.setText("Хуурай " + dry);

		} else if (timeline.getGrowth() != null) {
			viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
					timeline.getGrowth().getCreatedDate()).toString()
					+ mContext.getResources().getString(R.string.month));
			viewHolder.calDay.setText(Constants.rowDayFormat.format(
					timeline.getGrowth().getCreatedDate()).toString()
					+ dayOfWeekMN);

			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.anniversary));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_birthmonth));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(timeline.getGrowth().getBabyMonth()
					+ " сар хүрлээ");
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(timeline.getGrowth().getBabyHeight()
					+ " см");
			viewHolder.mFourth.setVisibility(View.VISIBLE);
			viewHolder.mFourth.setText(timeline.getGrowth().getBabyWeight()
					+ " гр");

			viewHolder.mFifth.setVisibility(View.VISIBLE);
			viewHolder.mFifth.setText(mContext.getResources().getString(
					R.string.congrats));

			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getLearn() != null) {

			viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
					timeline.getLearn().getCreatedDate()).toString()
					+ mContext.getResources().getString(R.string.month));
			viewHolder.calDay.setText(Constants.rowDayFormat.format(
					timeline.getLearn().getCreatedDate()).toString()
					+ dayOfWeekMN);

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

		} else if (timeline.getTooth() != null) {
			viewHolder.calMonth.setText(Constants.rowMonthFormat.format(
					timeline.getTooth().getCreatedDate()).toString()
					+ mContext.getResources().getString(R.string.month));
			viewHolder.calDay.setText(Constants.rowDayFormat.format(
					timeline.getTooth().getCreatedDate()).toString()
					+ dayOfWeekMN);

			viewHolder.icon.setBackgroundDrawable(mContext.getResources()
					.getDrawable(R.drawable.tooth_oval));
			viewHolder.icon.setImageDrawable(mContext.getResources()
					.getDrawable(R.drawable.icon_teeth));

			viewHolder.mFirst.setVisibility(View.VISIBLE);
			viewHolder.mFirst.setText(mContext.getResources().getString(
					R.string.tooth)
					+ " "
					+ timeline.getTooth().getToothNum()
					+ " "
					+ mContext.getResources().getString(R.string.tooth_grow));
			viewHolder.mThird.setVisibility(View.VISIBLE);
			viewHolder.mThird.setText(mContext.getResources().getString(
					R.string.hooray));

			viewHolder.mSecond.setVisibility(View.GONE);
			viewHolder.mFourth.setVisibility(View.GONE);
			viewHolder.mFifth.setVisibility(View.GONE);
			viewHolder.mSixth.setVisibility(View.GONE);
		} else if (timeline.getPurchase() != null) {
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