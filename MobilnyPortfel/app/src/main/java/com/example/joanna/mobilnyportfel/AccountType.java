package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccountType extends AppCompatActivity {

    DatabaseHelper db;

    public String determinate(String type)
    {
   /*     Cursor cursor = db.getAllData("users123");  //crushes app
        String msg ="";
        if(cursor.getCount() == 0)
        {
            msg = "false" + ":" + type;
        }
        else
        {
            while (cursor.moveToNext())
            {
                String data = cursor.getString(cursor.getColumnIndex("TYPE"));
                if (data.equals(type))
                {
                    msg = "true" + ":" + data;
                    return msg;
                }
            }
            msg = "false" + ":" + type;
 //           Toast.makeText(getApplicationContext(), "Witaj nowy użykowniku. Ustal hasło.", Toast.LENGTH_LONG).show();
        }
        cursor.close();
        return msg;
    */
    return "housewife";
    }

    public void buttonClicked(View v) {
        String msg = "";
        switch (v.getId()) {
            case R.id.housewife:
                msg = determinate("housewife");
                break;
            case R.id.student:
                msg = determinate("student");
                break;
            case R.id.business:
                msg = determinate("business");
                break;
            case R.id.custom:
                msg = determinate("custom");
                break;

        }

        Intent intent = new Intent(this, Password.class);
        intent.putExtra("EXTRA_MESSAGE", msg);
        startActivity(intent);

        //if profile exists
        //start PasswordActivity with info about type
        //else
        //start PasswordActivity with info about type and expect from user to input new pin - save in database

        //info exapmle "true:student" - exists and type 1is student
        // "false:student" - doesn't exist, create profile for type student

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        db = new DatabaseHelper(this);
        Button housewife = (Button) findViewById(R.id.housewife);
        housewife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(view); }}
        );

        Button student = (Button) findViewById(R.id.student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(view); }}
        );

        Button business = (Button) findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(view); }}
        );

        Button custom = (Button) findViewById(R.id.custom);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(view); }}
        );

    }
}