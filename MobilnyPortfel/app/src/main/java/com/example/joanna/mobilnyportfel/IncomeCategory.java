package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class IncomeCategory extends AppCompatActivity {

    public void buttonClicked(View v)
    {
        String category = "";
        switch (v.getId()) {
            case R.id.scholarship:
                category = "Stypendium";
                break;
            case R.id.payment:
                category = "wyplata";
                break;
            case R.id.prize:
                category = "wygrana";
                break;
            case R.id.loan:
                category = "pożyczka";
                break;
            case R.id.sale:
                category = "sprzedaż";
                break;
            case R.id.otherIncome:
                category = "inne";
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("category", category);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void setVisible()
    {
        Vector <Button> notVisible = new Vector<Button>();
        notVisible.add((Button) findViewById(R.id.scholarship));
        for(Button v : notVisible)
            v.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_category);

        Vector<Button> categories = new Vector<Button>();
        categories.add((Button)findViewById(R.id.scholarship));
        categories.add((Button)findViewById(R.id.payment));
        categories.add((Button)findViewById(R.id.prize));
        categories.add((Button)findViewById(R.id.sale));
        categories.add((Button)findViewById(R.id.loan));
        categories.add((Button)findViewById(R.id.otherIncome));
        //setVisible();  -- this func allows us to change shown categories, for example only student will have scholarship
        for(Button v : categories)
        {
            if(v.getVisibility()== View.VISIBLE) {
                v.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             buttonClicked(view);
                                         }
                                     }
                );
            }
        }
    }
}
