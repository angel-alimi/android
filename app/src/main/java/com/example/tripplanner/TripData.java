package com.example.tripplanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class TripData {
    static List<Trip> trips = new ArrayList<>();


        public static List<Trip> getTrips(Context context) {

            SharedPreferences prefs = context.getSharedPreferences("tripData", Context.MODE_PRIVATE);
            int count = prefs.getInt("count", 0);

             trips = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                String prefix = "trip_" + i + "_";

                String name = prefs.getString(prefix + "name", "");
                String dest = prefs.getString(prefix + "destination", "");
                String start = prefs.getString(prefix + "start", "");
                String end = prefs.getString(prefix + "end", "");
                String type = prefs.getString(prefix + "type", "");
                int travelers = Integer.parseInt(prefs.getString(prefix + "travelers", "1"));
                boolean passport = prefs.getBoolean(prefix + "passport", false);
                String budget = prefs.getString(prefix + "budget", "0");

                if(TextUtils.isEmpty(  name)) {
                    continue;
                }
                trips.add(new Trip(
                        prefix,
                        name,
                        budget,
                        dest,
                        type,
                        end,
                        start,
                        travelers,
                        passport
                ));
            }

            return trips;
        }
}
