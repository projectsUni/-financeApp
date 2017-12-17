package com.example.joanna.mobilnyportfel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
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

    public static final String i_TABLE_NAME = "incomeTable";
    public static final String i_COL_1 = "ID";
    public static final String i_COL_2 = "NAME";
    public static final String i_COL_3 = "CATEGORY";
    public static final String i_COL_4 = "AMOUNT";
    public static final String i_COL_5 = "DATE";
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

        db.execSQL("create table if not exists " + i_TABLE_NAME +" ("+ i_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + i_COL_2 + " VARCHAR(20), " + i_COL_3 + "  VARCHAR(20), " + i_COL_4 + " VARCHAR(10), " + i_COL_5 + " date )"  );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public void deleteRow(String table, int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + table + " WHERE ID=" + ID;
        db.execSQL(query);

    }

    public boolean insertData(String table, Vector<String> data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        if(table.matches("users1234")) {
            contentValues.put(u_COL_2, data.elementAt(0));
            contentValues.put(u_COL_3, data.elementAt(1));
        }else if (table.matches("shoppingList")){
            contentValues.put(l_COL_1, data.get(0));
            contentValues.put(l_COL_2, data.get(1));
            contentValues.put(l_COL_3, data.get(2));

        }
        else if (table.matches("expensesTable"))
        {
            contentValues.put(e_COL_2, data.elementAt(0));
            contentValues.put(e_COL_3, data.elementAt(1));
            contentValues.put(e_COL_4, data.elementAt(2));
            contentValues.put(e_COL_5, data.elementAt(3));

        }
        else if (table.matches("incomeTable"))
        {
            contentValues.put(i_COL_2, data.elementAt(0));
            contentValues.put(i_COL_3, data.elementAt(1));
            contentValues.put(i_COL_4, data.elementAt(2));
            contentValues.put(i_COL_5, data.elementAt(3));

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

                String name1 = cursor.getColumnName(cursor.getColumnIndex(DatabaseHelper.l_COL_1));
                int priceZL = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.l_COL_2)));
                int priceGR = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.l_COL_3)));
                int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.l_COL_0)));

                productsData.add(new productRow(name, name1, priceZL, priceGR, ID)); //add the item
                cursor.moveToNext();
            }

        }


        return productsData;
    }

    public ArrayList<productRow> getExpensesArr() {
        SQLiteDatabase db = this.getWritableDatabase();
        //String[] columns = new String[] { DatabaseHelper.e_COL_1, DatabaseHelper.e_COL_2, DatabaseHelper.e_COL_3, DatabaseHelper.e_COL_4 };
        String selectQuery = "SELECT  * FROM " + e_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Cursor cursor = db.query(DatabaseHelper.l_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList <productRow> productsData = new ArrayList<productRow>();
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {

                String name1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_2));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_3));
                String fullPriceString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_4));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_5));
                double fullPrice = Double.parseDouble(fullPriceString.replaceAll(",", "."));

                int priceZL = (int)fullPrice;
                int priceGR = (int)fullPrice-priceZL;

                //priceGR -= priceZL;
                //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_1)));

                productsData.add(new productRow(name, name1, priceZL, priceGR, date)); //add the item

                cursor.moveToNext();
            }

        }


        return productsData;
    }


    public ArrayList<productRow> getIncomesArr() {
        SQLiteDatabase db = this.getWritableDatabase();
        //String[] columns = new String[] { DatabaseHelper.e_COL_1, DatabaseHelper.e_COL_2, DatabaseHelper.e_COL_3, DatabaseHelper.e_COL_4 };
        String selectQuery = "SELECT  * FROM " + i_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Cursor cursor = db.query(DatabaseHelper.l_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList <productRow> productsData = new ArrayList<productRow>();
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                String name1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_2));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_3));
                String fullPriceString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_4));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_5));
                double fullPrice = Double.parseDouble(fullPriceString.replaceAll(",", "."));

                int priceZL = (int)fullPrice;
                int priceGR = (int)fullPrice-priceZL;

                //priceGR -= priceZL;
                //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_1)));

                productsData.add(new productRow(name, name1, priceZL, priceGR, date)); //add the item

                cursor.moveToNext();
            }

        }


        return productsData;
    }
    public ArrayList<productRow> getExpensesDay( String chosenDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        //String[] columns = new String[] { DatabaseHelper.e_COL_1, DatabaseHelper.e_COL_2, DatabaseHelper.e_COL_3, DatabaseHelper.e_COL_4 };
        String selectQuery = "SELECT  * FROM " + e_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Cursor cursor = db.query(DatabaseHelper.l_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList <productRow> productsData = new ArrayList<productRow>();
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {

                String name1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_2));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_3));
                String fullPriceString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_4));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_5));
                double fullPrice = Double.parseDouble(fullPriceString.replaceAll(",", "."));

                int priceZL = (int)fullPrice;
                int priceGR = (int)fullPrice-priceZL;

                //priceGR -= priceZL;
                //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_1)));
                if(date.equals(chosenDay))
                    productsData.add(new productRow(name, name1, priceZL, priceGR, date)); //add the item

                cursor.moveToNext();
            }

        }


        return productsData;
    }


    public ArrayList<productRow> getIncomesDay(String chosenDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        //String[] columns = new String[] { DatabaseHelper.e_COL_1, DatabaseHelper.e_COL_2, DatabaseHelper.e_COL_3, DatabaseHelper.e_COL_4 };
        String selectQuery = "SELECT  * FROM " + i_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Cursor cursor = db.query(DatabaseHelper.l_TABLE_NAME, columns, null, null, null, null, null);

        ArrayList <productRow> productsData = new ArrayList<productRow>();
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                String name1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_2));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_3));
                String fullPriceString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_4));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.i_COL_5));
                double fullPrice = Double.parseDouble(fullPriceString.replaceAll(",", "."));

                int priceZL = (int)fullPrice;
                int priceGR = (int)fullPrice-priceZL;

                //priceGR -= priceZL;
                //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.e_COL_1)));
                if(date.equals(chosenDate))
                    productsData.add(new productRow(name, name1, priceZL, priceGR, date)); //add the item

                cursor.moveToNext();
            }

        }


        return productsData;
    }

    public void deleteTable(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DROP TABLE if exists" + table;

        db.execSQL(selectQuery);

    }

    public void create(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("create table if not exists " + e_TABLE_NAME +" ("+ e_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + e_COL_2 + " VARCHAR(20), " + e_COL_3 + "  VARCHAR(20), " + e_COL_4 + " VARCHAR(10), " + e_COL_5 + " date )"  );

        db.execSQL("create table if not exists " + i_TABLE_NAME +" ("+ i_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + i_COL_2 + " VARCHAR(20), " + i_COL_3 + "  VARCHAR(20), " + i_COL_4 + " VARCHAR(10), " + i_COL_5 + " date )"  );

    }
}