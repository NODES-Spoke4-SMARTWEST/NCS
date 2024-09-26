package com.application.nodes.CalendarManagement;

import com.application.nodes.MapManagement.Coordinates;

import java.util.Date;

public class Initiative {
    private int id;                     // Unique identifier for the initiative
    private String name;                // Name of the initiative
    private Date start;                 // Start date of the initiative
    private Date end;                   // End date of the initiative
    private Coordinates location;        // Location coordinates of the initiative
    private String description;          // Description of the initiative
    private boolean canceled;            // Status indicating if the initiative is canceled

    // Constructor to initialize the Initiative
    public Initiative() {
        this.canceled = false;           // Initialize canceled status to false
    }

    // Method to create a new initiative
    public void create(String name, Date start, Date end, Coordinates location, String description) {
        this.name = name;                // Set the name
        this.start = start;              // Set the start date
        this.end = end;                  // Set the end date
        this.location = location;        // Set the location
        this.description = description;   // Set the description
        this.canceled = false;           // Ensure it starts as not canceled
        System.out.println("Initiative created: " + this.name);
    }

    // Method to mark the initiative as canceled
    public void setCanceled() {
        this.canceled = true;            // Set the canceled status to true
        System.out.println("Initiative canceled: " + this.name);
    }

    // Getters and Setters (optional, if you need to access or modify private attributes)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Coordinates getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
