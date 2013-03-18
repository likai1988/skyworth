package com.skyworth.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DatabaseManager {
    private Context mContext = null;
    private DatabaseHelper mHelper = null;
    private SQLiteDatabase mSQLite = null;
    private String dbname = "recordb.db";
    private int version = 1;
    
    public DatabaseManager(Context context){
        mContext = context;
    }
    
    public void open(){
        Log.i("open","open");
        try{
            mHelper = new DatabaseHelper(mContext, dbname, null, version);
            if(mHelper == null){
                return;
            }
            mSQLite = mHelper.getWritableDatabase();
        }catch(SQLiteException se){
            se.printStackTrace();
        }
    }
    
    public void close(){
        Log.i("close","clsose");
        mSQLite.close();
        mHelper.close();
    }
    
    public Cursor selectAll(){
        Cursor cursor = null;
        try{
            String sql = "select * from record";
            cursor = mSQLite.rawQuery(sql, null);
        }catch(SQLiteException se){
            se.printStackTrace();
            cursor = null;
        }
        return cursor;
    }
    
    public long insert(String content){
        Log.i("null","null");
        long mlong = -1;
        try{
            ContentValues cv = new ContentValues();
            cv.put("content", content);
            mlong = mSQLite.insert("record", null, cv);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mlong;
    }
    
    public int delete(){
        int del = 0;
        del = mSQLite.delete("record", null, null);
        return del;
    }
}
