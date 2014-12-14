package com.mercy.happybaby.ui.databook;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercy.happybaby.R;
import com.mercy.happybaby.entities.Databook;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.utils.Constants;

public class DatabookAdapter extends
		RecyclerView.Adapter<DatabookAdapter.ViewHolder> {
	String LOG_TAG = DatabookAdapter.class.getName();
	private Context mContext;
	private List<Databook> mList;
	private Typeface roboto_light;

	public DatabookAdapter(Context context, List<Databook> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
		mList = list;
		Log.d(LOG_TAG,"Mlist "+mList.size());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.databook_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		Databook databook = mList.get(position);

		viewHolder.mFirst.setTypeface(roboto_light);
		viewHolder.mSecond.setTypeface(roboto_light);
		viewHolder.mThird.setTypeface(roboto_light);
		viewHolder.mFourth.setTypeface(roboto_light);
		viewHolder.mFifth.setTypeface(roboto_light);
		viewHolder.mSixth.setTypeface(roboto_light);

		viewHolder.mFirst.setVisibility(View.VISIBLE);
		viewHolder.mFirst.setText(databook.getType());
		
		viewHolder.mThird.setVisibility(View.VISIBLE);
		viewHolder.mThird.setText(databook.getName());

		viewHolder.mFifth.setVisibility(View.VISIBLE);
		viewHolder.mFifth.setText(databook.getAddress());

		viewHolder.mSecond.setVisibility(View.VISIBLE);
		viewHolder.mSecond.setText(databook.getPhone1());

		viewHolder.mFourth.setVisibility(View.VISIBLE);
		viewHolder.mFourth.setText(databook.getPhone2());

		viewHolder.mSixth.setVisibility(View.VISIBLE);
		viewHolder.mSixth.setText(databook.getPhone3());

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

		public ViewHolder(View v) {
			super(v);
			mFirst = (TextView) v.findViewById(R.id.first);
			mSecond = (TextView) v.findViewById(R.id.second);
			mThird = (TextView) v.findViewById(R.id.third);
			mFourth = (TextView) v.findViewById(R.id.fourth);
			mFifth = (TextView) v.findViewById(R.id.fifth);
			mSixth = (TextView) v.findViewById(R.id.sixth);
		}
	}
}