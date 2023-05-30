package com.testing.parkpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register_Activity extends AppCompatActivity {
    EditText etRegEmail;
    EditText etRegPassword;
    TextView tvLoginHere;
    Button btnRegister;

    EditText name;

    EditText phone;

    EditText cPassword;

    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etRegEmail = findViewById(R.id.emailid);
        etRegPassword = findViewById(R.id.RPassword);
        tvLoginHere = findViewById(R.id.loginR);
        btnRegister = findViewById(R.id.RegistrationButton);
        name = findViewById(R.id.FullName);
        phone = findViewById(R.id.RMobile);
        cPassword = findViewById(R.id.RConPassword);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(view->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view->{
            startActivity(new Intent(Register_Activity.this, Login_Activity.class));
            finish();
        });

    }

    private void createUser(){
        String fullname = name.getText().toString();
        String number = phone.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String conpassword = cPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        } else if (TextUtils.isEmpty(fullname)) {
            name.setError("Full name cannot be empty");
            name.requestFocus();
        } else if (TextUtils.isEmpty(number)) {
            phone.setError("Mobile no. cannot be empty");
            phone.requestFocus();
        } else if (TextUtils.isEmpty(conpassword)) {
            cPassword.setError("Confirm password cannot be empty");
            cPassword.requestFocus();
        } else if (!password.equals(conpassword)) {
            Toast.makeText(Register_Activity.this, "Password are not matching",Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register_Activity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register_Activity.this, Login_Activity.class));

                        firebaseFirestore.collection("user")
                                .document(FirebaseAuth.getInstance().getUid())
                                .set(new UserModel(fullname,number,email));
                    }else {
                        Toast.makeText(Register_Activity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}