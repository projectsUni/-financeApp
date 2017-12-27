package com.example.joanna.mobilnyportfel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class EnterExpenses extends AppCompatActivity {

    DatabaseHelper db;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK)
            {
                String category = data.getStringExtra("category");
                Button cat = (Button) findViewById(R.id.expenseCategory);
                cat.setText(category);
            }
        }
    }
    public void categoryClicked()
    {
        Intent intent = new Intent(this, ExpensesCategory.class);
        startActivityForResult(intent,1);
    }

    public void confirmClicked()
    {
        TextView name  = (TextView) findViewById(R.id.expense);
        TextView category = (TextView) findViewById(R.id.expenseCategory);
        TextView zl = (TextView) findViewById(R.id.zl);
        TextView gr = (TextView) findViewById(R.id.grosz);
        DatePicker date = (DatePicker) findViewById(R.id.date);

        Vector<String> data = new Vector<String>();

        data.add(name.getText().toString());
        data.add(category.getText().toString());

        String p = zl.getText().toString() + "," + gr.getText().toString();
        data.add(p);
        String enteredDate = date.getYear() + "-" + (date.getMonth()+1) + "-" + date.getDayOfMonth();
        data.add(enteredDate);
        db.insertData("expensesTable", data);

        Toast.makeText(getApplicationContext(), "Dodane",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_expenses);
        db = new DatabaseHelper(this);

        Button category = (Button) findViewById(R.id.expenseCategory);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ categoryClicked();}}
        );


        Button confirm = (Button) findViewById(R.id.expenseConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ confirmClicked();}}
        );
    }
}
