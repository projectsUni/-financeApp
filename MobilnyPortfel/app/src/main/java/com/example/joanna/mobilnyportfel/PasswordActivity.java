package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    public void buttonClicked()
    {
        //if msg[0] == true
        //get password from database, authorize and start activity from second substring (switch case)
        //else
        //enter profile with type from second substring and given PIN to database and start this activity

        //start next activity

        Toast.makeText(getApplicationContext(), "Clicked button, EnterExpensesActivity should start", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EnterExpensesActivity.class);
        // intent.putExtra("EXTRA_MESSAGE", msg); -- do we need to send extra info?

        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //getmsg
        //substring msg[2] - ":" as delimeter
        Button confirm = (Button) findViewById(R.id.confirmPIN);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ buttonClicked();}}
        );
    }
}
