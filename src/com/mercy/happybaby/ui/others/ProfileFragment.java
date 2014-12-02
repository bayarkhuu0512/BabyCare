package com.mercy.happybaby.ui.others;

import java.sql.SQLException;
import java.util.List;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.R;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.Baby;
import com.mercy.happybaby.entities.Growth;
import com.mercy.happybaby.entities.Learn;
import com.mercy.happybaby.entities.Tooth;
import com.mercy.happybaby.utils.Constants;

public class ProfileFragment extends Fragment {
	private TextView babyName;
	private TextView babyAge;
	private TextView babyHeightLabel;
	private TextView babyHeight;
	private TextView babyWeightLabel;
	private TextView babyWeight;
	private TextView babyToothLabel;
	private TextView babyTooth;
	private TextView latestSkillLabel;
	private TextView latestSkill;
	private Typeface roboto_light;

	private Dao<Baby, Integer> babyDAO;
	private Dao<Growth, Integer> growthDAO;
	private Dao<Tooth, Integer> toothDAO;
	private Dao<Learn, Integer> learnDAO;

	private List<Baby> babyList = null;
	private List<Growth> growthList = null;
	private List<Tooth> toothList = null;
	private List<Learn> learnList = null;

	private DatabaseHelper databaseHelper = null;

	public ProfileFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		roboto_light = Typeface.createFromAsset(getActivity().getAssets(),
				Constants.ROBOTO_LIGHT);

		// Inflate the layout for this fragment
		View root = inflater.inflate(R.layout.profile_view, container, false);

		babyName = (TextView) root.findViewById(R.id.babyName);
		babyAge = (TextView) root.findViewById(R.id.babyAge);
		babyHeightLabel = (TextView) root.findViewById(R.id.babyHeightLabel);
		babyHeight = (TextView) root.findViewById(R.id.babyHeight);
		babyWeightLabel = (TextView) root.findViewById(R.id.babyWeightLabel);
		babyWeight = (TextView) root.findViewById(R.id.babyWeight);
		babyToothLabel = (TextView) root.findViewById(R.id.babyToothLabel);
		babyTooth = (TextView) root.findViewById(R.id.babyTooth);
		latestSkillLabel = (TextView) root.findViewById(R.id.latestSkillLabel);
		latestSkill = (TextView) root.findViewById(R.id.latestSkill);

		babyName.setTypeface(roboto_light);
		babyAge.setTypeface(roboto_light);
		babyHeightLabel.setTypeface(roboto_light);
		babyHeight.setTypeface(roboto_light);
		babyWeightLabel.setTypeface(roboto_light);
		babyWeight.setTypeface(roboto_light);
		babyToothLabel.setTypeface(roboto_light);
		babyTooth.setTypeface(roboto_light);
		latestSkillLabel.setTypeface(roboto_light);
		latestSkill.setTypeface(roboto_light);

		try {
			babyDAO = getHelper().getBabyDao();
			babyList = babyDAO.queryForAll();

			babyName.setText(babyList.get(0).getLastName().substring(0, 1)
					+ "." + babyList.get(0).getFirstName());
			babyAge.setText(Constants.dateFormat.format(babyList.get(0)
					.getBirthDate()) + "");

			growthDAO = getHelper().getGrowthDao();
			growthList = growthDAO.queryForAll();
			babyHeight.setText(growthList.get(growthList.size() - 1)
					.getBabyHeight() + "см");
			babyWeight.setText(growthList.get(growthList.size() - 1)
					.getBabyWeight() + "гр");

			toothDAO = getHelper().getToothDao();
			toothList = toothDAO.queryForAll();
			babyTooth.setText(toothList.size() + "ш");

			learnDAO = getHelper().getLearnDao();
			learnList = learnDAO.queryForAll();
			latestSkill.setText(learnList.get(learnList.size() - 1)
					.getLearnName() + "");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
}
