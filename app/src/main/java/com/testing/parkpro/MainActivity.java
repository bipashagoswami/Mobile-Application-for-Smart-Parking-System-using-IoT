package com.testing.parkpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    SearchView mySearchView;
    ListView myListView;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    ImageButton about;
    ImageView imageM;
    TextView textM;
    ImageButton logoutbtn;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        about = findViewById(R.id.aboutusM);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutUs_Activity.class));
            }
        });

        imageM = findViewById(R.id.logoM);
        imageM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        textM = findViewById(R.id.tittleM);
        textM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        mySearchView = findViewById(R.id.searchview);
        myListView = findViewById(R.id.listview);


        list = new ArrayList<String>();

        list.add("Bongaigaon");
        list.add("Dibrugarh");
        list.add("Dispur");
        list.add("Guwahati");
        list.add("Jorhat");
        list.add("Silchar");
        list.add("Tezpur");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        myListView.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, Bongaigaon_Activity.class));
                } else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, Dibrugarh_Activity.class));
                } else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, Dispur_Activity.class));
                } else if (position == 3) {
                    startActivity(new Intent(MainActivity.this, Guwahati_Activity.class));
                } else if (position == 4) {
                    startActivity(new Intent(MainActivity.this, Jorhat_Activity.class));
                } else if (position == 5) {
                    startActivity(new Intent(MainActivity.this, Silchar_Activity.class));
                } else if (position == 6) {
                    startActivity(new Intent(MainActivity.this, Tezpur_Activity.class));
                } else {

                }

            }
        });

        logoutbtn = findViewById(R.id.logoutM);
        mAuth = FirebaseAuth.getInstance();

        logoutbtn.setOnClickListener(view->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, Login_Activity.class));
        });
    }

}