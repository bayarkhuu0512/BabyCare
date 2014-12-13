package com.mercy.happybaby.ui.databook;

import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.melnykov.fab.FloatingActionButton;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Databook;

public class DatabookFragment extends Fragment {
	String LOG_TAG = DatabookFragment.class.getName();

	private DatabaseHelper databaseHelper = null;
	private JSONObject json = null;
	private List<Databook> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.databook_view, container, false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		if (list != null) {
			DatabookAdapter adapter = new DatabookAdapter(getActivity(), list);
			recyclerView.setAdapter(adapter);
		}
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
