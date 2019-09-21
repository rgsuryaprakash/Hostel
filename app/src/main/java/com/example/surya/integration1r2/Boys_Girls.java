package com.example.surya.integration1r2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Boys_Girls extends AppCompatActivity {

    CardView boys_hostel,girls_hostel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys__girls);

        boys_hostel = findViewById(R.id.boys);
        girls_hostel = findViewById(R.id.girls);

        boys_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent host_b = new Intent(Boys_Girls.this,List.class);
                host_b.putExtra("value","MALE");
                startActivity(host_b);
            }
        });

        girls_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent host_g = new Intent(Boys_Girls.this, List.class);
                host_g.putExtra("value","FEMALE");
                startActivity(host_g);

            }
        });
    }
}
