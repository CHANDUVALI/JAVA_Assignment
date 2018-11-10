package com.example.sois.healthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FetchPd extends AppCompatActivity {
    EditText e1;
    Button b1;
    DatabaseHelper db;
    String name;
    List item;
    private ArrayAdapter<String> adapter;
    ListView  list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_pd);
        e1 = findViewById(R.id.search);
        b1 = findViewById(R.id.serachb);
        list = findViewById(R.id.my);

        item = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        list.setAdapter(adapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String id = e1.getText().toString();
                db = new DatabaseHelper(getApplicationContext());
                name = db.fetchBypatientId(id);
                if(name != null)
                {
                    Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                    adapter.add(name);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}

