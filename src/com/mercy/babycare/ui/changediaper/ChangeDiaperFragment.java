package com.mercy.babycare.ui.changediaper;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.melnykov.fab.DividerItemDecoration;
import com.melnykov.fab.FloatingActionButton;
import com.mercy.babycare.MainActivity;
import com.mercy.babycare.R;
import com.mercy.babycare.db.DatabaseHelper;
import com.mercy.babycare.entities.Breast;
import com.mercy.babycare.entities.ChangeDiaper;

public class ChangeDiaperFragment extends Fragment {
	String LOG_TAG = ChangeDiaperFragment.class.getName();

	private Dao<ChangeDiaper, Integer> changeDiaperDAO;
	private DatabaseHelper databaseHelper = null;
	List<ChangeDiaper> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		try {
			changeDiaperDAO = getHelper().getChangeDiaperDao();
			QueryBuilder<ChangeDiaper, Integer> changeDiaperQb = changeDiaperDAO
					.queryBuilder();
			changeDiaperQb.orderBy("createdDate", false);
			list = changeDiaperQb.query();

			Log.d(LOG_TAG, "List " + list.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		View root = inflater.inflate(R.layout.changediaper_view, container,
				false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		ChangeDiaperAdapter adapter = new ChangeDiaperAdapter(getActivity(),
				list);
		recyclerView.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) root
				.findViewById(R.id.fab);
		fab.attachToRecyclerView(recyclerView);
		return root;
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
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
