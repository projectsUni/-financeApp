package com.example.joanna.mobilnyportfel;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Vector;
import android.text.InputFilter;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Adapter;
import java.util.ArrayList;


public class ShoppingList extends AppCompatActivity {

    private DatabaseHelper db;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { db.l_TABLE_NAME, db.l_COL_1, db.l_COL_2, db.l_COL_3 };
    final int[] to = new int[] { R.id.productName, R.id.zl, R.id.grosz };



    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        db = new DatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        final ArrayList<productRow> products = db.fetch();
        final sl_RowAdapter adapter = new sl_RowAdapter(this, R.layout.view_record, products);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Here I want the category_id


                db.deleteRow(DatabaseHelper.l_TABLE_NAME, products.get(position).getID());
                products.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_shopping_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.add_record){
            Intent add_mem = new Intent(this, AddProductActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }

}
