package com.example.surya.integration1r2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    CardView hostel,randd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hostel = findViewById(R.id.hostel);
        randd = findViewById(R.id.rede);

        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kec_hostel = new Intent(Home.this,Hostel.class);
                startActivity(kec_hostel);
            }
        });

        randd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(Home.this, "Under Construction", Toast.LENGTH_SHORT).show();
                Intent kec_rd = new Intent(Home.this, Devlp.class);
                startActivity(kec_rd);

            }
        });
    }
}
