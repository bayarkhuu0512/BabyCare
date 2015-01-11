package com.apu.maamuu.ui.purchase;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apu.maamuu.db.DatabaseHelper;
import com.apu.maamuu.entities.Timeline;
import com.apu.maamuu.utils.Constants;
import com.apu.maamuu.utils.DateRangeInstance;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.melnykov.fab.FloatingActionButton;
import com.apu.maamuu.R;

import dreamers.graphics.RippleDrawable;

public class PurchaseFragment extends Fragment {
	String LOG_TAG = PurchaseFragment.class.getName();

	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	List<Timeline> list;
	DateRangeInstance dateRange = DateRangeInstance.getInstance();
	private Typeface roboto_light;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(dateRange.getStartDate());
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);

			timelineDAO = getHelper().getTimelineDao();
			QueryBuilder<Timeline, Integer> timelineQb = timelineDAO
					.queryBuilder();
			Where where = timelineQb.where();
			where.isNotNull("purchase_id");
			where.and();
			where.between("createdDate", c.getTime(), dateRange.getEndDate());
			list = timelineQb.query();

			timelineQb.orderBy("createdDate", false);
			list = timelineQb.query();

			Log.d(LOG_TAG, "List " + list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		View root = inflater.inflate(R.layout.purchase_view, container, false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		PurchaseAdapter adapter = new PurchaseAdapter(getActivity(), list);
		recyclerView.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) root
				.findViewById(R.id.fab);
		fab.attachToRecyclerView(recyclerView);
		TextView startDate = (TextView) root.findViewById(R.id.startDate);
		startDate.setTypeface(roboto_light);
		RippleDrawable.createRipple(startDate,
				getResources().getColor(R.color.mainColor));
		startDate
				.setText(Constants.dateFormat.format(dateRange.getStartDate()));

		TextView endDate = (TextView) root.findViewById(R.id.endDate);
		endDate.setTypeface(roboto_light);
		RippleDrawable.createRipple(endDate,
				getResources().getColor(R.color.mainColor));
		endDate.setText(Constants.dateFormat.format(dateRange.getEndDate()));
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}
