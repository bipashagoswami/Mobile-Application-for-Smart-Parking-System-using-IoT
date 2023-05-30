package com.testing.parkpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword_Activity extends AppCompatActivity {

    EditText emailF;
    Button submitF;
    TextView blogin;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailF = findViewById(R.id.Femailid);
        submitF = findViewById(R.id.submitbutton);
        blogin = findViewById(R.id.backLogin);


        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword_Activity.this, Login_Activity.class));
                finish();
            }
        });

        submitF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailF.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(ForgotPassword_Activity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPassword_Activity.this, "Email sent", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgotPassword_Activity.this, "Login Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
    }
}
