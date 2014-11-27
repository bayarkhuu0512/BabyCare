package com.mercy.babycare.ui.chart;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
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
import com.melnykov.fab.FloatingActionButton;
import com.mercy.babycare.R;

public class ChartFragment extends Fragment {
	String LOG_TAG = ChartFragment.class.getName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.chart_view, container,
				false);
		ListView lv = (ListView) root.findViewById(R.id.listView1);

		ArrayList<ChartItem> list = new ArrayList<ChartItem>();
		// 30 items
		for (int i = 0; i < 30; i++) {

			if (i % 3 == 0) {
				list.add(new LineChartItem(generateDataLine(i + 1),
						getActivity()));
			} else if (i % 3 == 1) {
				list.add(new BarChartItem(generateDataBar(i + 1), getActivity()));
			} else if (i % 3 == 2) {
				list.add(new PieChartItem(generateDataPie(i + 1), getActivity()));
			}
		}

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
	private LineData generateDataLine(int cnt) {

		ArrayList<Entry> e1 = new ArrayList<Entry>();

		for (int i = 0; i < 12; i++) {
			e1.add(new Entry((int) (Math.random() * 65) + 40, i));
		}

		LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
		d1.setLineWidth(3f);
		d1.setCircleSize(5f);
		d1.setHighLightColor(Color.rgb(244, 117, 117));

		ArrayList<Entry> e2 = new ArrayList<Entry>();

		for (int i = 0; i < 12; i++) {
			e2.add(new Entry(e1.get(i).getVal() - 30, i));
		}

		LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
		d2.setLineWidth(3f);
		d2.setCircleSize(5f);
		d2.setHighLightColor(Color.rgb(244, 117, 117));
		d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
		d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);

		ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
		sets.add(d1);
		sets.add(d2);

		LineData cd = new LineData(getMonths(), sets);
		return cd;
	}

	/**
	 * generates a random ChartData object with just one DataSet
	 * 
	 * @return
	 */
	private BarData generateDataBar(int cnt) {

		ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

		for (int i = 0; i < 12; i++) {
			entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
		}

		BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
		d.setBarSpacePercent(20f);
		d.setColors(ColorTemplate.VORDIPLOM_COLORS);
		d.setHighLightAlpha(255);

		BarData cd = new BarData(getMonths(), d);
		return cd;
	}

	/**
	 * generates a random ChartData object with just one DataSet
	 * 
	 * @return
	 */
	private PieData generateDataPie(int cnt) {

		ArrayList<Entry> entries = new ArrayList<Entry>();

		for (int i = 0; i < 4; i++) {
			entries.add(new Entry((int) (Math.random() * 70) + 30, i));
		}

		PieDataSet d = new PieDataSet(entries, "");

		// space between slices
		d.setSliceSpace(5f);
		d.setColors(ColorTemplate.VORDIPLOM_COLORS);

		PieData cd = new PieData(getQuarters(), d);
		return cd;
	}

	private ArrayList<String> getQuarters() {

		ArrayList<String> q = new ArrayList<String>();
		q.add("1st Quarter");
		q.add("2nd Quarter");
		q.add("3rd Quarter");
		q.add("4th Quarter");

		return q;
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
		m.add("Okt");
		m.add("Nov");
		m.add("Dec");

		return m;
	}
}
