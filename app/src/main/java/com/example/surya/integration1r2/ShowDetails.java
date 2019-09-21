package com.example.surya.integration1r2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class ShowDetails extends AppCompatActivity {

    TextView rhstlname,rmngrname,rwrdnname,rdebtyname,rrooms,rordrooms,rcontacts;
    FirebaseFirestore firebaseFirestore;
    ImageView imageView;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        firebaseFirestore=FirebaseFirestore.getInstance();
        rhstlname = findViewById(R.id.name_retri);
        imageView=findViewById(R.id.img_retri);
        rmngrname = findViewById(R.id.mngr_retri);
        rwrdnname = findViewById(R.id.wrdn_retri);
        rdebtyname = findViewById(R.id.debty_retri);
        rrooms = findViewById(R.id.no_of_rooms);
        rordrooms = findViewById(R.id.ordroom_retri);

        b=getIntent().getExtras();

        //rsplrooms = findViewById(R.id.splroom_retri);
        rcontacts = findViewById(R.id.contact_retri);
        firebaseFirestore.collection(b.getString("hst_typ")).document(b.getString("hst_name")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot=task.getResult();
                Picasso.with(getApplicationContext()).load(documentSnapshot.getString("imguri")).into(imageView);
                rhstlname.setText(documentSnapshot.getString("hostelname"));
                rmngrname.setText(documentSnapshot.getString("manager"));
                rwrdnname.setText(documentSnapshot.getString("warden"));
                rdebtyname.setText(documentSnapshot.getString("debutywarden"));
                rrooms.setText(documentSnapshot.getString("totalrooms"));
                rordrooms.setText(documentSnapshot.getString("normrooms"));
                //rsplrooms.setText(documentSnapshot.getString("splrooms"));
                rcontacts.setText(documentSnapshot.getString("contact"));
            }
        });

    }
}
