package com.application.nodes.ResourcesManagement;

import java.util.ArrayList;

public class BookingForm {
    private ArrayList<Resource> resources; // List to hold resources

    // Constructor
    public BookingForm() {
        this.resources = new ArrayList<>(); // Initialize the resources list
    }

    // Public Methods
    public void create() {
        // Logic to create a new booking form
        System.out.println("Booking form created.");
    }

    public void addResource(Resource resource) {
        if (resource != null) {
            resources.add(resource); // Add resource to the list
            System.out.println("Resource added: " + resource.getName());
        } else {
            System.out.println("Cannot add null resource.");
        }
    }

    public void removeResource(Resource resource) {
        if (resources.remove(resource)) {
            System.out.println("Resource removed: " + resource.getName());
        } else {
            System.out.println("Resource not found: " + resource.getName());
        }
    }

    // Getter for resources
    public ArrayList<Resource> getResources() {
        return resources;
    }

    @Override
    public String toString() {
        return "BookingForm{" +
                "resources=" + resources +
                '}';
    }
}
