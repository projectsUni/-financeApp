package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class ExpensesCategory extends AppCompatActivity {
    public void buttonClicked(View v)
    {
        String category = "";
        switch (v.getId()) {
            case R.id.grocery:
                category = "zakupy spożywcze";
                break;
            case R.id.fun:
                category = "rozrywka";
                break;
            case R.id.rtvagd:
                category = "rtv/agd";
                break;
            case R.id.sport:
                category = "sport";
                break;
            case R.id.moto:
                category = "motoryzacja";
                break;
            case R.id.exchange:
                category = "akcje / obligacje";
                break;
            case R.id.bills:
                category = "rachunki";
                break;
            case R.id.clothes:
                category = "odzież / obuwie / akcesoria";
                break;
            case R.id.cosmetics:
                category = "kosmetyki / drogeria";
                break;
            case R.id.otherExpenses:
                category = "inne";
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("category", category);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_category);
        Vector<Button> categories = new Vector<Button>();
        categories.add((Button)findViewById(R.id.grocery));
        categories.add((Button)findViewById(R.id.fun));
        categories.add((Button)findViewById(R.id.rtvagd));
        categories.add((Button)findViewById(R.id.sport));
        categories.add((Button)findViewById(R.id.moto));
        categories.add((Button)findViewById(R.id.exchange));
        categories.add((Button)findViewById(R.id.bills));
        categories.add((Button)findViewById(R.id.clothes));
        categories.add((Button)findViewById(R.id.cosmetics));
        categories.add((Button)findViewById(R.id.otherExpenses));

        for(Button v : categories)
        {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){ buttonClicked(view);}}
            );
        }
    }
}
