package com.example.joanna.mobilnyportfel;

import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Daily extends AppCompatActivity {

    DatabaseHelper db;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    public void buttonClicked()
    {
        DatePicker chosenDate = (DatePicker) findViewById(R.id.chdate);
        String caller = getIntent().getStringExtra("caller");
        int month = Integer.valueOf(chosenDate.getMonth())+1;
        String date = chosenDate.getYear() + "-" +Integer.toString(month)  + "-" + chosenDate.getDayOfMonth();
        if (caller.equals("expenses"))
        {
            final ArrayList<productRow> products = db.getExpensesDay(date);
            final ie_RowAdapter adapter = new ie_RowAdapter(this, R.layout.ie_records, products);
            listView.setAdapter(adapter);
        }
        else
        {
            final ArrayList<productRow> products = db.getIncomesDay(date) ;
            final ie_RowAdapter adapter = new ie_RowAdapter(this, R.layout.ie_records, products);
            listView.setAdapter(adapter);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);
        db = new DatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        Button submit = (Button) findViewById(R.id.subButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ buttonClicked();}}
        );

    }
}
