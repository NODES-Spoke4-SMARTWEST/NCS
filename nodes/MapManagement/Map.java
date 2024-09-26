package com.application.nodes.MapManagement;

import java.util.ArrayList;

public class Map {
    private boolean empty;
    private Coordinates centerCoordinates;
    private ArrayList<Hub> hubs;
    private ArrayList<Facility> facilities;
    private ArrayList<District> districts;

    public Map() {
        this.empty = true;  // Assuming a new map starts as empty
        this.hubs = new ArrayList<>();
        this.facilities = new ArrayList<>();
        this.districts = new ArrayList<>();
        this.centerCoordinates = new Coordinates(0, 0); // Default center
    }

    // Getters and Setters

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Coordinates getCenterCoordinates() {
        return centerCoordinates;
    }

    public void setCenterCoordinates(Coordinates centerCoordinates) {
        this.centerCoordinates = centerCoordinates;
    }

    // Methods

    public void create() {
        // Implementation for initializing the map, if necessary
        this.empty = true;
        this.hubs.clear();
        this.facilities.clear();
        this.districts.clear();
    }

    public void addHub(Hub hub) {
        this.hubs.add(hub);
        this.empty = false; // Map is no longer empty after adding a hub
        findCenter(); // Update center coordinates after adding a hub
    }

    public void addFacility(Facility facility) {
        this.facilities.add(facility);
        this.empty = false; // Map is no longer empty after adding a facility
        findCenter(); // Update center coordinates after adding a facility
    }

    public void addDistrict(District district) {
        this.districts.add(district);
        this.empty = false; // Map is no longer empty after adding a district
        findCenter(); // Update center coordinates after adding a district
    }

    private void findCenter() {
        if (hubs.isEmpty() && facilities.isEmpty() && districts.isEmpty()) {
            centerCoordinates = new Coordinates(0, 0); // Default center if nothing is added
            return;
        }

        double sumX = 0;
        double sumY = 0;
        int count = 0;

        // Calculate center from hubs
        for (Hub hub : hubs) {
            sumX += hub.getLocation().getX();
            sumY += hub.getLocation().getY();
            count++;
        }

        // Calculate center from facilities
        for (Facility facility : facilities) {
            sumX += facility.getLocation().getX();
            sumY += facility.getLocation().getY();
            count++;
        }

        // Calculate center from districts
        for (District district : districts) {
            Coordinates districtCenter = district.getCenterCoordinates();
            sumX += districtCenter.getX();
            sumY += districtCenter.getY();
            count++;
        }

        if (count > 0) {
            centerCoordinates = new Coordinates(sumX / count, sumY / count);
        }
    }
}
