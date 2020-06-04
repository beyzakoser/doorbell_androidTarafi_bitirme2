package com.example.tab;

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

public class LoginActivity extends AppCompatActivity {
    TextView register;
    TextView forgot_password;
    EditText mail;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        String girilenMail=mail.getText().toString();
        String girilenSifre=password.getText().toString();
        if(TextUtils.isEmpty(girilenMail)){
            Toast.makeText(this,"Please enter a Email!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(girilenSifre)){
            Toast.makeText(this,"Please enter a Password!",Toast.LENGTH_LONG).show();

        }

    }
}
