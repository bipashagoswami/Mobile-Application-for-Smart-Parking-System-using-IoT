package com.testing.parkpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ParkingDetails_Activity extends AppCompatActivity {

    ImageButton logout;
    TextView direction;
    TextView number;
    DatabaseReference dbref;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);

        final TextView tittle = findViewById(R.id.namePD);
        final ImageView logo = findViewById(R.id.logoPD);
        final ImageButton error = findViewById(R.id.aboutusPD);



        tittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingDetails_Activity.this, MainActivity.class));
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingDetails_Activity.this, MainActivity.class));
            }
        });

        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingDetails_Activity.this, AboutUs_Activity.class));
            }
        });

        number = findViewById(R.id.FData);
        dbref = FirebaseDatabase.getInstance().getReference().child("data");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    int value = childSnapshot.getValue(Integer.class);
                        char character = (char) value;
                    String characterString = String.valueOf(character);
                    number.setText(characterString);
                        //number.setText(String.(value));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                number.setText("E");
            }
        });


        logout = findViewById(R.id.logoutPD);
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(view->{
            mAuth.signOut();
            Toast.makeText(ParkingDetails_Activity.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ParkingDetails_Activity.this, Login_Activity.class));
            finish();
        });

        direction = findViewById(R.id.location);
        direction.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

