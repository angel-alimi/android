package com.example.tripplanner;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private List<Trip> tripList;
    private List<Trip> filteredList;
    TripsActivity.OnTripClickListener listener;
    public TripAdapter(List<Trip> tripList, TripsActivity.OnTripClickListener listener) {
        this.tripList = tripList;
        this.filteredList = new ArrayList<>( tripList);
        this.listener = listener;
    }

    public void setTripList(List<Trip> tripList) {
        filteredList.clear();
        this.tripList.clear();
        this.tripList.addAll(tripList);
        this.filteredList.addAll(tripList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = filteredList.get(position);
        holder.name.setText(trip.getName());
        holder.destination.setText(trip.getDestination());
        holder.dates.setText(trip.getStartDate() + " - " + trip.getEndDate());
        holder.type.setText(trip.getType());
        holder.travelers.setText("Travelers: " + trip.getNumberOfTravelers());
        holder.passport.setText("Passport: " + (trip.isNeedPassport() ? "Yes" : "No"));
        holder.budget.setText("Budget: " +trip.getBudget());

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }


    public void filter(String searchTerm) {
        filteredList.clear();
        if(TextUtils.isEmpty( searchTerm)) {
            filteredList.addAll( tripList );
        }
        else {
            searchTerm = searchTerm.toLowerCase();
            for (Trip t : tripList) {
                if (t.getName() != null && t.getName().toLowerCase().contains( searchTerm ))
                    filteredList.add( t );
            }
        }
        notifyDataSetChanged();
    }

     class TripViewHolder extends RecyclerView.ViewHolder {
        TextView name, destination, dates, type, travelers, passport,budget;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            destination = itemView.findViewById(R.id.tvDestination);
            dates = itemView.findViewById(R.id.tvDates);
            type = itemView.findViewById(R.id.tvType);
            travelers = itemView.findViewById(R.id.tvTravelers);
            passport = itemView.findViewById(R.id.tvPassport);
            budget=itemView.findViewById(R.id.tvBudget );

            itemView.setOnClickListener( v-> {
                if(listener != null) {
                    listener.onTripClick( getAdapterPosition() );
                }
            } );
        }
    }
}
