package com.example.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    TextView forgot_password;
    EditText mail;
    EditText password;
    Button login;
    FirebaseDatabase db;
    DatabaseReference dbRefReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=FirebaseDatabase.getInstance();
        register=(TextView)findViewById(R.id.text_register);
        forgot_password=(TextView)findViewById(R.id.text_forgot_password);
        mail=(EditText)findViewById(R.id.text_mail);
        password=(EditText)findViewById(R.id.text_password);
        login=(Button)findViewById(R.id.btn_login) ;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentLogin);
            }
        });


    }
    private void logIn(){
        final String girilenMail=mail.getText().toString();
        final String girilenSifre=password.getText().toString();
        if(TextUtils.isEmpty(girilenMail)){
            Toast.makeText(this,"Please enter a Email!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(girilenSifre)){
            Toast.makeText(this,"Please enter a Password!",Toast.LENGTH_LONG).show();

        }else {
            dbRefReceive = db.getReference("Users");
            dbRefReceive.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        HashMap<String,String> hashMap=(HashMap<String,String>)ds.getValue();
                        String userMail=hashMap.get("mail");
                        String password=hashMap.get("password");
                        if(girilenMail.equals(userMail) && girilenSifre.equals(password)){
                            Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_LONG).show();
                            Intent intentLogin=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intentLogin);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
