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

        String exName = name.getText().toString();
        String exCat = category.getText().toString();
        String zlote = zl.getText().toString();
        String grosze = gr.getText().toString();


        if (!exName.matches("")){
            if (!exCat.matches("Kategoria")){
                if (!zlote.matches("") && !grosze.matches("")){

                    data.add(exName);
                    data.add(exCat);

                    String fullPrice = zlote + "," + grosze;
                    data.add(fullPrice);
                    String enteredDate = date.getYear() + "-" + (date.getMonth()+1) + "-" + date.getDayOfMonth();
                    data.add(enteredDate);


                    db.insertData("expensesTable", data);

                    Toast.makeText(getApplicationContext(), "Dodane",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Menu.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Wprowadź cene", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Wybierz kategorie", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Wprowadz nazwę wydatku", Toast.LENGTH_SHORT).show();
        }


            //name, category, price,

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
