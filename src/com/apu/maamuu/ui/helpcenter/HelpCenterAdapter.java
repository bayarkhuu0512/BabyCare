package com.apu.maamuu.ui.helpcenter;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apu.maamuu.entities.HelpCenter;
import com.apu.maamuu.utils.Constants;
import com.apu.maamuu.R;

public class HelpCenterAdapter extends
		RecyclerView.Adapter<HelpCenterAdapter.ViewHolder> {
	String LOG_TAG = HelpCenterAdapter.class.getName();
	private Context mContext;
	private List<HelpCenter> mList;
	private Typeface roboto_light;

	public HelpCenterAdapter(Context context, List<HelpCenter> list) {
		mContext = context;
		roboto_light = Typeface.createFromAsset(mContext.getAssets(),
				Constants.ROBOTO_LIGHT);
		mList = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.helpcenter_row, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		HelpCenter helpCenter = mList.get(position);
		viewHolder.helpCenter.setTypeface(roboto_light);
		viewHolder.title.setTypeface(roboto_light);
		viewHolder.content.setTypeface(roboto_light);
		viewHolder.helpCenter.setText(mContext.getResources().getString(R.string.helpCenter) + " " + helpCenter.getId());
		viewHolder.title.setText(helpCenter.getHelpCenterTitle());
		viewHolder.content.setText(helpCenter.getHelpCenterContent());
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView helpCenter;
		public TextView title;
		public TextView content;

		public ViewHolder(View v) {
			super(v);
			helpCenter = (TextView) v.findViewById(R.id.helpCenter);
			title = (TextView) v.findViewById(R.id.title);
			content = (TextView) v.findViewById(R.id.content);

		}
	}
}