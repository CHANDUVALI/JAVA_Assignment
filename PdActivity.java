package com.example.sois.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PdActivity extends AppCompatActivity {
    EditText age,gender,mobile,pid;
    Button submit;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);

        pid =findViewById(R.id.pid);
        age = findViewById(R.id.age);
        gender =findViewById(R.id.gender);
        mobile = findViewById(R.id.mobile);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id= pid.getText().toString();
                int user_age= Integer.parseInt(age.getText().toString());
                String user_gender = gender.getText().toString();
                int user_mobile = Integer.parseInt(mobile.getText().toString());

                db= new DatabaseHelper(getApplicationContext());

                boolean user_result=db.insertDataIntoPd(user_id,user_age,user_gender,user_mobile);
                if(user_result==true)
                {
                    Toast.makeText(getApplicationContext(),"Patient details entered sucessfully",Toast.LENGTH_LONG).show();
                    Intent i2= new Intent(getApplicationContext(),FetchPd.class);
                    startActivity(i2);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter patient deatils",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}



