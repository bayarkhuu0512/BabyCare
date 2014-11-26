package com.mercy.babycare.ui.breast;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercy.babycare.R;
import com.mercy.babycare.entities.Breast;
import com.mercy.babycare.utils.Constants;

public class BreastAdapter extends
		RecyclerView.Adapter<BreastAdapter.ViewHolder> {
	private final Context mContext;
	List<Breast> mList;

	public BreastAdapter(Context context, List<Breast> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.breast_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		viewHolder.mFirst.setVisibility(View.VISIBLE);
		if (mList.get(position).isRight()) {
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
		viewHolder.mSecond.setVisibility(View.VISIBLE);
		viewHolder.mSecond.setText(Constants.timeFormat.format(mList.get(
				position).getBreastTime()));

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