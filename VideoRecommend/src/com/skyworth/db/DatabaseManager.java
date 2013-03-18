package com.skyworth.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class DatabaseManager {
    private static final String TAG = "DatabaseManager";
    /**
     * database name
     */
    private static final String DB_NAME = "records.db";
    /**
     * database version
     */
    private static final int VERSION = 1;

    private Context mContext = null;
    private DatabaseHelper mHelper = null;
    private SQLiteDatabase mSQLite = null;

    public DatabaseManager(Context context) {
        mContext = context;
    }

    /**
     * create database if it does not exist, and obtain writable sqlite object
     */
    public void open() {
        try {
            mHelper = new DatabaseHelper(mContext, DB_NAME, null, VERSION);
            if (mHelper == null) {
                return;
            }
            mSQLite = mHelper.getWritableDatabase();
        } catch (SQLiteException se) {
            Log.e(TAG, "get writable database error!", se);
        }
    }

    /**
     * close the database when it is not used
     */
    public void close() {
        mSQLite.close();
        mHelper.close();
    }

    /**
     * query all records that searched
     * 
     * @return Cursor that contains records
     */
    public Cursor selectAll() {
        Cursor cursor = null;
        try {
            String sql = "select * from " + Tables.SearchTable.TABLE_NAME;
            cursor = mSQLite.rawQuery(sql, null);
        } catch (SQLiteException se) {
            Log.e(TAG, "sqlite exception", se);
            cursor = null;
        }
        return cursor;
    }

    /**
     * Insert one record into table
     * 
     * @param content search content
     * @return id number
     */
    public long insert(String content) {
        Log.i("null", "null");
        long mlong = -1;
        try {
            ContentValues cv = new ContentValues();
            cv.put(Tables.SearchTable.CONTENT, content);
            mlong = mSQLite.insert(Tables.SearchTable.TABLE_NAME, null, cv);
        } catch (Exception e) {
            Log.e(TAG, "insert exception", e);
        }
        return mlong;
    }

    /**
     * delete all search records
     * 
     * @return numbers of deleted
     */
    public int delete() {
        int del = 0;
        del = mSQLite.delete("record", null, null);
        return del;
    }

}
