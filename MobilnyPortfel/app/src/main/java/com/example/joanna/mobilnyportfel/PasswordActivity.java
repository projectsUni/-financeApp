package com.example.joanna.mobilnyportfel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //get message from parent activity and string split with ' : ' as delimeter
        //if first substring is true
            //get password from database, authorize and start activity from second substring (switch case)
        //else
            //enter profile with type from second substring and given PIN to database and start this activity

    }
}
