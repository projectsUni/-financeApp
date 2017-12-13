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
                category = "Zakupy spożywcze";
                break;
            case R.id.fun:
                category = "Rozrywka";
                break;
            case R.id.rtvagd:
                category = "RTV/AGD";
                break;
            case R.id.sport:
                category = "Sport";
                break;
            case R.id.moto:
                category = "Motoryzacja";
                break;
            case R.id.exchange:
                category = "Akcje / obligacje";
                break;
            case R.id.bills:
                category = "Rachunki";
                break;
            case R.id.clothes:
                category = "Odzież / obuwie / akcesoria";
                break;
            case R.id.cosmetics:
                category = "Kosmetyki / drogeria";
                break;
            case R.id.otherExpenses:
                category = "Inne";
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
