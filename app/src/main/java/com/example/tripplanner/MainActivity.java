package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button addBtn;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         View splash = findViewById(R.id.splashScreen);
        splash.postDelayed(() -> splash.setVisibility(View.GONE), 3000);

         addBtn = findViewById(R.id.btnAddTrip);
        prefs = getSharedPreferences("TripData", Context.MODE_PRIVATE);

        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTripActivity.class);
            startActivity(intent);
        });
    }

     public void onclick(View view) {
        Intent intent = new Intent(MainActivity.this, TripsActivity.class);
        startActivity(intent);
    }

    public void showAboutUs(View view) {
        Toast.makeText(this,
                "Trip Planner - Easy Travel Planning\n\nWe make planning your next destination simple and enjoyable.\n\nStart your journey today!\n",
                Toast.LENGTH_LONG).show();
    }
}




