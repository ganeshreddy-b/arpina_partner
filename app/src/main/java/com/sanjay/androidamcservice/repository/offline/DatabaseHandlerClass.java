package com.sanjay.androidamcservice.repository.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sanjay.androidamcservice.utils.Logger;

public class DatabaseHandlerClass extends SQLiteOpenHelper {

    public static final String DB_NAME = "OelpDatabase.db";
    public static final int VERSION = 1;
    public static final String DATABASESECRETKEY = "oelp@12345";
    public static SQLiteDatabase database;
    private static final String TAG = DatabaseHandlerClass.class.getSimpleName();


    public DatabaseHandlerClass(Context mContext) {
        super(mContext, DB_NAME, null, VERSION);
        try {
//            SQLiteDatabase.loadLibs(mContext);
            initDatabase();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onCreate method", e);
        }
    }

    public void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createMessageTable(sqLiteDatabase);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void initDatabase() {
        if (database == null || !database.isOpen() || database.isReadOnly())
            database = this.getWritableDatabase();

    }

    public void closeDatabase(){
        /*if (database!=null)
            database.close();*/
    }



    private void createMessageTable(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS tbl_messages (" +
                "uuid TEXT," +
                "message TEXT," +
                "from_user TEXT," +
                "to_user TEXT," +
                "message_date TEXT," +
                "status integer DEFAULT 2 )";
        Logger.logD(TAG, "Database creation query :" + query);
        sqLiteDatabase.execSQL(query);
    }


    public SQLiteDatabase getWriteDb() {
        if (database != null && database.isOpen())
            return database;
        else
            return getWritableDatabase();
    }
}
