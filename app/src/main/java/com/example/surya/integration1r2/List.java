package com.example.surya.integration1r2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    ListView list;
    Bundle value;
    FirebaseFirestore firebaseFirestore;
    ArrayList hstlname_list = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = findViewById(R.id.hstl_list);
        value=getIntent().getExtras();
        firebaseFirestore=FirebaseFirestore.getInstance();

      firebaseFirestore.collection(value.getString("value")).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
              for(DocumentSnapshot documentSnapshot:task.getResult()){
                  hstlname_list.add(documentSnapshot.getId());
              }
              ArrayAdapter arrayAdapter=new ArrayAdapter(List.this,android.R.layout.simple_spinner_dropdown_item,hstlname_list);
              list.setAdapter(arrayAdapter);
          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(getApplicationContext(),"Some Problem May Occured",Toast.LENGTH_SHORT).show();
          }
      });

      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
              Intent intent =new Intent(List.this,ShowDetails.class);
              intent.putExtra("hst_typ",value.getString("value"));
              intent.putExtra("hst_name",parent.getItemAtPosition(position).toString());
              startActivity(intent);
          }
      });
    }
}
