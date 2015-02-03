package vn.cybersoft.simplegame.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import vn.cybersoft.simplegame.Preferences;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 * 
 */
public class DBService {
	private static final String TAG = "DBService";
	@SuppressLint("SdCardPath")
	public static String DB_PATH = Preferences.APPLICATION_PATH 
	+File.separator+"databases"+File.separator;
	private static String DB_NAME = "game_object.db";
	private static String SCRIPT_NAME = "game_object.sql";

	private DatabaseHelper mDatabaseHelper;
	private Context context;

	private static DBService helper;

	/**
	 * @return Game object manager (singleton pattern)
	 */
	public static DBService getInstance() {
		if (helper==null) {
			helper = 
					new DBService(Preferences.getInstance().getApplicationContext());
		}
		return helper;
	}
	
	/**
	 * Save content values data to table has tableName
	 * @param tableName
	 * @param values
	 */
	private void saveData(String tableName, ContentValues values) {
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

		db.beginTransaction();
		try {
			db.insert(tableName, null, values);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e(TAG, "Database error");
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
	
	public Cursor getAllSecondaryCharacter() {
		return getAll(SecCharacterColumns.TABLENAME);
	}
	
	public Cursor getAllPrimaryCharacter() {
		return getAll(PriCharacterColumns.TABLENAME);
	}
	
	public Cursor getAllTools() {
		return getAll(ToolColumns.TABLENAME);
	}
	
	private Cursor getAll(String tablename) {
		Cursor c = getReadableDatabase().query(
				tablename,
				null, null, null, null, null, null);
		return c;
	}
	
	public SQLiteDatabase getWritableDatabase() {
		return mDatabaseHelper.getWritableDatabase();
	}
	
	public SQLiteDatabase getReadableDatabase() {
		return mDatabaseHelper.getReadableDatabase();
	}

	/**
	 * private Constructor (singleton pattern)
	 * 
	 * @param context
	 *            Context
	 */
	private DBService(Context context) {
		Log.i(TAG, "Create DB service");
		this.context = context;
		mDatabaseHelper = new DatabaseHelper(this.context);
		try {
			mDatabaseHelper.createDataBase();
			mDatabaseHelper.openDataBase();
		} catch (Exception e) {
			//Log.e(TAG, e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * DatabaseHelper Class
	 * 
	 */
	public class DatabaseHelper extends SQLiteOpenHelper {

		// initialize at November 26, 2014 (version 1)
		private static final int DB_VERSION = 1;

		private SQLiteDatabase mDatabase;

		private final Context mHelperContext;

		/**
		 * Constructor Takes and keeps a reference of the passed context in
		 * order to access to the application assets and resources.
		 * 
		 * @param context
		 */
		public DatabaseHelper(Context context) {
			super(context, DB_PATH+DB_NAME, null, DB_VERSION);
			this.mHelperContext = context;
		}

		/**
		 * Creates a empty database on the system and rewrites it with your own
		 * database.
		 * */
		public void createDataBase() throws IOException {
			Log.i(TAG, "createDataBase");
			boolean dbExist = checkDataBase();
			Log.i(TAG, "dbExist : " + dbExist);


			if (dbExist) {
				// do nothing - database already exist
				this.getWritableDatabase();

			} else {
				File file = new File(DBService.DB_PATH);
				if(!file.exists()) {
					file.mkdirs();
				}

				// By calling this method and empty database will be created
				// into the default system path
				// of your application so we are gonna be able to overwrite that
				// database with our database.
				this.getWritableDatabase();

				// copyDataBase();
			}

		}

		/**
		 * Check if the database already exist to avoid re-copying the file each
		 * time you open the application.
		 * 
		 * @return true if it exists, false if it doesn't
		 */
		private boolean checkDataBase() {

			SQLiteDatabase checkDB = null;

			try {
				String myPath = DB_PATH + DB_NAME;
				checkDB = SQLiteDatabase.openDatabase(myPath, null,
						SQLiteDatabase.OPEN_READONLY);

			} catch (SQLiteException e) {
				Log.i(TAG, "Database doesn't exist yet.");

			}

			if (checkDB != null) {

				checkDB.close();

			}

			return checkDB != null;
		}

		/**
		 * Execute SQL command in sql file
		 * 
		 * @param db
		 * @throws IOException
		 */
		public void executeSQL(SQLiteDatabase db) throws IOException {
			Log.i(TAG, "executeSQL");
			InputStream myInput = mHelperContext.getAssets().open(SCRIPT_NAME);
			StringBuilder sb = new StringBuilder();
			byte[] buffer = new byte[1024];
			int length = 0;
			String strFileContents;
			while ((length = myInput.read(buffer)) != -1) {
				strFileContents = new String(buffer, 0, length);
				sb.append(strFileContents);

			}

			String[] sqls = sb.toString().split("END");
			for (String sql : sqls) {
				db.execSQL(sql + ";");
				Log.i(TAG, sql + ";");
			}
		}

		public void openDataBase() throws SQLException {
			Log.i(TAG, "openDataBase");
			close();

			// Open the database
			String myPath = DB_PATH + DB_NAME;
			mDatabase = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);

		}



		@Override
		public synchronized void close() {

			if (mDatabase != null)
				mDatabase.close();

			super.close();

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				// copyDataBase();
				executeSQL(db);
			} catch (Exception e) {
				Log.e(TAG,
						"executeSQL error: " + e.getLocalizedMessage());
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			System.out.println("Upgrading db from " + oldVersion + " to new version: " + newVersion);

			db.execSQL("DROP TABLE IF EXISTS " + ToolColumns.TABLENAME);

			try {
				executeSQL(db);
			} catch (Exception e) {
				Log.e(TAG,
						"executeSQL error: " + e.getLocalizedMessage());
			}
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			System.out.println("Downgrading db from " + oldVersion + " to old version: " + newVersion);

			db.execSQL("DROP TABLE IF EXISTS " + ToolColumns.TABLENAME);

			try {
				executeSQL(db);
			} catch (Exception e) {
				Log.e(TAG,
						"executeSQL error: " + e.getLocalizedMessage());
			}
		}
	}

	public static class GameObjectColumns implements BaseColumns {
		private GameObjectColumns() {}
		
		public static final String NAME = "name";
		public static final String DESCRIPTION = "desc";
		public static final String STATUS = "status";
		public static final String IMAGE_PATH = "image_path";
		public static final String UPDATE_TIME = "updateTime";
	}

	public static class ToolColumns implements BaseColumns {
		private ToolColumns() {}
		
		public static final String TABLENAME = "tool";
		public static final String PRICE = "price";
		
	}

	public static class PriCharacterColumns implements BaseColumns {
		private PriCharacterColumns() {}
		
		public static final String TABLENAME = "primary_character";
		public static final String SCORE = "score";
		
	}

	public static class SecCharacterColumns implements BaseColumns {
		private SecCharacterColumns() {}
		
		public static final String TABLENAME = "secondary_character";
		public static final String SCORE = "cumulative_score";
		
	}

}
