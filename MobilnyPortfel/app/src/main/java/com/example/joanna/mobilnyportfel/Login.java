package com.example.joanna.mobilnyportfel;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.database.Cursor;

public class Login extends AppCompatActivity
{
    DatabaseHelper db;

    public void registerClicked(View target)
    {
        EditText login = (EditText) findViewById(R.id.login);
        EditText passowrd = (EditText) findViewById(R.id.password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        db.insertData("lewa", "123");

        Cursor res = db.getAllData("users");
        System.out.println(res.getColumnNames());

    }


};
