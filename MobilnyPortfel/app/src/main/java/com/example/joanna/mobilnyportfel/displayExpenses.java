package com.example.joanna.mobilnyportfel;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class displayExpenses extends AppCompatActivity {

    private DatabaseHelper db;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_expenses);

        db = new DatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        final ArrayList<productRow> products = db.getExpensesArr();
        final ie_RowAdapter adapter = new ie_RowAdapter(this, R.layout.ie_records, products);

        listView.setAdapter(adapter);
    }
}
