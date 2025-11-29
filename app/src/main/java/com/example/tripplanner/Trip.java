package com.example.tripplanner;

public class Trip {
    private String id;
    private String name;
    private String destination;
    private String startDate;
    private String endDate;
    private String type;
    private int numberOfTravelers;
    private boolean needPassport;
    private String budget;


    public Trip(String id , String name, String budget, String destination, String type, String endDate, String startDate, int numberOfTravelers , boolean needPassport) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.numberOfTravelers = numberOfTravelers;
        this.type = type;
        this.endDate = endDate;
        this.startDate = startDate;
        this.destination = destination;
        this.needPassport=needPassport;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isNeedPassport() {
        return needPassport; }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }







}
