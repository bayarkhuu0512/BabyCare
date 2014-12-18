package com.mercy.happybaby.ui.databook;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercy.happybaby.R;
import com.mercy.happybaby.entities.Databook;
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
		Log.d(LOG_TAG, "Mlist " + mList.size());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.databook_row, parent, false);
		return new ViewHolder(v);
	}

	Databook databook = null;

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		databook = mList.get(position);

		viewHolder.name.setTypeface(roboto_light);
		viewHolder.address.setTypeface(roboto_light);
		viewHolder.type.setTypeface(roboto_light);
		viewHolder.phone1.setTypeface(roboto_light);
		viewHolder.phone2.setTypeface(roboto_light);
		viewHolder.phone3.setTypeface(roboto_light);

		viewHolder.name.setVisibility(View.VISIBLE);
		viewHolder.name.setText(databook.getName());

		if (databook.getAddress().length() > 0){
			viewHolder.address.setVisibility(View.VISIBLE);
		} else {
			viewHolder.address.setVisibility(View.GONE);
		}
		viewHolder.address.setText(databook.getAddress());

		viewHolder.type.setVisibility(View.VISIBLE);
		viewHolder.type.setText(databook.getType());

		viewHolder.phone1.setVisibility(View.VISIBLE);
		viewHolder.phone1.setText(databook.getPhone1());
		viewHolder.phone1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri number = Uri.parse("tel:" + databook.getPhone1());
				Intent dial = new Intent(Intent.ACTION_CALL, number);
				mContext.startActivity(dial);
			}
		});

		viewHolder.phone2.setVisibility(View.VISIBLE);
		viewHolder.phone2.setText(databook.getPhone2());
		viewHolder.phone2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri number = Uri.parse("tel:" + databook.getPhone2());
				Intent dial = new Intent(Intent.ACTION_CALL, number);
				mContext.startActivity(dial);
			}
		});
		viewHolder.phone3.setVisibility(View.VISIBLE);
		viewHolder.phone3.setText(databook.getPhone3());
		viewHolder.phone3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri number = Uri.parse("tel:" + databook.getPhone3());
				Intent dial = new Intent(Intent.ACTION_CALL, number);
				mContext.startActivity(dial);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView name;
		public TextView address;
		public TextView type;
		public TextView phone1;
		public TextView phone2;
		public TextView phone3;

		public ViewHolder(View v) {
			super(v);
			name = (TextView) v.findViewById(R.id.name);
			address = (TextView) v.findViewById(R.id.address);
			type = (TextView) v.findViewById(R.id.type);
			phone1 = (TextView) v.findViewById(R.id.phone1);
			phone2 = (TextView) v.findViewById(R.id.phone2);
			phone3 = (TextView) v.findViewById(R.id.phone3);
		}
	}
}