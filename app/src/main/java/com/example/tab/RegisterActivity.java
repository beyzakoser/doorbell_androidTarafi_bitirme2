package com.example.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    TextView login;
    EditText firstName,lastName,email,password,againPassword;
    Button signUp;
    FirebaseDatabase db;
    DatabaseReference dbRef;
    DatabaseReference dbRefKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=FirebaseDatabase.getInstance();
        login=(TextView)findViewById(R.id.text_logIn);
        firstName=(EditText)findViewById(R.id.text_name);
        lastName=(EditText)findViewById(R.id.text_surname);
        email=(EditText)findViewById(R.id.text_email);
        password=(EditText)findViewById(R.id.text_password);
        againPassword=(EditText)findViewById(R.id.text_againPassword);
        signUp=(Button)findViewById(R.id.btn_signUp) ;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }
    private void SignUp(){
        String girilenAd=firstName.getText().toString();
        String girilenSoyad=lastName.getText().toString();
        String girilenMail=email.getText().toString();
        String girilenSifre=password.getText().toString();
        String girilenTekrarSifre=againPassword.getText().toString();

        if(TextUtils.isEmpty(girilenAd)){
            Toast.makeText(this,"Please enter a First Name!",Toast.LENGTH_LONG).show();

        }else if(TextUtils.isEmpty(girilenSoyad)){
            Toast.makeText(this,"Please enter a Last Name!",Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(girilenMail)){
            Toast.makeText(this,"Please enter a Email Address!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(girilenSifre)){
            Toast.makeText(this,"Please enter a Password!",Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(girilenTekrarSifre)){
            Toast.makeText(this,"Please enter a Confirm Password!",Toast.LENGTH_LONG).show();

        }

        dbRef = db.getReference("Users");
        String key=dbRef.push().getKey();
        dbRefKey=db.getReference("Users/"+key);
        dbRefKey.setValue(new User(girilenAd,girilenSoyad,girilenMail,girilenSifre));
        Toast.makeText(this,"Successfully Registered !",Toast.LENGTH_LONG).show();

        firstName.setText("");
        lastName.setText("");
        email.setText("");
        password.setText("");
        againPassword.setText("");



    }
}