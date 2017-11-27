package com.example.joanna.mobilnyportfel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;


public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "database123.db";
    public static final String u_TABLE_NAME = "users123";
    public static final String u_COL_1 = "ID";
    public static final String u_COL_2 = "TYPE";
    public static final String u_COL_3 = "PASSWORD";

    public static final String l_TABLE_NAME = "shoppingList";
    public static final String l_COL_0 = "ID";
    public static final String l_COL_1 = "PRODUCT";
    public static final String l_COL_2 = "ZL";
    public static final String l_COL_3 = "GR";

    public static final String e_TABLE_NAME = "expensesTable";
    public static final String e_COL_1 = "ID";
    public static final String e_COL_2 = "NAME";
    public static final String e_COL_3 = "CATEGORY";
    public static final String e_COL_4 = "AMOUNT";
    public static final String e_COL_5 = "DATE";


    //HERE can be added new tables like above

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists " + u_TABLE_NAME +" ("+ u_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + u_COL_2 + " VARCHAR(20), " + u_COL_3 + " VARCHAR(50))");

        db.execSQL("create table if not exists " + l_TABLE_NAME +" ("+ l_COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + l_COL_1 + " VARCHAR(50), " + l_COL_2 + " VARCHAR(20), " + l_COL_3 + " VARCHAR(20))");

        db.execSQL("create table if not exists " + e_TABLE_NAME +" ("+ e_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + e_COL_2 + " VARCHAR(20), " + e_COL_3 + "  VARCHAR(20), " + e_COL_4 + " VARCHAR(10), " + e_COL_5 + " date )"  );

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
            contentValues.put(l_COL_2, data.get(1));
            contentValues.put(l_COL_3, data.get(2));

        }
        else if (table.charAt(0) == 'e')
        {
            contentValues.put(e_COL_2, data.elementAt(0));
            contentValues.put(e_COL_3, data.elementAt(1));
            contentValues.put(e_COL_4, data.elementAt(2));
            contentValues.put(e_COL_5, data.elementAt(3));

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

    public ArrayList<productRow> fetch() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { DatabaseHelper.l_COL_0, DatabaseHelper.l_COL_1, DatabaseHelper.l_COL_2, DatabaseHelper.l_COL_3 };
        String selectQuery = "SELECT  * FROM " + l_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Cursor cursor = db.query(DatabaseHelper.l_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList <productRow> productsData = new ArrayList<productRow>();
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.l_COL_1));
                int priceZL = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.l_COL_2)));
                int priceGR = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.l_COL_3)));

                productsData.add(new productRow(name, priceZL, priceGR)); //add the item
                cursor.moveToNext();
            }

        }


        return productsData;
    }

}