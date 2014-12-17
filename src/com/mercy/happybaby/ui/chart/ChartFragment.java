package com.mercy.happybaby.ui.chart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.melnykov.fab.FloatingActionButton;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.Growth;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.DateRangeInstance;

import dreamers.graphics.RippleDrawable;

public class ChartFragment extends Fragment {
	String LOG_TAG = ChartFragment.class.getName();
	private DatabaseHelper databaseHelper = null;
	List<Breast> mBreastList = null;
	List<Breast> mBreastDateCounterList = null;
	List<Growth> mGrowthList = null;
	DateRangeInstance dateRange = DateRangeInstance.getInstance();
	private Typeface roboto_light;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.chart_view, container, false);
		ListView lv = (ListView) root.findViewById(R.id.listView1);

		Dao<Breast, Integer> breastDAO;
		Dao<Growth, Integer> growthDAO;
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);

		try {
			Calendar c = Calendar.getInstance();
			c.setTime(dateRange.getStartDate());
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);

			breastDAO = getHelper().getBreastDao();
			QueryBuilder<Breast, Integer> breastQb = breastDAO.queryBuilder();
			Where where = breastQb.where();
			where.between("createdDate", c.getTime(), dateRange.getEndDate());
			mBreastList = breastQb.query();

			growthDAO = getHelper().getGrowthDao();
			QueryBuilder<Growth, Integer> growthQb = growthDAO.queryBuilder();
			Where whereGrowth = growthQb.where();
			whereGrowth.between("createdDate", c.getTime(),
					dateRange.getEndDate());
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

		if (mGrowthList != null) {
			list.add(new LineChartItem(weightAgeLine(), getActivity()));
			list.add(new LineChartItem(heightAgeLine(), getActivity()));
			// list.add(new LineChartItem(weightHeightLine(),
			// getActivity()));
		}

		list.add(new BarChartItem(generateDataBar(), getActivity()));
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

	/**
	 * generates a random ChartData object with just one DataSet
	 * 
	 * @return
	 */
	private LineData weightAgeLine() {

		ArrayList<Entry> e1 = new ArrayList<Entry>();
		Log.d(LOG_TAG,"mGrowthList.size() "+mGrowthList.size());
		for (int i = 0; i < mGrowthList.size(); i++) {
			Log.d(LOG_TAG,"mGrowthList i "+mGrowthList.get(i));
			e1.add(new Entry((int) mGrowthList.get(i).getBabyWeight(), i));
		}
		// Baby weight
		LineDataSet d1 = new LineDataSet(e1, getActivity().getResources()
				.getString(R.string.weightAgeLine));
		d1.setLineWidth(2f);
		d1.setCircleSize(4f);
		d1.setColor(getActivity().getResources().getColor(R.color.entity_ao_massage));
		d1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_massage));
		d1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_massage));

		ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
		sets.add(d1);
		// 3
		LineDataSet line3 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_age_line_3_girl.txt"), "3");
		line3.setColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		line3.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		line3.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));

		line3.setLineWidth(1f);
		line3.setCircleSize(0f);
		sets.add(line3);

		// 2
		LineDataSet line2 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_age_line_2_girl.txt"), "2");
		line2.setColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		line2.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		line2.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		line2.setLineWidth(1f);
		line2.setCircleSize(0f);
		sets.add(line2);

		// 1
		LineDataSet line1 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_age_line_1_girl.txt"), "1");
		line1.setColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		line1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		line1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		line1.setLineWidth(1f);
		line1.setCircleSize(0f);
		sets.add(line1);

		// 0
		LineDataSet line0 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_age_line_0_girl.txt"), "0");
		line0.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setHighLightColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setLineWidth(1f);
		line0.setCircleSize(0f);
		sets.add(line0);

		// -1
		LineDataSet lineMinus1 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"weight_age_line_minus_1_girl.txt"), "-1");
		lineMinus1.setColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		lineMinus1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		lineMinus1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		lineMinus1.setLineWidth(1f);
		lineMinus1.setCircleSize(0f);
		sets.add(lineMinus1);

		// -2
		LineDataSet lineMinus2 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"weight_age_line_minus_2_girl.txt"), "-2");
		lineMinus2.setColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		lineMinus2.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		lineMinus2.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		lineMinus2.setLineWidth(1f);
		lineMinus2.setCircleSize(0f);
		sets.add(lineMinus2);

		// -3
		LineDataSet lineMinus3 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"weight_age_line_minus_3_girl.txt"), "-3");
		lineMinus3.setColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		lineMinus3.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		lineMinus3.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		lineMinus3.setLineWidth(1f);
		lineMinus3.setCircleSize(0f);
		sets.add(lineMinus3);

		LineData cd = new LineData(getMonths(), sets);
		return cd;
	}

	private LineData heightAgeLine() {

		ArrayList<Entry> e1 = new ArrayList<Entry>();

		for (int i = 0; i < mGrowthList.size(); i++) {
			e1.add(new Entry((int) mGrowthList.get(i).getBabyHeight(), i));
		}
		// Baby weight
		LineDataSet d1 = new LineDataSet(e1, getActivity().getResources()
				.getString(R.string.heightAgeLine));
		d1.setLineWidth(2f);
		d1.setCircleSize(4f);
		d1.setColor(getActivity().getResources().getColor(R.color.entity_anniversary));
		d1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_anniversary));
		d1.setCircleColor(getActivity().getResources().getColor(R.color.entity_anniversary));

		ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
		sets.add(d1);
		// 3
		LineDataSet line3 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "height_age_line_3_girl.txt"), "3");
		line3.setColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		line3.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		line3.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		line3.setLineWidth(1f);
		line3.setCircleSize(0f);
		sets.add(line3);

		// 2
		LineDataSet line2 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "height_age_line_2_girl.txt"), "2");
		line2.setColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		line2.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		line2.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		line2.setLineWidth(1f);
		line2.setCircleSize(0f);
		sets.add(line2);

		// 1
		LineDataSet line1 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "height_age_line_1_girl.txt"), "1");
		line1.setColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		line1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		line1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		line1.setLineWidth(1f);
		line1.setCircleSize(0f);
		sets.add(line1);

		// 0
		LineDataSet line0 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "height_age_line_0_girl.txt"), "0");
		line0.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setHighLightColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setLineWidth(1f);
		line0.setCircleSize(0f);
		sets.add(line0);

		// -1
		LineDataSet lineMinus1 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"height_age_line_minus_1_girl.txt"), "-1");
		lineMinus1.setColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		lineMinus1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		lineMinus1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_feed_trns));
		lineMinus1.setLineWidth(1f);
		lineMinus1.setCircleSize(0f);
		sets.add(lineMinus1);

		// -2
		LineDataSet lineMinus2 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"height_age_line_minus_2_girl.txt"), "-2");
		lineMinus2.setColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		lineMinus2.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		lineMinus2.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_breast_trns));
		lineMinus2.setLineWidth(1f);
		lineMinus2.setCircleSize(0f);
		sets.add(lineMinus2);

		// -3
		LineDataSet lineMinus3 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"height_age_line_minus_3_girl.txt"), "-3");
		lineMinus3.setColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		lineMinus3.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		lineMinus3.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower_trns));
		lineMinus3.setLineWidth(1f);
		lineMinus3.setCircleSize(0f);
		sets.add(lineMinus3);

		LineData cd = new LineData(getMonths(), sets);
		return cd;
	}

	private LineData weightHeightLine() {

		ArrayList<Entry> e1 = new ArrayList<Entry>();

		for (int i = 0; i < mGrowthList.size(); i++) {
			e1.add(new Entry((int) mGrowthList.get(i).getBabyHeight(), i));
		}

		LineDataSet d1 = new LineDataSet(e1, getActivity().getResources()
				.getString(R.string.weightHeightLine));
		d1.setLineWidth(3f);
		d1.setCircleSize(5f);
		d1.setHighLightColor(getActivity().getResources().getColor(
				R.color.mainColor));
		d1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		d1.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
		sets.add(d1);
		// 3
		LineDataSet line3 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_height_line_3_girl.txt"),
				"3");
		line3.setColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower));
		line3.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower));
		line3.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower));
		line3.setLineWidth(1f);
		line3.setCircleSize(0f);
		sets.add(line3);

		// 2
		LineDataSet line2 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_height_line_2_girl.txt"),
				"2");
		line2.setColor(getActivity().getResources().getColor(
				R.color.entity_breast));
		line2.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_breast));
		line2.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_breast));
		line2.setLineWidth(1f);
		line2.setCircleSize(0f);
		sets.add(line2);

		// 1
		LineDataSet line1 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_height_line_1_girl.txt"),
				"1");
		line1.setColor(getActivity().getResources().getColor(
				R.color.entity_feed));
		line1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_feed));
		line1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_feed));
		line1.setLineWidth(1f);
		line1.setCircleSize(0f);
		sets.add(line1);

		// 0
		LineDataSet line0 = new LineDataSet(FileUtils.loadEntriesFromAssets(
				getActivity().getAssets(), "weight_height_line_0_girl.txt"),
				"0");
		line0.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setHighLightColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		line0.setLineWidth(1f);
		line0.setCircleSize(0f);
		sets.add(line0);

		// -1
		LineDataSet lineMinus1 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"weight_height_line_minus_1_girl.txt"), "-1");
		lineMinus1.setColor(getActivity().getResources().getColor(
				R.color.entity_feed));
		lineMinus1.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_feed));
		lineMinus1.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_feed));
		lineMinus1.setLineWidth(1f);
		lineMinus1.setCircleSize(0f);
		sets.add(lineMinus1);

		// -2
		LineDataSet lineMinus2 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"weight_height_line_minus_2_girl.txt"), "-2");
		lineMinus2.setColor(getActivity().getResources().getColor(
				R.color.entity_breast));
		lineMinus2.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_breast));
		lineMinus2.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_breast));
		lineMinus2.setLineWidth(1f);
		lineMinus2.setCircleSize(0f);
		sets.add(lineMinus2);

		// -3
		LineDataSet lineMinus3 = new LineDataSet(
				FileUtils.loadEntriesFromAssets(getActivity().getAssets(),
						"weight_height_line_minus_3_girl.txt"), "-3");
		lineMinus3.setColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower));
		lineMinus3.setCircleColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower));
		lineMinus3.setHighLightColor(getActivity().getResources().getColor(
				R.color.entity_ao_shower));
		lineMinus3.setLineWidth(1f);
		lineMinus3.setCircleSize(0f);
		sets.add(lineMinus3);

		LineData cd = new LineData(getMonths(), sets);
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

		BarDataSet d = new BarDataSet(entries, getActivity().getResources()
				.getString(R.string.feedNum));
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
		q.add(getActivity().getResources().getString(R.string.breastRight));
		q.add(getActivity().getResources().getString(R.string.breastLeft));
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
		m.add("төрсөн");
		m.add("1");
		m.add("2");
		m.add("3");
		m.add("4");
		m.add("5");
		m.add("6");
		m.add("7");
		m.add("8");
		m.add("9");
		m.add("10");
		m.add("11");
		m.add("12");
		m.add("13");
		m.add("14");
		m.add("15");
		m.add("16");
		m.add("17");
		m.add("18");
		m.add("19");
		m.add("20");
		m.add("21");
		m.add("22");
		m.add("23");
		m.add("24");

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
