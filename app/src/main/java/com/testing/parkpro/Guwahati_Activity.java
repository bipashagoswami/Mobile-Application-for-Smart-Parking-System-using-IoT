package com.testing.parkpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Guwahati_Activity extends AppCompatActivity {

    ImageButton btnlogout;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guwahati);

        final Button select = findViewById(R.id.selectbutton);
        final TextView tittle = findViewById(R.id.parkproH);
        final ImageView logo = findViewById(R.id.logoH);
        final ImageButton error = findViewById(R.id.aboutus);
        final Loading loading = new Loading(Guwahati_Activity.this);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.startLoading();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismissLoading();
                    }
                },5000);
                startActivity(new Intent(Guwahati_Activity.this, ParkingDetails_Activity.class));
            }
        });

        tittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Guwahati_Activity.this, MainActivity.class));
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Guwahati_Activity.this, MainActivity.class));
            }
        });

        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Guwahati_Activity.this, AboutUs_Activity.class));
            }
        });

        btnlogout = findViewById(R.id.logoutG);
        mAuth = FirebaseAuth.getInstance();

        btnlogout.setOnClickListener(view->{
            mAuth.signOut();
            Toast.makeText(Guwahati_Activity.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Guwahati_Activity.this, Login_Activity.class));
            finish();
        });
    }
}
