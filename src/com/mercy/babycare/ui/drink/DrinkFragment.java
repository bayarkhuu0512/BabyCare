package com.mercy.babycare.ui.feed;

import android.app.Fragment;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercy.babycare.R;

public class DrinkFragment extends Fragment {
	
	public DrinkFragment() {
		// Empty constructor required for fragment subclasses
	}
=======
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

public class DrinkFragment extends Fragment {
	String LOG_TAG = DrinkFragment.class.getName();
>>>>>>> origin/master

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
<<<<<<< HEAD
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.drink_view, container, false);
=======
		View root = inflater.inflate(R.layout.timeline_view, container, false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		// recyclerView.addItemDecoration(new
		// DividerItemDecoration(getActivity(),
		// DividerItemDecoration.VERTICAL_LIST));

		DrinkAdapter adapter = new DrinkAdapter(getActivity(),
				getResources().getStringArray(R.array.nav_array));
		recyclerView.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) root
				.findViewById(R.id.fab);
		fab.attachToRecyclerView(recyclerView);
		return root;
>>>>>>> origin/master
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
<<<<<<< HEAD
=======
	
>>>>>>> origin/master
}
