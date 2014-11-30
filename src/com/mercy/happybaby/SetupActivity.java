package com.mercy.happybaby;

import java.sql.SQLException;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mercy.happybaby.db.DatabaseHelper;
import com.mercy.happybaby.entities.ActiveOperation;
import com.mercy.happybaby.entities.Baby;
import com.mercy.happybaby.entities.Breast;
import com.mercy.happybaby.entities.ChangeDiaper;
import com.mercy.happybaby.entities.Drink;
import com.mercy.happybaby.entities.Feed;
import com.mercy.happybaby.entities.Formula;
import com.mercy.happybaby.entities.Growth;
import com.mercy.happybaby.entities.HelpCenter;
import com.mercy.happybaby.entities.Hospital;
import com.mercy.happybaby.entities.Learn;
import com.mercy.happybaby.entities.Purchase;
import com.mercy.happybaby.entities.Timeline;
import com.mercy.happybaby.entities.Tooth;
import com.mercy.happybaby.utils.Constants;
import com.mercy.happybaby.utils.PrefManager;

public class SetupActivity extends FragmentActivity {
	String LOG_TAG = SetupActivity.class.getName();

	private PrefManager prefManager;
	private DatabaseHelper databaseHelper = null;
	private Intent intent;
	private Timeline timeline;
	private Baby baby;

	private Dao<Timeline, Integer> timelineDAO;
	private Dao<HelpCenter, Integer> helpCenterDAO;

