package com.mercy.babycare.ui.tooth;

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

import com.melnykov.fab.DividerItemDecoration;
import com.melnykov.fab.FloatingActionButton;
import com.mercy.babycare.MainActivity;
import com.mercy.babycare.R;

public class ToothFragment extends Fragment {
	String LOG_TAG = ToothFragment.class.getName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.tooth_view, container, false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		// recyclerView.addItemDecoration(new
		// DividerItemDecoration(getActivity(),
		// DividerItemDecoration.VERTICAL_LIST));

		ToothAdapter adapter = new ToothAdapter(getActivity(),
				getResources().getStringArray(R.array.nav_array));
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
	
}
