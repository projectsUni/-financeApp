package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class Password extends AppCompatActivity {

    DatabaseHelper db;
    public String getUserPassword(String type)
    {
        Cursor cursor = db.getAllData("users123");
        while (cursor.moveToNext())
        {
            String data = cursor.getString(cursor.getColumnIndex("TYPE"));
            if (data.equals(type))
               break;
        }
        return cursor.getString(cursor.getColumnIndex("PASSWORD"));
    }

    public void buttonClicked(String[] substring)
    {
        EditText pin = (EditText) findViewById(R.id.pin);
        String enteredPIN = pin.getText().toString();
        Vector<String> data = new Vector<String>();
        data.add("housewife");///substring[1]);
        data.add(enteredPIN);
        if(substring[0].equals("true"))
        {
            //get password from database, authorize and start activity from second substring (switch case)
            String pass = "lll";//getUserPassword(substring[1]);
            if(enteredPIN.equals(pass))
                Toast.makeText(getApplicationContext(), "Logowanie...", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(getApplicationContext(), "Błędny login lub hasło", Toast.LENGTH_LONG).show();

        }
        else
        {
            boolean ins = db.insertData("users123",data);
            //enter profile with type from second substring and given PIN to database and start this activity
        }
        //start next activity

        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        final String[] substring = message.split(":");
        Button confirm = (Button) findViewById(R.id.confirmPIN);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ buttonClicked(substring);}}
        );
    }
}