	private TextView initTextView;
	private Typeface roboto_light;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		prefManager = new PrefManager(this);
		if (prefManager.getHasSetup()) {
			launchMainActivity();
		} else {
			setContentView(R.layout.fragment_setup);
			initTextView = (TextView) findViewById(R.id.initTextView);
			roboto_light = Typeface.createFromAsset(getAssets(),
					Constants.ROBOTO_LIGHT);
			initTextView.setTypeface(roboto_light);
			// Init UIs
			new LongOperation().execute("");
		}
	}

	private class LongOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			setDateData();
			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
			prefManager.setHasSetup(true);
			launchMainActivity();
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

	public void nextOnClick(View v) {

	}

	private void launchMainActivity() {
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		finish();
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	private void setDateData() {
		try {
			// Baby
			baby = new Baby();
			baby.setFirstName("Аядуу");
			baby.setLastName("Баярхүү");

			Calendar cal = Calendar.getInstance();
			cal.set(2014, 1, 5, 8, 38);
			baby.setBirthDate(cal.getTime());
			baby.setBirthTime(cal.getTime());
			baby.setBirthHeadCirc(35f);
			baby.setBirthHeight(51f);
			baby.setBirthWeight(3500f);
			baby.setDoctorName("Oyungerel");
			baby.setGender(getResources().getString(R.string.gender_girl));
			baby.setHospitalName(getResources().getString(
					R.string.hospital_name));
			timeline = new Timeline();
			timeline.setBaby(baby);
			timeline.setCreatedDate(cal.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timeline);

			// Tooth
			Tooth tooth1 = new Tooth();
			Calendar cal1 = Calendar.getInstance();
			cal1.set(2014, 6, 11, 2, 38);
			tooth1.setCreatedDate(cal1.getTime());
			tooth1.setToothNum(1);

			Timeline timelineTooth1 = new Timeline();
			timelineTooth1.setTooth(tooth1);
			timelineTooth1.setCreatedDate(cal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth1);

			Tooth tooth2 = new Tooth();
			Calendar cal2 = Calendar.getInstance();
			cal2.set(2014, 6, 20, 8, 38);
			tooth2.setCreatedDate(cal2.getTime());
			tooth2.setToothNum(2);

			Timeline timelineTooth2 = new Timeline();
			timelineTooth2.setTooth(tooth2);
			timelineTooth2.setCreatedDate(cal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth2);

			Tooth tooth3 = new Tooth();
			Calendar cal3 = Calendar.getInstance();
			cal3.set(2014, 9, 16, 8, 38);
			tooth3.setCreatedDate(cal3.getTime());
			tooth3.setToothNum(3);

			Timeline timelineTooth3 = new Timeline();
			timelineTooth3.setTooth(tooth3);
			timelineTooth3.setCreatedDate(cal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth3);

			Tooth tooth4 = new Tooth();
			Calendar cal4 = Calendar.getInstance();
			cal4.set(2014, 9, 21, 8, 38);
			tooth4.setCreatedDate(cal4.getTime());
			tooth4.setToothNum(4);

			Timeline timelineTooth4 = new Timeline();
			timelineTooth4.setTooth(tooth4);
			timelineTooth4.setCreatedDate(cal4.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineTooth4);

			Learn learn1 = new Learn();
			Calendar learnCal1 = Calendar.getInstance();
			learnCal1.set(2014, 01, 06, 8, 38);
			learn1.setCreatedDate(learnCal1.getTime());
			learn1.setLearnName("Гэртээ ирлээ");

			Learn learn2 = new Learn();
			Calendar learnCal2 = Calendar.getInstance();
			learnCal2.set(2014, 03, 21, 8, 38);
			learn2.setCreatedDate(learnCal2.getTime());
			learn2.setLearnName("Анх толгойгоо өргөлөө");

			Learn learn3 = new Learn();
			Calendar learnCal3 = Calendar.getInstance();
			learnCal3.set(2014, 04, 12, 8, 38);
			learn3.setCreatedDate(learnCal3.getTime());
			learn3.setLearnName("Эргэж хөрвөөлөө");

			Learn learn4 = new Learn();
			Calendar learnCal4 = Calendar.getInstance();
			learnCal4.set(2014, 8, 20, 8, 38);
			learn4.setCreatedDate(learnCal4.getTime());
			learn4.setLearnName("Суудаг боллоо");

			Timeline timelineLearn1 = new Timeline();
			timelineLearn1.setLearn(learn1);
			timelineLearn1.setCreatedDate(learnCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineLearn1);

			Timeline timelineLearn2 = new Timeline();
			timelineLearn2.setLearn(learn2);
			timelineLearn2.setCreatedDate(learnCal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineLearn2);

			Timeline timelineLearn3 = new Timeline();
			timelineLearn3.setLearn(learn3);
			timelineLearn3.setCreatedDate(learnCal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineLearn3);

			Timeline timelineLearn4 = new Timeline();
			timelineLearn4.setLearn(learn4);
			timelineLearn4.setCreatedDate(learnCal4.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineLearn4);

			Growth growth1 = new Growth();
			Calendar growthCal1 = Calendar.getInstance();
			growthCal1.set(2014, 2, 05, 8, 38);
			growth1.setCreatedDate(growthCal1.getTime());
			growth1.setBabyHeight(54);
			growth1.setBabyWeight(4100);
			growth1.setBabyMonth(1);
			Timeline growthTimeline1 = new Timeline();
			growthTimeline1.setGrowth(growth1);
			growthTimeline1.setCreatedDate(growthCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline1);

			Growth growth2 = new Growth();
			Calendar growthCal2 = Calendar.getInstance();
			growthCal2.set(2014, 3, 05, 8, 38);
			growth2.setCreatedDate(growthCal2.getTime());
			growth2.setBabyHeight(58);
			growth2.setBabyWeight(5400);
			growth2.setBabyMonth(2);
			Timeline growthTimeline2 = new Timeline();
			growthTimeline2.setGrowth(growth2);
			growthTimeline2.setCreatedDate(growthCal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline2);

			Growth growth3 = new Growth();
			Calendar growthCal3 = Calendar.getInstance();
			growthCal3.set(2014, 4, 05, 8, 38);
			growth3.setCreatedDate(growthCal3.getTime());
			growth3.setBabyHeight(60);
			growth3.setBabyWeight(6500);
			growth3.setBabyMonth(3);
			Timeline growthTimeline3 = new Timeline();
			growthTimeline3.setGrowth(growth3);
			growthTimeline3.setCreatedDate(growthCal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline3);

			Growth growth4 = new Growth();
			Calendar growthCal4 = Calendar.getInstance();
			growthCal4.set(2014, 5, 05, 8, 38);
			growth4.setCreatedDate(growthCal4.getTime());
			growth4.setBabyHeight(62);
			growth4.setBabyWeight(7200);
			growth4.setBabyMonth(4);
			Timeline growthTimeline4 = new Timeline();
			growthTimeline4.setGrowth(growth4);
			growthTimeline4.setCreatedDate(growthCal4.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline4);

			Growth growth5 = new Growth();
			Calendar growthCal5 = Calendar.getInstance();
			growthCal5.set(2014, 6, 05, 8, 38);
			growth5.setCreatedDate(growthCal5.getTime());
			growth5.setBabyHeight(63);
			growth5.setBabyWeight(7600);
			growth5.setBabyMonth(5);
			Timeline growthTimeline5 = new Timeline();
			growthTimeline5.setGrowth(growth5);
			growthTimeline5.setCreatedDate(growthCal5.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline5);

			Growth growth6 = new Growth();
			Calendar growthCal6 = Calendar.getInstance();
			growthCal6.set(2014, 7, 05, 8, 38);
			growth6.setCreatedDate(growthCal6.getTime());
			growth6.setBabyHeight(65);
			growth6.setBabyWeight(7800);
			growth6.setBabyMonth(6);
			Timeline growthTimeline6 = new Timeline();
			growthTimeline6.setGrowth(growth6);
			growthTimeline6.setCreatedDate(growthCal6.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline6);

			Growth growth7 = new Growth();
			Calendar growthCal7 = Calendar.getInstance();
			growthCal7.set(2014, 8, 05, 8, 38);
			growth7.setCreatedDate(growthCal7.getTime());
			growth7.setBabyHeight(66);
			growth7.setBabyWeight(8100);
			growth7.setBabyMonth(7);
			Timeline growthTimeline7 = new Timeline();
			growthTimeline7.setGrowth(growth7);
			growthTimeline7.setCreatedDate(growthCal7.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline7);

			Growth growth8 = new Growth();
			Calendar growthCal8 = Calendar.getInstance();
			growthCal8.set(2014, 9, 05, 8, 38);
			growth8.setCreatedDate(growthCal8.getTime());
			growth8.setBabyHeight(67);
			growth8.setBabyWeight(8200);
			growth8.setBabyMonth(8);
			Timeline growthTimeline8 = new Timeline();
			growthTimeline8.setGrowth(growth8);
			growthTimeline8.setCreatedDate(growthCal8.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline8);

			Growth growth9 = new Growth();
			Calendar growthCal9 = Calendar.getInstance();
			growthCal9.set(2014, 10, 05, 8, 38);
			growth9.setCreatedDate(growthCal9.getTime());
			growth9.setBabyHeight(69);
			growth9.setBabyWeight(8700);
			growth9.setBabyMonth(9);
			Timeline growthTimeline9 = new Timeline();
			growthTimeline9.setGrowth(growth9);
			growthTimeline9.setCreatedDate(growthCal9.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(growthTimeline9);

			// Meals
			Breast breast1 = new Breast();
			Calendar breastCal1 = Calendar.getInstance();
			breastCal1.set(2014, 3, 27, 12, 20);
			breast1.setCreatedDate(breastCal1.getTime());
			breast1.setBreastTime(breastCal1.getTime());
			breast1.setRight(true);

			Breast breast2 = new Breast();
			Calendar breastCal2 = Calendar.getInstance();
			breastCal2.set(2014, 9, 13, 14, 04);
			breast2.setCreatedDate(breastCal2.getTime());
			breast2.setBreastTime(breastCal2.getTime());
			breast2.setRight(false);

			Breast breast3 = new Breast();
			Calendar breastCal3 = Calendar.getInstance();
			breastCal3.set(2014, 10, 17, 18, 31);
			breast3.setCreatedDate(breastCal3.getTime());
			breast3.setBreastTime(breastCal3.getTime());
			breast3.setRight(true);

			Timeline timelineBreast1 = new Timeline();
			timelineBreast1.setBreast(breast1);
			timelineBreast1.setCreatedDate(breastCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineBreast1);

			Timeline timelineBreast2 = new Timeline();
			timelineBreast2.setBreast(breast2);
			timelineBreast2.setCreatedDate(breastCal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineBreast2);

			Timeline timelineBreast3 = new Timeline();
			timelineBreast3.setBreast(breast3);
			timelineBreast3.setCreatedDate(breastCal3.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineBreast3);

			Feed feed1 = new Feed();
			Calendar feedCal1 = Calendar.getInstance();
			feedCal1.set(2014, 4, 12, 18, 31);
			feed1.setCreatedDate(feedCal1.getTime());
			feed1.setMl(200);
			feed1.setFeedName("Бантан");

			Timeline timelineFeed1 = new Timeline();
			timelineFeed1.setFeed(feed1);
			timelineFeed1.setCreatedDate(feedCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineFeed1);

			Feed feed2 = new Feed();
			Calendar feedCal2 = Calendar.getInstance();
			feedCal2.set(2014, 5, 12, 18, 31);
			feed2.setCreatedDate(feedCal2.getTime());
			feed2.setAmount(2);
			feed2.setFeedName("Жимс");

			Timeline timelineFeed2 = new Timeline();
			timelineFeed2.setFeed(feed2);
			timelineFeed2.setCreatedDate(feedCal2.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineFeed2);

			Formula formula1 = new Formula();
			Calendar formulaCal1 = Calendar.getInstance();
			formulaCal1.set(2014, 5, 12, 18, 31);
			formula1.setCreatedDate(formulaCal1.getTime());
			formula1.setFormulaName("Wakodo");
			formula1.setMl(200);

			Timeline timelineFormula1 = new Timeline();
			timelineFormula1.setFormula(formula1);
			timelineFormula1.setCreatedDate(formulaCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineFormula1);

			Drink drink1 = new Drink();
			Calendar drinkCal1 = Calendar.getInstance();
			drinkCal1.set(2014, 6, 14, 16, 12);
			drink1.setCreatedDate(drinkCal1.getTime());
			drink1.setDrinkName("Липтон");
			drink1.setMl(150);

			Timeline timelineDrink1 = new Timeline();
			timelineDrink1.setDrink(drink1);
			timelineDrink1.setCreatedDate(drinkCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineDrink1);

			ChangeDiaper changeDiaper1 = new ChangeDiaper();
			Calendar changeDiaperCal1 = Calendar.getInstance();
			changeDiaperCal1.set(2014, 4, 19, 16, 11);
			changeDiaper1.setCreatedDate(changeDiaperCal1.getTime());
			changeDiaper1.setDirty(1);
			changeDiaper1.setDry(0);
			changeDiaper1.setMixed(0);
			changeDiaper1.setWet(0);
			changeDiaper1.setDiaperType("Баастай");

			Timeline timelineChangeDiaper1 = new Timeline();
			timelineChangeDiaper1.setChangeDiaper(changeDiaper1);
			timelineChangeDiaper1.setCreatedDate(changeDiaperCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineChangeDiaper1);

			// Health
			Hospital hospital1 = new Hospital();
			Calendar hospitalCal1 = Calendar.getInstance();
			hospitalCal1.set(2014, 8, 21, 12, 11);
			hospital1.setCreatedDate(changeDiaperCal1.getTime());
			hospital1.setDiagnosis("Хоолой улайсан");
			hospital1.setPainName("Халуурсан");
			hospital1.setHealing("Вит С");
			hospital1.setDoctorName("Азаа");
			hospital1.setHospitalName("Өрхийн эмнэлэг");

			Timeline timelineHospital1 = new Timeline();
			timelineHospital1.setHospital(hospital1);
			timelineHospital1.setCreatedDate(hospitalCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineHospital1);

			Purchase purchase1 = new Purchase();
			Calendar purchaseCal1 = Calendar.getInstance();
			purchaseCal1.set(2014, 8, 24, 16, 12);
			purchase1.setCreatedDate(purchaseCal1.getTime());
			purchase1.setPurchaseName("Живх");
			purchase1.setPurchaseAmount(54);
			purchase1.setPurchasePrice(42000);

			Timeline timelinePurchase1 = new Timeline();
			timelinePurchase1.setPurchase(purchase1);
			timelinePurchase1.setCreatedDate(purchaseCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelinePurchase1);

			ActiveOperation ao1 = new ActiveOperation();
			Calendar aoCal1 = Calendar.getInstance();
			aoCal1.set(2014, 8, 26, 20, 10);
			ao1.setCreatedDate(aoCal1.getTime());
			ao1.setType(Constants.ACTIVE_OPERATION_BATH);
			ao1.setActiveName("Усанд орлоо");

			Timeline timelineAo1 = new Timeline();
			timelineAo1.setActiveOperation(ao1);
			timelineAo1.setCreatedDate(aoCal1.getTime());
			timelineDAO = getHelper().getTimelineDao();
			timelineDAO.create(timelineAo1);

			HelpCenter helpCenter1 = new HelpCenter();
			helpCenter1
					.setHelpCenterTitle("Хэзээ яаралтай эмнэлэгт хандах вэ?");
			StringBuilder content = new StringBuilder();
			content.append("- Хөхөхдөө идэвхи муудах");
			content.append("\n");
			content.append("- Өвчин хүндрэх");
			content.append("\n");
			content.append("- Татах");
			content.append("\n");
			content.append("- Хөдөлгөөн муудах");
			content.append("\n");
			content.append("- Халуурах");
			content.append("\n");
			content.append("- Бие нь хөрөх");
			content.append("\n");
			content.append("- Амьсгал олшрох");
			content.append("\n");
			content.append("- Амьсгал саадтай болох");
			content.append("\n");
			content.append("- Баас цустай болох");
			content.append("\n");
			content.append("- Гарын алга ба хөлийн ул шарлах");
			content.append("\n");
			content.append("- Жин нэмэгдэхгүй байх");
			content.append("\n");
			helpCenter1.setHelpCenterContent(content.toString());
			helpCenter1.setCreatedDate(Calendar.getInstance().getTime());
			helpCenterDAO = getHelper().getHelpCenterDao();
			helpCenterDAO.create(helpCenter1);

			HelpCenter helpCenter2 = new HelpCenter();
			helpCenter2.setHelpCenterTitle("Халууны шил");
			StringBuilder content2 = new StringBuilder();
			content2.append("Та гэртээ халууны шил байнга байлгаарай. Хэрвээ хүүхэд тань халуурвал халууныг нь заавал хэмжиж үзээрэй. 38.5C хэмээс дээш халуурвал халуун буулгах эмийг зөвхөн эмчийн заавраар наад зах нь 6 цагийн  зайтай хэрэглэнэ.");
			helpCenter2.setHelpCenterContent(content2.toString());
			helpCenter2.setCreatedDate(Calendar.getInstance().getTime());
			helpCenterDAO = getHelper().getHelpCenterDao();
			helpCenterDAO.create(helpCenter2);

			HelpCenter helpCenter3 = new HelpCenter();
			helpCenter3.setHelpCenterTitle("Хүүхдэд тохиромжтой хоол хүнс");
			StringBuilder content3 = new StringBuilder();
			content3.append("Гол нэрийн хүнсний бүтээгдэхүүн нь хүүхдэд илчлэг өгдөг. Гол нэрийн хүнсэнд мах, төмс, лууван, байцаа, сүү, цагаан идээ - ааруул, аарц, бяслаг, ээдэм, алим болон бусад жимс жимсгэнэ орно.");
			helpCenter3.setHelpCenterContent(content3.toString());
			helpCenter3.setCreatedDate(Calendar.getInstance().getTime());
			helpCenterDAO = getHelper().getHelpCenterDao();
			helpCenterDAO.create(helpCenter3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
