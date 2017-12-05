package com.example.joanna.mobilnyportfel;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

public class displayExpenses extends AppCompatActivity {

    DatabaseHelper db;
    public void display()
    {
        Cursor cursor = db.getAllData("expensesTable");
        Vector<String> columns = new Vector<String>();
        columns.add("DATE"); columns.add("CATEGORY"); columns.add("AMOUNT"); columns.add("NAME");
        StringBuilder disp = new StringBuilder();
        double sum = 0;
        disp.append("Wydane: ");
        while (cursor.moveToNext())
        {
            StringBuilder row = new StringBuilder();


                    String prize = cursor.getString(cursor.getColumnIndex("AMOUNT"));
                    String z[] = prize.split(",");
                    sum += Integer.parseInt(z[0]);
                    sum += Integer.parseInt(z[1]) / 100;

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
                    sum +=  Double.parseDouble(z[1])/100;

                }
                row.append(cursor.getString(cursor.getColumnIndex(column)));
                row.append("\n");
            }

            disp.append(row);
            disp.append("-----------------------------\n");
        }while (cursor.moveToNext());
        TextView expenses = (TextView) findViewById(R.id.expensesList);
        expenses.setText(disp);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expenses);
        db=new DatabaseHelper(this);
        display();
    }
}
