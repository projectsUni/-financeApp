package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterIncome extends AppCompatActivity {

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK)
            {
                String category = data.getStringExtra("category");
                Button cat = (Button) findViewById(R.id.incomeCategory);
                cat.setText(category);
            }
        }
    }
    public void categoryClicked()
    {
        Intent intent = new Intent(this, IncomeCategory.class);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_income);
        Button category = (Button) findViewById(R.id.incomeCategory);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ categoryClicked();}}
        );
    }
}
