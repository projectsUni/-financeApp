package com.example.joanna.mobilnyportfel;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Vector;
import android.text.InputFilter;

public class ShoppingList extends AppCompatActivity {

    DatabaseHelper db;


    public void buttonClicked(){
        EditText productName = (EditText)findViewById(R.id.productName);
        EditText priceZl = (EditText)findViewById(R.id.zl);
        EditText priceGrosz = (EditText)findViewById(R.id.grosz);
        priceGrosz.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        Vector <String> data = new Vector<>();
        String msg;

        if(productName.getText().toString().matches("")){
            msg = "Wprowadź nazwę produktu";
        }else if(priceZl.getText().toString().matches("")){
            productName.setText(productName.getText().toString());
            msg = "Wprowadź cenę produktu";
        }else
        {
            data.add(productName.getText().toString());
            data.add(priceZl.getText().toString());
            data.add(priceGrosz.getText().toString());

            db.insertData("shoppingList", data);
            msg = "Product added: " + data.get(0) + " " + Integer.parseInt(data.get(1)) + "." + Integer.parseInt(data.get(2)) + "zl";
        }


        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        db = new DatabaseHelper(this);

        Button confirm = (Button) findViewById(R.id.productConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
              }
        });
    }



}
