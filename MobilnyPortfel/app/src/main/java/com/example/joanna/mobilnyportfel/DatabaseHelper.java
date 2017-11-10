package com.example.joanna.mobilnyportfel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;


public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NAME = "users";
    public static final String u_COL_1 = "ID";
    public static final String u_COL_2 = "TYPE";
    public static final String u_COL_3 = "PASSWORD";


    //HERE can be added new tables like above

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" ("+ u_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + u_COL_2 + " VARCHAR(20), " + u_COL_3 + " VARCHAR(50))");
        //dont forget to create your table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String table, Vector<String> data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(table.charAt(0) == 'u') {
            contentValues.put(u_COL_2, data.elementAt(0));
            contentValues.put(u_COL_3, data.elementAt(1));
        }
        // else if == 'other letter'
            // ->> otherletter_COL_2 etc. - other letter is the first letter of database and you have to add so many puts as columns
        long result = db.insert(table,null ,contentValues);
        db.close();
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table,null);
        db.close();
        return res;
    }



    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


}