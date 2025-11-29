package com.example.tripplanner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEditTripActivity extends AppCompatActivity {

    EditText nameInput, destinationInput, startDateInput, endDateInput,numberOfTravelers,budgetInput;
    RadioButton typeBusiness, typeLeisure, typeAdventure;
    CheckBox checkPassport;
    Button saveBtn;

    SharedPreferences prefs;
    int editIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_trip);

        prefs = getSharedPreferences("tripData", Context.MODE_PRIVATE);

        nameInput = findViewById(R.id.inputName);
        destinationInput = findViewById(R.id.inputDestination);
        startDateInput = findViewById(R.id.inputStartDate);
        endDateInput = findViewById(R.id.inputEndDate);
        typeBusiness = findViewById(R.id.radioBusiness);
        typeLeisure = findViewById(R.id.radioLeisure);
        typeAdventure = findViewById(R.id.radioAdventure);

        checkPassport = findViewById(R.id.checkPassport);
        numberOfTravelers=findViewById(R.id.inputTravelers);
               saveBtn = findViewById(R.id.btnSave);
        budgetInput=findViewById(R.id.budgetInput);

         startDateInput.setOnClickListener(v -> showDatePicker(startDateInput));
        endDateInput.setOnClickListener(v -> showDatePicker(endDateInput));

         editIndex = getIntent().getIntExtra("editIndex", -1);
        if (editIndex != -1) {
            loadTripData(editIndex);
        }

        saveBtn.setOnClickListener(v -> saveTrip());
    }

    private void showDatePicker(EditText target) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (DatePicker datePicker, int y, int m, int d) ->
                        target.setText(d + "/" + (m + 1) + "/" + y),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private void loadTripData(int index) {
        String prefix = "trip_" + index + "_";

        nameInput.setText(prefs.getString(prefix + "name", ""));
        destinationInput.setText(prefs.getString(prefix + "destination", ""));
        startDateInput.setText(prefs.getString(prefix + "start", ""));
        endDateInput.setText(prefs.getString(prefix + "end", ""));

        String type = prefs.getString(prefix + "type", "");
        if (type.equals("Business")) typeBusiness.setChecked(true);
        if (type.equals("Leisure")) typeLeisure.setChecked(true);
        if (type.equals("Adventure")) typeAdventure.setChecked(true);
        String tripTravelers = prefs.getString(prefix + "travelers", "1");

        numberOfTravelers.setText(tripTravelers);

        checkPassport.setChecked(prefs.getBoolean(prefix + "passport", false));

       String tripBudget = prefs.getString(prefix + "budget", "0");
        budgetInput.setText( tripBudget );

    }

    private void saveTrip() {
        if (nameInput.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();

        int index = (editIndex == -1) ? prefs.getInt("count", 0) : editIndex;
        String prefix = "trip_" + index + "_";

        editor.putString(prefix + "name", nameInput.getText().toString());
        editor.putString(prefix + "destination", destinationInput.getText().toString());
        editor.putString(prefix + "start", startDateInput.getText().toString());
        editor.putString(prefix + "end", endDateInput.getText().toString());
        editor.putString(prefix + "travelers", numberOfTravelers.getText().toString());

         editor.putString(prefix + "budget", budgetInput.getText().toString());

        String type = typeBusiness.isChecked() ? "Business" :
                typeLeisure.isChecked() ? "Leisure" : "Adventure";

        editor.putString(prefix + "type", type);
        editor.putBoolean(prefix + "passport", checkPassport.isChecked());

        if (editIndex == -1) {
            editor.putInt("count", index + 1);
            editor.putInt("currentIndex", index + 1);
        }

        editor.apply();

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        finish();
    }




    }


