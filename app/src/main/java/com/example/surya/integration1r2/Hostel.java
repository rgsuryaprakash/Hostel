package com.example.surya.integration1r2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Hostel extends AppCompatActivity {

    CardView informations,database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);

        informations = findViewById(R.id.hstl_info);
        database = findViewById(R.id.db);

        informations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Hostel.this,Boys_Girls.class);
                startActivity(i);
            }
        });
        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Hostel.this,Login.class);
                startActivity(i);
            }
        });
    }
}

