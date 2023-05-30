package com.testing.parkpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class Login_Activity extends AppCompatActivity {
    Button btnLogin;
    EditText etLoginEmail;
    EditText etLoginPassword;

    TextView forgotPassword;
    TextView tvRegisterHere;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.loginbutton);
        etLoginEmail = findViewById(R.id.Lusername);
        etLoginPassword = findViewById(R.id.LPassword);
        forgotPassword = findViewById(R.id.ForgotPassword);
        tvRegisterHere = findViewById(R.id.textRegistration);
        final Loading loading = new Loading(Login_Activity.this);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view->{
            loading.startLoading();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loading.dismissLoading();
                }
            },5000);
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view->{
            startActivity(new Intent(Login_Activity.this, Register_Activity.class));
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, ForgotPassword_Activity.class));
            }
        });

    }

    private void loginUser(){
        String email = etLoginEmail.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login_Activity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login_Activity.this, MainActivity.class));
                    }else{
                        Toast.makeText(Login_Activity.this, "Login Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }


}
