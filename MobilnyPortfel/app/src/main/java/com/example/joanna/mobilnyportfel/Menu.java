package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class Menu extends AppCompatActivity {

    public void buttonClicked(View v)
    {
        Intent intent;
        switch (v.getId()) {
            case R.id.enterExpenses:
                intent = new Intent(this, EnterExpenses.class);
                startActivity(intent);
                break;
            case R.id.enterIncome:
                intent = new Intent(this, EnterIncome.class);
                startActivity(intent);
                break;
            case R.id.shoppingList:
                intent = new Intent( this, ShoppingList.class );
                startActivity(intent);
                break;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Vector<Button> options = new Vector<Button>();
        options.add((Button)findViewById(R.id.enterExpenses));
        options.add((Button) findViewById(R.id.enterIncome));
        options.add((Button) findViewById(R.id.shoppingList));
        for(Button v : options)
        {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){ buttonClicked(view);}}
            );
        }
    }
}
