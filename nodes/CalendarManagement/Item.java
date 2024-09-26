package com.application.nodes.CalendarManagement;

import com.application.nodes.ResourcesManagement.Resource;

import java.util.Date;

public class Item {
    private Date start;                // Start date of the booking
    private Date end;                  // End date of the booking
    private int quantity;              // Quantity of resources booked
    private boolean booked;            // Status indicating if the item is booked
    private boolean canceled;          // Status indicating if the item is canceled
    private Resource resource;         // Associated resource for the booking
    private Initiative initiative;     // Associated initiative for the booking

    // Constructor to initialize the Item
    public Item() {
        this.booked = false;           // Initialize booked status to false
        this.canceled = false;         // Initialize canceled status to false
    }

    // Method to create a new item with quantity, start and end dates
    public void create(int quantity, Date start, Date end) {
        this.quantity = quantity;       // Set the quantity
        this.start = start;             // Set the start date
        this.end = end;                 // Set the end date
        this.booked = false;            // Ensure it starts as not booked
        this.canceled = false;          // Ensure it starts as not canceled
        System.out.println("Item created with quantity: " + this.quantity);
    }

    // Method to set the associated resource for the booking
    public void setBooking(Resource resource) {
        this.resource = resource;       // Set the resource
        this.booked = true;             // Mark the item as booked
        System.out.println("Item booked with resource: " + resource.getName());
    }

    // Method to get the associated initiative
    public Initiative getInitiative() {
        return initiative;              // Return the associated initiative
    }

    // Method to set the associated initiative for the booking
    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;   // Set the initiative
    }

    // Method to get the associated resource
    public Resource getBooking() {
        return resource;                // Return the associated resource
    }

    // Method to mark the item as canceled
    public void setCanceled() {
        this.canceled = true;           // Set the canceled status to true
        this.booked = false;            // Optionally mark it as not booked
        System.out.println("Item canceled.");
    }

    // Getters for the Item attributes (optional)
    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public String getName() { //do not delete. useful for resourcemanager
        return "item placeholder";
    }
}
