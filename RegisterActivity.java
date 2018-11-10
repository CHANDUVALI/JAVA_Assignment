package com.example.sois.healthcare;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText e1;
    EditText e2;
    //EditText e3;

    Button b1;
    String data1;
    String data2;
    //String data3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.pass);
        //e3=(EditText)findViewById(R.id.confirm);

        b1=(Button)findViewById(R.id.button);
        db=new DatabaseHelper(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data1 = e1.getText().toString();
                data2 = e2.getText().toString();
                //data3 = e3.getText().toString();



                boolean insert = db.insertData(data1, data2);
                if (insert == true) {
                    Toast.makeText(getApplication(), "Registered ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


                else{
                    Toast.makeText(getApplication(), "Not Registered ", Toast.LENGTH_SHORT).show();

                }




            }
        });

    }
}













