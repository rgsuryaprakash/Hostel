package com.example.surya.integration1r2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class TextUpload extends AppCompatActivity {

    EditText hostelname,manager,warden,debutywarden,tot_room,norm_room,contact_number;
    TextView imgTxt;
    Uri mImageUri;
    Button insert;
    ImageView imageView1;
    RadioButton hstl_ty_rd;
    RadioGroup hstl_ty_rg;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    int tot,norm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_upload);
        firebaseFirestore=FirebaseFirestore.getInstance();
        hostelname=findViewById(R.id.hstl_name);
        manager = findViewById(R.id.mngr_name);
        warden = findViewById(R.id.wrdn_name);
        imgTxt=findViewById(R.id.image);
        imageView1=findViewById(R.id.imageView);
        storageReference=FirebaseStorage.getInstance().getReference();
        debutywarden = findViewById(R.id.dbty_wrdn_name);
        tot_room = findViewById(R.id.tot_room_count);
        norm_room = findViewById(R.id.ord_room_count);
        contact_number = findViewById(R.id.contact);
        hstl_ty_rg = findViewById(R.id.for_whom);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...!");

        insert = findViewById(R.id.submit);

        imgTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(), "Insertion Successfully", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(hostelname.getText())) {
                    hostelname.setError("Enter Hostel Name");
                    hostelname.requestFocus();
                }
                else if(TextUtils.isEmpty(manager.getText()))
                {
                    manager.setError("Enter Manager Name");
                    manager.requestFocus();
                }
                else if(TextUtils.isEmpty(warden.getText()))
                {
                    warden.setError("Enter Warden Name");
                    warden.requestFocus();
                }
                else if(TextUtils.isEmpty(debutywarden.getText()))
                {
                    debutywarden.setError("Enter Debuty Warden Name");
                    debutywarden.requestFocus();
                }

                else if(TextUtils.isEmpty(tot_room.getText()))
                {
                    tot_room.setError("Enter the Room Count");
                    tot_room.requestFocus();

                }

                else if(TextUtils.isEmpty(norm_room.getText()))
                {
                    norm_room.setError("Enter Normal Rooms Count");
                    norm_room.requestFocus();

                }

                else if(TextUtils.isEmpty(contact_number.getText()))
                {

                    contact_number.setError("Enter Contact Number");
                    contact_number.requestFocus();
                }

                else if (Integer.parseInt(norm_room.getText().toString()) >  Integer.parseInt(tot_room.getText().toString()))
                {
                    norm_room.setError("Normal Room Should be Lower than Total Room Count");
                    norm_room.requestFocus();
                }

                else if (contact_number.getText().toString().length() < 9)
                {
                    contact_number.setError("Enter Valid Contact Number");
                    contact_number.requestFocus();
                }
                else {
                    progressDialog.show();
                    final Map dbmap = new HashMap();
                    int ch = hstl_ty_rg.getCheckedRadioButtonId();
                    hstl_ty_rd = findViewById(ch);
                    dbmap.put("hostelname", hostelname.getText().toString());
                    dbmap.put("manager", manager.getText().toString());
                    dbmap.put("warden", warden.getText().toString());
                    dbmap.put("debutywarden", debutywarden.getText().toString());
                    dbmap.put("gender", hstl_ty_rd.getText().toString());
                    dbmap.put("totalrooms", tot_room.getText().toString());
                    dbmap.put("normrooms", norm_room.getText().toString());
                    //dbmap.put("splrooms", spl_room.getText().toString());
                    dbmap.put("contact", contact_number.getText().toString());
                    storageReference.child("hostel/"+hostelname.getText().toString()).putFile(mImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            storageReference.child("hostel/"+hostelname.getText().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dbmap.put("imguri",uri.toString());
                                    firebaseFirestore.collection(hstl_ty_rd.getText().toString()).document(hostelname.getText().toString()).set(dbmap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            Toast.makeText(getApplicationContext(), "Insertion Successfully", Toast.LENGTH_SHORT).show();
                                            hostelname.setText("");
                                            manager.setText("");
                                            warden.setText("");
                                            debutywarden.setText("");
                                            tot_room.setText("");
                                            norm_room.setText("");
                                            contact_number.setText("");
                                            hstl_ty_rg.clearCheck();
                                            imageView1.setVisibility(View.INVISIBLE);


                                            progressDialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            storageReference.child("hostel/"+hostelname.getText().toString()).delete();
                                            Toast.makeText(getApplicationContext(), "Insertion FAILED", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    storageReference.child("hostel/"+hostelname.getText().toString()).delete();
                                    Toast.makeText(getApplicationContext(),"INSERTION FAILED",Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"INSERTION FAILED",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int)progress) + "% Uploaded...");
                        }
                    });
                }
            }
        });


    }

    public void openFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 &&resultCode==RESULT_OK
                &&data!=null &&data.getData()!=null){
            mImageUri=data.getData();
            Picasso.with(this).load(mImageUri).into(imageView1);
            imageView1.setVisibility(View.VISIBLE);
        }
    }

}
