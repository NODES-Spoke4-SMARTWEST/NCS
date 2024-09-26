package com.application.nodes.BullettinBoardManagement;

import com.application.nodes.MapManagement.Coordinates;
import com.application.nodes.UserManagement.Competence;
import com.application.nodes.UserManagement.Interest;
import com.application.nodes.UserManagement.User;

import java.util.ArrayList;
import java.util.Date;

public class Announcement {
    private int id; // Unique identifier for the announcement
    private String name; // Name of the announcement
    private Date date; // Date of the announcement
    private Coordinates location; // Location associated with the announcement
    private String description; // Description of the announcement
    private boolean open; // Indicates if the announcement is open
    private boolean canceled; // Indicates if the announcement is canceled
    private ArrayList<Competence> competences; // List of competences
    private ArrayList<Interest> interests; // List of interests
    private User owner; // Owner of the announcement

    // Constructor
    public Announcement() {
        this.competences = new ArrayList<>(); // Initialize the list of competences
        this.interests = new ArrayList<>(); // Initialize the list of interests
        this.open = false; // Announcement is initially closed
        this.canceled = false; // Announcement is initially not canceled
    }

    // Method to create a new announcement
    public void create(String name, Date date, Coordinates location, ArrayList<Competence> competences,
                       ArrayList<Interest> interests, String description) {
        this.name = name; // Set the name
        this.date = date; // Set the date
        this.location = location; // Set the location
        this.competences = competences; // Set the competences
        this.interests = interests; // Set the interests
        this.description = description; // Set the description
        this.open = true; // Announcement is now open
        this.canceled = false; // Ensure it is not canceled
        System.out.println("Announcement created: " + this.name);
    }

    // Method to set the announcement to open
    public void setOpen() {
        this.open = true; // Mark the announcement as open
        System.out.println("Announcement is now open: " + this.name);
    }

    // Method to set the announcement to canceled
    public void setCanceled() {
        this.canceled = true; // Mark the announcement as canceled
        this.open = false; // Ensure it is not open if canceled
        System.out.println("Announcement is now canceled: " + this.name);
    }

    // Getters for the attributes
    public int getId() {
        return id; // Return the ID
    }

    public String getName() {
        return name; // Return the name
    }

    public Date getDate() {
        return date; // Return the date
    }

    public Coordinates getLocation() {
        return location; // Return the location
    }

    public String getDescription() {
        return description; // Return the description
    }

    public boolean isOpen() {
        return open; // Return if the announcement is open
    }

    public boolean isCanceled() {
        return canceled; // Return if the announcement is canceled
    }

    public ArrayList<Competence> getCompetences() {
        return competences; // Return the list of competences
    }

    public ArrayList<Interest> getInterests() {
        return interests; // Return the list of interests
    }

    public User getOwner() {
        return owner; // Return the owner of the announcement
    }

    public void setOwner(User owner) {
        this.owner = owner; // Set the owner of the announcement
    }

    public void setId(int id) {
        this.id = id; // Set the ID of the announcement
    }
}
