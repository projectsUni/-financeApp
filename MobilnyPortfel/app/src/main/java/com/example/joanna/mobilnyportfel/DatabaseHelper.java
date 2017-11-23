package com.example.joanna.mobilnyportfel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Vector;


public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "database123.db";
    public static final String u_TABLE_NAME = "users123";
    public static final String u_COL_1 = "ID";
    public static final String u_COL_2 = "TYPE";
    public static final String u_COL_3 = "PASSWORD";

    public static final String l_TABLE_NAME = "shoppingList";
    public static final String l_COL_1 = "PRODUCT";
    public static final String l_COL_2 = "ZL";
    public static final String l_COL_3 = "GR";



    //HERE can be added new tables like above

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists " + u_TABLE_NAME +" ("+ u_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + u_COL_2 + " VARCHAR(20), " + u_COL_3 + " VARCHAR(50))");
        db.execSQL("create table if not exists " + l_TABLE_NAME +" ("+ l_COL_1 + " VARCHAR(50), " + l_COL_2 + " INTEGER " + l_COL_3 + " TINYINT(255))");
        //dont forget to create your table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String table, Vector<String> data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        if(table.charAt(0) == 'u') {
            contentValues.put(u_COL_2, data.elementAt(0));
            contentValues.put(u_COL_3, data.elementAt(1));
        }else if (table.matches("shoppingList")){
            contentValues.put(l_COL_1, data.get(0));
            contentValues.put(l_COL_2, Integer.parseInt(data.get(1)));
            contentValues.put(l_COL_3, Integer.parseInt(data.get(2)));

        }
        // else if == 'other letter'
            // ->> otherletter_COL_2 etc. - other letter is the first letter of database and you have to add so many puts as columns
        long result = db.insert(table,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table,null);
        return res;
    }


}