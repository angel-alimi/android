package com.example.tripplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TripDetailsActivity extends AppCompatActivity {

    TextView name, destination, dates, type, travelers, passport,budget;
    Button editBtn, deleteBtn;

    SharedPreferences prefs;
    String tripId;

    @Override
    protected void onResume() {
        loadTrip(tripId);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        prefs = getSharedPreferences("tripData", Context.MODE_PRIVATE);

        tripId = getIntent().getStringExtra("tripId");

        name = findViewById(R.id.detailName);
        destination = findViewById(R.id.detailDestination);
        dates = findViewById(R.id.detailDates);
        type = findViewById(R.id.detailType);
        travelers = findViewById(R.id.tvdetailsTravelers);
        passport =findViewById(R.id.tvdetailsPassport);
        budget=findViewById(R.id.tvdetailsBudget );
        editBtn = findViewById(R.id.btnEdit);
        deleteBtn = findViewById(R.id.btnDelete);



        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditTripActivity.class);
            intent.putExtra("editId", tripId);
            startActivity(intent);
        });

        deleteBtn.setOnClickListener(v -> deleteTrip());
    }

    private void loadTrip(String id) {

        String prefix = id;

        String tripName = prefs.getString(prefix + "name", "");
        String dest = prefs.getString(prefix + "destination", "");
        String start = prefs.getString(prefix + "start", "");
        String end = prefs.getString(prefix + "end", "");
        String tripType = prefs.getString(prefix + "type", "");
        int tripTravelers = Integer.parseInt(prefs.getString(prefix + "travelers", "1"));
        boolean tripIsPassport = prefs.getBoolean(prefix + "passport", false);
        String tripBudget = prefs.getString(prefix + "budget", "0");

        name.setText(tripName);
        destination.setText(dest);
        dates.setText(start + " → " + end);

        travelers.setText("Travelers: " + tripTravelers);
        passport.setText("Passport: " + (tripIsPassport ? "Yes" : "No"));
         budget.setText("Budget: " +tripBudget);
        type.setText("Type: " +tripType);
    }

    private void deleteTrip() {
        SharedPreferences.Editor editor = prefs.edit();

        String prefix = tripId;

        editor.remove(prefix + "name");
        editor.remove(prefix + "destination");
        editor.remove(prefix + "start");
        editor.remove(prefix + "end");
        editor.remove(prefix + "type");
        editor.remove(prefix + "passport");
        editor.remove(prefix + "travelers");
        editor.remove(prefix + "budget");

//        int count =  prefs.getInt("count", 0);
//        editor.putInt("count", count - 1);

        editor.apply();
        finish();
    }
}
