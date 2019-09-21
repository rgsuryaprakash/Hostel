package com.example.surya.integration1r2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    Button login;
    EditText user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user.getText()))
                {
                    user.setError("Enter Username");
                    user.requestFocus();
                }
                else if(TextUtils.isEmpty(pass.getText()))
                {
                    pass.setError("Enter Password");
                    pass.requestFocus();
                }
                else
                {
                    if (user.getText().toString().equals("admin")&&pass.getText().toString().equals("admin"))
                    {
                        Intent i =new Intent(Login.this,Uploads.class);
                        startActivity(i);
                        user.setText("");
                        pass.setText("");
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Username/Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
