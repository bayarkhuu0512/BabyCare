package com.mercy.babycare.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mercy.babycare.entities.Child;
import com.mercy.babycare.entities.Feed;

public class DatabaseHelper  extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "babycare.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;

	// DAO objects
	private Dao<Child, Integer> childDao = null;
	private Dao<Feed, Integer> feedDao = null;
	

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Child.class);
			TableUtils.createTable(connectionSource, Feed.class);

			// here we try inserting data in the on-create as a test
//			Dao<Feed, Integer> dao = getFeedDao();
//			long millis = System.currentTimeMillis();
//			// create some entries in the onCreate
//			Feed simple = new Feed(millis);
//			dao.create(simple);
//			simple = new Feed(millis + 1);
//			dao.create(simple);
//			Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Child.class, true);
			TableUtils.dropTable(connectionSource, Feed.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
	 * value.
	 */
	public Dao<Child, Integer> getChildDao() throws SQLException {
		if (childDao == null) {
			childDao = getDao(Child.class);
		}
		return childDao;
	}

	public Dao<Feed, Integer> getFeedDao() throws SQLException {
		if (feedDao == null) {
			feedDao = getDao(Feed.class);
		}
		return feedDao;
	}
	
	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		feedDao = null;
		childDao = null;
	}
}
