package com.example.joanna.mobilnyportfel;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.database.Cursor;

public class Login extends AppCompatActivity
{
    DatabaseHelper db;

    public boolean checkIfExists(String input)
    {
        Cursor res = db.getAllData();
        String log;
        while (res.moveToNext())
        {
            log = res.getString(1);
            if (log.equals(input))
                return true;

        }
        return false;
    }

    public String getUserPassword(String user) {
        Cursor res = db.getAllData();
        String log;
        while (res.moveToNext()) {
            log = res.getString(1);
            if (log.equals(user))
                break;
        }
        return res.getString(2);
    }

    public void registerClicked(View target)
    {
        EditText login = (EditText) findViewById(R.id.login);
        EditText passowrd = (EditText) findViewById(R.id.password);

        String loginText = login.getText().toString();
        String passwordText = passowrd.getText().toString();

        if(!loginText.isEmpty() & !passwordText.isEmpty()) {
            if (!checkIfExists(loginText))
            {
                db.insertData(loginText, passwordText.toString());
                Intent myIntent = new Intent(Login.this, AccountType.class);
                Login.this.startActivity(myIntent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Login taken", Toast.LENGTH_LONG).show();
                login.setText("");
                passowrd.setText("");
            }
        }
    }


    public void signinClicked(View target)
    {
        EditText login = (EditText) findViewById(R.id.login);
        EditText passowrd = (EditText) findViewById(R.id.password);

        String loginText = login.getText().toString();
        String passwordText = passowrd.getText().toString();

        if(!loginText.isEmpty() & !passwordText.isEmpty())
        {
            if (checkIfExists(loginText))
            {
                String pass = getUserPassword(loginText);
                if(pass.equals(passwordText))
                {
                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong login or password", Toast.LENGTH_LONG).show();
                    login.setText("");
                    passowrd.setText("");
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wrong login or password", Toast.LENGTH_LONG).show();
                login.setText("");
                passowrd.setText("");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);

    }


};
