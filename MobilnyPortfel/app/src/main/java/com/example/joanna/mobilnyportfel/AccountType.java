package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccountType extends AppCompatActivity  {

    public void buttonClicked()
    {
        //if profile exists

            //start PasswordActivity with info about type
        //else
            //start PasswordActivity with info about type and expect from user to input new pin - save in database

        //info exapmle "true:student" - exists and type 1is student
        // "false:student" - doesn't exist, create profile for type student
        Toast.makeText(getApplicationContext(), "Clicked button, PasswordActivity should start", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        Button housewife = (Button) findViewById(R.id.housewife);
        housewife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(); }}
        );

        Button student = (Button) findViewById(R.id.student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(); }}
        );

        Button business = (Button) findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(); }}
        );

        Button custom = (Button) findViewById(R.id.custom);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { buttonClicked(); }}
        );
    }
}
