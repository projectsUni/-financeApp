package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class SortData extends AppCompatActivity {

    public void buttonClicked(View v)
    {
        String msg = "";
        switch (v.getId()) {
            case R.id.daily:
                break;
            case R.id.monthly:
                break;
            case R.id.yearly:
                break;
            case R.id.allexpenses:
                Intent intent = new Intent(this, DisplayExpenses.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_data);

        Vector<Button> buttons = new Vector<>();
        Button daily = (Button) findViewById(R.id.daily); buttons.add(daily);
        Button monthly = (Button) findViewById(R.id.monthly); buttons.add(monthly);
        Button yearly = (Button) findViewById(R.id.yearly); buttons.add(yearly);
        Button all = (Button) findViewById(R.id.allexpenses); buttons.add(all);
        for(Button v : buttons)
        {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){ buttonClicked(view);}}
            );
        }
    }

    }

