package com.example.joanna.mobilnyportfel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class AddProductActivity extends Activity implements OnClickListener {

    private Button addButton;
    private EditText productName;
    private EditText priceZloty;
    private EditText priceGrosz;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTitle("Dodaj produkt");
        setContentView(R.layout.activity_add_product);

        productName = (EditText)findViewById(R.id.productName);
        priceZloty = (EditText)findViewById(R.id.zl);
        priceGrosz = (EditText)findViewById(R.id.grosz);
        addButton = (Button) findViewById(R.id.add_record);

        db = new DatabaseHelper(this);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_record:
                Vector<String> data = new Vector<>();
                data.add(productName.getText().toString());
                data.add(priceZloty.getText().toString());
                data.add(priceGrosz.getText().toString());

                db.insertData(DatabaseHelper.l_TABLE_NAME, data);

                Intent main = new Intent(AddProductActivity.this, ShoppingList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String msg = "Product added: " + data.get(0) + " " + Integer.parseInt(data.get(1)) + "." + Integer.parseInt(data.get(2)) + "zl";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                startActivity(main);
                break;
        }
    }
}