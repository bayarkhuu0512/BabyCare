package com.mercy.babycare.ui.chart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.melnykov.fab.FloatingActionButton;
import com.mercy.babycare.R;
import com.mercy.babycare.db.DatabaseHelper;
import com.mercy.babycare.entities.Breast;
import com.mercy.babycare.entities.Growth;
import com.mercy.babycare.entities.Timeline;

public class ChartFragment extends Fragment {
	String LOG_TAG = ChartFragment.class.getName();
	private DatabaseHelper databaseHelper = null;
	List<Breast> mBreastList = null;
	List<Breast> mBreastDateCounterList = null;
	List<Growth> mGrowthList = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.chart_view, container, false);
		ListView lv = (ListView) root.findViewById(R.id.listView1);

		Dao<Breast, Integer> breastDAO;
		Dao<Growth, Integer> growthDAO;

		try {
			breastDAO = getHelper().getBreastDao();
			QueryBuilder<Breast, Integer> breastQb = breastDAO.queryBuilder();
			mBreastList = breastQb.query();

			growthDAO = getHelper().getGrowthDao();
			QueryBuilder<Growth, Integer> growthQb = growthDAO.queryBuilder();
			mGrowthList = growthQb.query();

			// QueryBuilder<Breast, Integer> breastDateQb =
			// breastDAO.queryBuilder();
			// mBreastDateCounterList = breastDateQb.query();
			//
			// Log.d(LOG_TAG,"mBreastDateCounterList "+mBreastDateCounterList.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<ChartItem> list = new ArrayList<ChartItem>();
		if (mBreastList != null) {
			list.add(new PieChartItem(generateDataBreastPie(), getActivity()));
		}
		list.add(new BarChartItem(generateDataBar(), getActivity()));

		if (mGrowthList != null) {
			list.add(new LineChartItem(generateGrowthWeightLine(),
					getActivity()));
		}
		if (mGrowthList != null) {
			list.add(new LineChartItem(generateGrowthHeightLine(),
					getActivity()));
		}

		// for (int i = 0; i < 3; i++) {
		//
		// if (i % 3 == 0) {
		// list.add(new LineChartItem(generateDataLine(i + 1),
		// getActivity()));
		// } else if (i % 3 == 1) {
		// // list.add(new BarChartItem(generateDataBar(i + 1), getActivity()));
		// } else if (i % 3 == 2) {
		// list.add(new PieChartItem(generateDataPie(i + 1), getActivity()));
		// }
		// }

		ChartAdapter adapter = new ChartAdapter(getActivity(), list);
		lv.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) root
				.findViewById(R.id.fab);
		fab.attachToListView(lv);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * generates a random ChartData object with just one DataSet
	 * 
	 * @return
	 */
	private LineData generateGrowthWeightLine() {

		ArrayList<Entry> e1 = new ArrayList<Entry>();

		for (int i = 0; i < mGrowthList.size(); i++) {
			e1.add(new Entry((int) mGrowthList.get(i).getBabyWeight(), i));
		}

		LineDataSet d1 = new LineDataSet(e1, "Жингийн өсөлт ");
		d1.setLineWidth(3f);
		d1.setCircleSize(5f);
		d1.setHighLightColor(getActivity().getResources().getColor(
				R.color.mainColor));
		ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
		sets.add(d1);
		LineData cd = new LineData(getMonths(mGrowthList), sets);
		return cd;
	}

	private LineData generateGrowthHeightLine() {

		ArrayList<Entry> e1 = new ArrayList<Entry>();

		for (int i = 0; i < mGrowthList.size(); i++) {
			e1.add(new Entry((int) mGrowthList.get(i).getBabyHeight(), i));
		}

		LineDataSet d1 = new LineDataSet(e1, "Өндрийн өсөлт ");
		d1.setLineWidth(3f);
		d1.setCircleSize(5f);
		d1.setHighLightColor(getActivity().getResources().getColor(
				R.color.mainColor));
		d1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		d1.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
		sets.add(d1);
		LineData cd = new LineData(getMonths(mGrowthList), sets);
		return cd;
	}

	private PieData generateDataBreastPie() {

		ArrayList<Entry> entries = new ArrayList<Entry>();

		int numIsRight = 0;
		int numIsLeft = 0;
		for (int i = 0; i < mBreastList.size(); i++) {
			if (mBreastList.get(i).isRight()) {
				numIsRight++;
			} else {
				numIsLeft++;
			}
		}

		entries.add(new Entry((int) numIsRight, 0));
		entries.add(new Entry((int) numIsLeft, 1));

		PieDataSet d = new PieDataSet(entries, "");

		// space between slices
		d.setSliceSpace(5f);
		int[] COLORS = {
				getActivity().getResources().getColor(R.color.mainColor),
				getActivity().getResources().getColor(R.color.entity_breast) };
		d.setColors(COLORS);

		PieData cd = new PieData(getBreastSections(), d);
		return cd;
	}

	/**
	 * generates a random ChartData object with just one DataSet
	 * 
	 * @return
	 */
	private BarData generateDataBar() {

		ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

		for (int i = 0; i < 12; i++) {
			entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
		}

		BarDataSet d = new BarDataSet(entries, "Хооллолтын тоо ");
		d.setBarSpacePercent(20f);
		int[] VORDIPLOM_COLORS = {
				getActivity().getResources().getColor(R.color.entity_breast),
				getActivity().getResources().getColor(R.color.entity_feed),
				getActivity().getResources().getColor(R.color.entity_formula),
				getActivity().getResources().getColor(R.color.entity_drink) };

		d.setColors(VORDIPLOM_COLORS);
		d.setHighLightAlpha(255);

		BarData cd = new BarData(getMonths(), d);
		return cd;
	}

	/**
	 * generates a random ChartData object with just one DataSet
	 * 
	 * @return
	 */
	// private PieData generateDataPie(int cnt) {
	//
	// ArrayList<Entry> entries = new ArrayList<Entry>();
	//
	// for (int i = 0; i < 4; i++) {
	// entries.add(new Entry((int) (Math.random() * 70) + 30, i));
	// }
	//
	// PieDataSet d = new PieDataSet(entries, "");
	//
	// // space between slices
	// d.setSliceSpace(5f);
	// d.setColors(ColorTemplate.VORDIPLOM_COLORS);
	//
	// PieData cd = new PieData(getQuarters(), d);
	// return cd;
	// }

	// private ArrayList<String> getQuarters() {
	//
	// ArrayList<String> q = new ArrayList<String>();
	// q.add("1st Quarter");
	// q.add("2nd Quarter");
	// q.add("3rd Quarter");
	// q.add("4th Quarter");
	//
	// return q;
	// }

	private ArrayList<String> getBreastSections() {

		ArrayList<String> q = new ArrayList<String>();
		q.add("Баруун");
		q.add("Зүүн");

		return q;
	}

	private ArrayList<String> getMonths(List<Growth> mList) {
		ArrayList<String> m = new ArrayList<String>();
		for (int i = 0; i < mList.size(); i++) {
			m.add((mList.get(i).getBabyMonth() + 1) + "");
		}

		/*
		 * ArrayList<String> m = new ArrayList<String>(); m.add("Jan");
		 * m.add("Feb"); m.add("Mar"); m.add("Apr"); m.add("May"); m.add("Jun");
		 * m.add("Jul"); m.add("Aug"); m.add("Sep"); m.add("Okt"); m.add("Nov");
		 * m.add("Dec");
		 */
		return m;
	}

	private ArrayList<String> getMonths() {

		ArrayList<String> m = new ArrayList<String>();
		m.add("Jan");
		m.add("Feb");
		m.add("Mar");
		m.add("Apr");
		m.add("May");
		m.add("Jun");
		m.add("Jul");
		m.add("Aug");
		m.add("Sep");
		m.add("Oct");
		m.add("Nov");
		m.add("Dec");

		return m;
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
