package com.mercy.happybaby.ui.helpcenter;

import java.sql.SQLException;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.melnykov.fab.FloatingActionButton;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.HelpCenter;

public class HelpCenterFragment extends Fragment {
	String LOG_TAG = HelpCenterFragment.class.getName();

	private Dao<HelpCenter, Integer> helpCenterDAO;
	private DatabaseHelper databaseHelper = null;
	List<HelpCenter> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		try {
			helpCenterDAO = getHelper().getHelpCenterDao();
			QueryBuilder<HelpCenter, Integer> helpCenterQb = helpCenterDAO
					.queryBuilder();
			helpCenterQb.orderBy("createdDate", false);
			list = helpCenterQb.query();

			Log.d(LOG_TAG, "List " + list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		View root = inflater.inflate(R.layout.helpcenter_view, container, false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		HelpCenterAdapter adapter = new HelpCenterAdapter(getActivity(), list);
		recyclerView.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) root
				.findViewById(R.id.fab);
		fab.attachToRecyclerView(recyclerView);
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
