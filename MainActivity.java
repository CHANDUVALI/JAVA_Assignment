package com.example.sois.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    DatabaseHelper db;
    EditText e1;
    EditText e2;
    Button b1;
    TextView b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.button);
        b2=(TextView) findViewById(R.id.register);
        db=new DatabaseHelper(this);
        b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String p=e1.getText().toString();
                        String n=e2.getText().toString();
                        boolean validate = db.validate(p,n);
                        if(validate==true) {
                            Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_LONG).show();
                            String str = e1.getText().toString();
                            Intent i = new Intent(getApplicationContext(),PdActivity.class);
                            i.putExtra("message", str);


                            startActivity(i);
                        } else
                            Toast.makeText(MainActivity.this,"login failed enter valid data",Toast.LENGTH_LONG).show();

                    }
                });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
}


