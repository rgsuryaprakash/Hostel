package com.example.surya.integration1r2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import java.nio.channels.FileLockInterruptionException;

public class Uploads extends AppCompatActivity {

    CardView textupload,searchcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploads);

        textupload = findViewById(R.id.textupld);
        searchcard = findViewById(R.id.searchdata);

        textupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent txtupld = new Intent(Uploads.this,TextUpload.class);
                startActivity(txtupld);

            }
        });

        searchcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fetch =  new Intent(Uploads.this,searchData.class);
                startActivity(fetch);
            }
        });
    }
}
