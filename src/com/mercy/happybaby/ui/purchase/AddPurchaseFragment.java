package com.mercy.happybaby.ui.purchase;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Purchase;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.ui.timeline.TimelineFragment;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.crouton.CroutonMessage;
import com.mercy.happybaby.utils.crouton.Style;

import dreamers.graphics.RippleDrawable;

public class AddPurchaseFragment extends Fragment  implements
CalendarDatePickerDialog.OnDateSetListener {
	String LOG_TAG = AddPurchaseFragment.class.getName();

	private TextView timePurchase;
	private ListView purchaseList;
	private String[] purchaseNames;

	private CroutonMessage crtnMsg = null;
	private Dao<Timeline, Integer> timelineDAO;
	private DatabaseHelper databaseHelper = null;
	Calendar cal = Calendar.getInstance();
	private Typeface roboto_light;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);
		
		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.add_purchase, container, false);
		crtnMsg = new CroutonMessage(getActivity());
		try {
			timelineDAO = getHelper().getTimelineDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timePurchase = (TextView) root.findViewById(R.id.timePurchase);
		timePurchase.setTypeface(roboto_light);
		timePurchase.setText(Constants.timeFormat.format(cal.getTime()) + "");
		timePurchase.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "timeFeed");
			}
		});

		purchaseNames = getResources().getStringArray(R.array.vitamin_array);
		purchaseList = (ListView) root.findViewById(R.id.purchaseList);
		purchaseList.setAdapter(new PurchaseListAdapter(getActivity(), purchaseNames));

		ImageButton close = (ImageButton) root.findViewById(R.id.close);
		RippleDrawable.createRipple(close,
				getResources().getColor(android.R.color.white));
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "close");
				gotoTimeline();
			}
		});
		ImageButton save = (ImageButton) root.findViewById(R.id.save);
		RippleDrawable.createRipple(save,
				getResources().getColor(android.R.color.white));
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "save");
				// if (isSelectedRight) {
				// addBreast(true);
				// } else if (isSelectedLeft) {
				// addBreast(false);
				// } else {
				// crtnMsg.hide();
				// crtnMsg.showCrouton(Style.CONFIRM, getActivity()
				// .getResources().getString(R.string.pleaseselect));
				// }
			}
		});

		return root;
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}
	
	private void addVitamin(boolean isRight) {
		crtnMsg.hide();
		crtnMsg.showCrouton(Style.INFO,
				getActivity().getResources().getString(R.string.success));
		Purchase purchase1 = new Purchase();
		Calendar purchaseCal1 = Calendar.getInstance();
		purchase1.setCreatedDate(purchaseCal1.getTime());
		purchase1.setPurchaseName("Wakodo сүү");
		purchase1.setPurchaseAmount(1);
		purchase1.setPurchasePrice(46000);

		Timeline timelinePurchase1 = new Timeline();
		timelinePurchase1.setPurchase(purchase1);
		timelinePurchase1.setCreatedDate(purchaseCal1.getTime());
		try {
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelinePurchase1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dateRange.setEndDate(purchaseCal1.getTime());
		gotoTimeline();
	}

	private void gotoTimeline() {
		TimelineFragment fragment = new TimelineFragment();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.animator.slide_up,
						R.animator.slide_down, R.animator.slide_up,
						R.animator.slide_down)
				.replace(R.id.content_frame, fragment).commit();
		
		getActivity().setTitle(getActivity().getTitle());
		getActivity().getActionBar().show();
	}

	public class PurchaseListAdapter extends ArrayAdapter<String> {

		public PurchaseListAdapter(Context context, String[] vitaminNames) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.add_purchase_item, vitaminNames);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.add_purchase_item, parent,
					false);
			TextView vitaminName = (TextView) convertView
					.findViewById(R.id.purchaseName);
			ImageView vitaminIcon = (ImageView) convertView
					.findViewById(R.id.purchaseIcon);
			vitaminName.setText(purchaseNames[position] + "");
			return convertView;

		}
	}
}
