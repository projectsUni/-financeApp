package com.example.joanna.mobilnyportfel;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

public class displayIncome extends AppCompatActivity {

    DatabaseHelper db;
    public void display()
    {
        Cursor cursor = db.getAllData("incomeTable");
        Vector<String> columns = new Vector<String>();
        columns.add("DATE"); columns.add("CATEGORY"); columns.add("AMOUNT"); columns.add("NAME");
        StringBuilder disp = new StringBuilder();
        double sum = 0;
        disp.append("Uzyskane: ");
        while (cursor.moveToNext())
        {
            StringBuilder row = new StringBuilder();


            String prize = cursor.getString(cursor.getColumnIndex("AMOUNT"));
            String z[] = prize.split(",");
            sum += Integer.parseInt(z[0]);
            sum += Double.parseDouble(z[1]) / 100;

        }

        disp.append(Double.toString(sum));
        disp.append("\n-----\n");
        cursor.moveToFirst();
        do
        {
            StringBuilder row = new StringBuilder();

            for(String column : columns)
            {
                if(column.matches("AMOUNT"))
                {
                    String prize = cursor.getString(cursor.getColumnIndex(column));
                    String z[] = prize.split(",");
                    sum += Integer.parseInt(z[0]);
                    sum += Integer.parseInt(z[1])/100;

                }
                row.append(cursor.getString(cursor.getColumnIndex(column)));
                row.append("\n");
            }

            disp.append(row);
            disp.append("-----------------------------\n");
        }while (cursor.moveToNext());
        TextView expenses = (TextView) findViewById(R.id.incomeList);
        expenses.setText(disp);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_income);
        db = new DatabaseHelper(this);
        display();

    }
}
