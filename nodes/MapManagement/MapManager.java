package com.application.nodes.MapManagement;

import com.application.nodes.UserManagement.UserManager;

import java.util.ArrayList;
import java.util.List;


public class MapManager {
    private Facility currentFacility;
    private UserManager userMgr; // Instance of UserManager to manage user notifications
    private List<MapEventReceiver> eventReceivers; // List of event receivers

    public MapManager(UserManager userMgr) {
        this.eventReceivers = new ArrayList<>(); // Initialize the event receivers list
        this.userMgr = userMgr; // Initialize the UserManager
    }

    // Getters and Setters
    public Facility getCurrentFacility() {
        return currentFacility;
    }

    private void setCurrentFacility(Facility newFacility) {
        this.currentFacility = newFacility;
    }

    // Method to register an event receiver
    public void addEventReceiver(MapEventReceiver receiver) {
        eventReceivers.add(receiver);
    }

    // Method to remove an event receiver
    public void removeEventReceiver(MapEventReceiver receiver) {
        eventReceivers.remove(receiver);
    }

    // Public Methods
    public void askOfferFacility() {
        // Logic to prompt the user to offer a facility
        System.out.println("Please provide the facility details to offer.");
    }

    public void specifyFacilityDetails(String name, String type, Coordinates location, String description) {
        Facility newFacility = new Facility(); // Assuming a method to create a new facility
        newFacility.create(name, type, location, description); // Assuming the create method is defined in Facility
        setCurrentFacility(newFacility);
        notifyFacilityAdded(newFacility);
    }

    public void confirmFacility(Facility facility) {
        // Logic to confirm the facility
        setCurrentFacility(facility);
        notifyFacilityOpen(facility);
        System.out.println("Facility confirmed: " + facility.getName());
    }

    public void cancelFacility(Facility facility) {
        // Logic to cancel the facility
        if (facility.equals(currentFacility)) {
            setCurrentFacility(null); // Clear current facility if it was canceled
        }
        notifyFacilityCanceled(facility);
        System.out.println("Facility canceled: " + facility.getName());
    }

    // Notification Methods
    private void notifyFacilityAdded(Facility newFacility) {
        for (MapEventReceiver receiver : eventReceivers) {
            receiver.updateFacilityAdded(this, newFacility);
        }
    }

    private void notifyFacilityOpen(Facility facility) {
        for (MapEventReceiver receiver : eventReceivers) {
            receiver.updateFacilityOpen(this, facility);
        }
    }

    private void notifyFacilityCanceled(Facility facility) {
        for (MapEventReceiver receiver : eventReceivers) {
            receiver.updateFacilityCanceled(this, facility);
        }
    }
}