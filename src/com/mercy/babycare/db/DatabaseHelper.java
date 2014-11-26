package com.mercy.babycare.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mercy.babycare.entities.ActiveOperation;
import com.mercy.babycare.entities.Baby;
import com.mercy.babycare.entities.Breast;
import com.mercy.babycare.entities.ChangeDiaper;
import com.mercy.babycare.entities.Drink;
import com.mercy.babycare.entities.Feed;
import com.mercy.babycare.entities.Formula;
import com.mercy.babycare.entities.Health;
import com.mercy.babycare.entities.HelpCenter;
import com.mercy.babycare.entities.Learn;
import com.mercy.babycare.entities.MedCheck;
import com.mercy.babycare.entities.MilkingBreast;
import com.mercy.babycare.entities.Pain;
import com.mercy.babycare.entities.Purchase;
import com.mercy.babycare.entities.TakeMedicine;
import com.mercy.babycare.entities.Timeline;
import com.mercy.babycare.entities.Tooth;
import com.mercy.babycare.entities.Vaccine;
import com.mercy.babycare.entities.Vitamin;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "babycare.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;

	// DAO objects
	private Dao<Timeline, Integer> timelineDAO = null;
	private Dao<Baby, Integer> babyDAO = null;
	private Dao<ActiveOperation, Integer> activeOperationDAO = null;
	private Dao<Breast, Integer> breastDAO = null;
	private Dao<ChangeDiaper, Integer> changeDiaperDAO = null;
	private Dao<Drink, Integer> drinkDAO = null;
	private Dao<Feed, Integer> feedDAO = null;
	private Dao<Formula, Integer> formulaDAO = null;
	private Dao<Health, Integer> healthDAO = null;
	private Dao<HelpCenter, Integer> helpCenterDAO = null;
	private Dao<Learn, Integer> learnDAO = null;
	private Dao<MedCheck, Integer> medcheckDAO = null;
	private Dao<MilkingBreast, Integer> milkingBreastDAO = null;
	private Dao<Pain, Integer> painDAO = null;
	private Dao<Purchase, Integer> purchaseDAO = null;
	private Dao<TakeMedicine, Integer> takeMedicineDAO = null;
	private Dao<Tooth, Integer> toothDAO = null;
	private Dao<Vaccine, Integer> vaccineDAO = null;
	private Dao<Vitamin, Integer> vitaminDAO = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Timeline.class);
			TableUtils.createTable(connectionSource, Baby.class);
			TableUtils.createTable(connectionSource, ActiveOperation.class);
			TableUtils.createTable(connectionSource, Breast.class);
			TableUtils.createTable(connectionSource, ChangeDiaper.class);
			TableUtils.createTable(connectionSource, Drink.class);
			TableUtils.createTable(connectionSource, Feed.class);
			TableUtils.createTable(connectionSource, Formula.class);
			TableUtils.createTable(connectionSource, Health.class);
			TableUtils.createTable(connectionSource, HelpCenter.class);
			TableUtils.createTable(connectionSource, Learn.class);
			TableUtils.createTable(connectionSource, MedCheck.class);
			TableUtils.createTable(connectionSource, MilkingBreast.class);
			TableUtils.createTable(connectionSource, Pain.class);
			TableUtils.createTable(connectionSource, Purchase.class);
			TableUtils.createTable(connectionSource, TakeMedicine.class);
			TableUtils.createTable(connectionSource, Tooth.class);
			TableUtils.createTable(connectionSource, Vaccine.class);
			TableUtils.createTable(connectionSource, Vitamin.class);

		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Timeline.class, true);
			TableUtils.dropTable(connectionSource, Baby.class, true);
			TableUtils.dropTable(connectionSource, ActiveOperation.class, true);
			TableUtils.dropTable(connectionSource, Breast.class, true);
			TableUtils.dropTable(connectionSource, ChangeDiaper.class, true);
			TableUtils.dropTable(connectionSource, Drink.class, true);
			TableUtils.dropTable(connectionSource, Feed.class, true);
			TableUtils.dropTable(connectionSource, Formula.class, true);
			TableUtils.dropTable(connectionSource, Health.class, true);
			TableUtils.dropTable(connectionSource, HelpCenter.class, true);
			TableUtils.dropTable(connectionSource, Learn.class, true);
			TableUtils.dropTable(connectionSource, MedCheck.class, true);
			TableUtils.dropTable(connectionSource, MilkingBreast.class, true);
			TableUtils.dropTable(connectionSource, Pain.class, true);
			TableUtils.dropTable(connectionSource, Purchase.class, true);
			TableUtils.dropTable(connectionSource, TakeMedicine.class, true);
			TableUtils.dropTable(connectionSource, Tooth.class, true);
			TableUtils.dropTable(connectionSource, Vaccine.class, true);
			TableUtils.dropTable(connectionSource, Vitamin.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our SimpleData class. It
	 * will create it or just give the cached value.
	 */
	public Dao<Timeline, Integer> getTimelineDao() throws SQLException {
		if (timelineDAO == null) {
			timelineDAO = getDao(Timeline.class);
		}
		return timelineDAO;
	}

	public Dao<Baby, Integer> getBabyDao() throws SQLException {
		if (babyDAO == null) {
			babyDAO = getDao(Baby.class);
		}
		return babyDAO;
	}

	public Dao<ActiveOperation, Integer> getActiveOperationDao()
			throws SQLException {
		if (activeOperationDAO == null) {
			activeOperationDAO = getDao(ActiveOperation.class);
		}
		return activeOperationDAO;
	}

	public Dao<Breast, Integer> getBreastDao() throws SQLException {
		if (breastDAO == null) {
			breastDAO = getDao(Breast.class);
		}
		return breastDAO;
	}

	public Dao<ChangeDiaper, Integer> getChangeDiaperDao() throws SQLException {
		if (changeDiaperDAO == null) {
			changeDiaperDAO = getDao(ChangeDiaper.class);
		}
		return changeDiaperDAO;
	}

	public Dao<Drink, Integer> getDrinkDao() throws SQLException {
		if (drinkDAO == null) {
			drinkDAO = getDao(Drink.class);
		}
		return drinkDAO;
	}

	public Dao<Feed, Integer> getFeedDao() throws SQLException {
		if (feedDAO == null) {
			feedDAO = getDao(Feed.class);
		}
		return feedDAO;
	}

	public Dao<Formula, Integer> getFormulaDao() throws SQLException {
		if (formulaDAO == null) {
			formulaDAO = getDao(Formula.class);
		}
		return formulaDAO;
	}

	public Dao<Health, Integer> getHealthDao() throws SQLException {
		if (healthDAO == null) {
			healthDAO = getDao(Health.class);
		}
		return healthDAO;
	}

	public Dao<HelpCenter, Integer> getHelpCenterDao() throws SQLException {
		if (helpCenterDAO == null) {
			helpCenterDAO = getDao(HelpCenter.class);
		}
		return helpCenterDAO;
	}

	public Dao<Learn, Integer> getLearnDao() throws SQLException {
		if (learnDAO == null) {
			learnDAO = getDao(Learn.class);
		}
		return learnDAO;
	}

	public Dao<MedCheck, Integer> getMedCheckDao() throws SQLException {
		if (medcheckDAO == null) {
			medcheckDAO = getDao(MedCheck.class);
		}
		return medcheckDAO;
	}

	public Dao<MilkingBreast, Integer> getMilkingBreastDao()
			throws SQLException {
		if (milkingBreastDAO == null) {
			milkingBreastDAO = getDao(MilkingBreast.class);
		}
		return milkingBreastDAO;
	}

	public Dao<Pain, Integer> getPainDao() throws SQLException {
		if (painDAO == null) {
			painDAO = getDao(Pain.class);
		}
		return painDAO;
	}

	public Dao<Purchase, Integer> getPurchaseDao() throws SQLException {
		if (purchaseDAO == null) {
			purchaseDAO = getDao(Purchase.class);
		}
		return purchaseDAO;
	}

	public Dao<TakeMedicine, Integer> getTakeMedicineDao() throws SQLException {
		if (takeMedicineDAO == null) {
			takeMedicineDAO = getDao(TakeMedicine.class);
		}
		return takeMedicineDAO;
	}

	public Dao<Tooth, Integer> getToothDao() throws SQLException {
		if (toothDAO == null) {
			toothDAO = getDao(Tooth.class);
		}
		return toothDAO;
	}

	public Dao<Vaccine, Integer> getVaccineDao() throws SQLException {
		if (vaccineDAO == null) {
			vaccineDAO = getDao(Vaccine.class);
		}
		return vaccineDAO;
	}

	public Dao<Vitamin, Integer> getVitaminDao() throws SQLException {
		if (vitaminDAO == null) {
			vitaminDAO = getDao(Vitamin.class);
		}
		return vitaminDAO;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		timelineDAO = null;
		feedDAO = null;
		babyDAO = null;
		activeOperationDAO = null;
		breastDAO = null;
		changeDiaperDAO = null;
		drinkDAO = null;
		feedDAO = null;
		formulaDAO = null;
		healthDAO = null;
		helpCenterDAO = null;
		learnDAO = null;
		medcheckDAO = null;
		milkingBreastDAO = null;
		painDAO = null;
		purchaseDAO = null;
		takeMedicineDAO = null;
		toothDAO = null;
		vaccineDAO = null;
		vitaminDAO = null;

	}
}
