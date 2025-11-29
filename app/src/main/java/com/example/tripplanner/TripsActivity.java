package com.example.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class TripsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TripAdapter adapter;
    private List<Trip> tripList;
    private EditText searchInput;

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null) {
            adapter.setTripList( TripData.getTrips(this) );

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        recyclerView = findViewById(R.id.recyclerViewTrips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tripList = TripData.getTrips(this);
        adapter = new TripAdapter(tripList, new OnTripClickListener() {

            @Override
            public void onTripClick(int pos) {
                Trip trip = tripList.get(pos);
                Intent intent = new Intent(TripsActivity.this, TripDetailsActivity.class);
                intent.putExtra("tripIndex", pos);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);


        searchInput = findViewById( R.id.searchInput );
        searchInput.addTextChangedListener( new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                         adapter.filter( charSequence.toString() );
            }
        } );


    }

   public interface OnTripClickListener{
        void onTripClick(int pos);
   }
}
