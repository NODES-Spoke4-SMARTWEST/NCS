package com.application.nodes.MapManagement;

public class Facility {
    private int id;
    private Coordinates location;
    private String name;
    private String type;
    private String description;
    private boolean facility;

    public Facility() {
        // Default constructor
    }

    public Facility(String name, String type, Coordinates location, String description) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
        this.facility = true;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFacility() {
        return facility;
    }

    public void setFacility(boolean facility) {
        this.facility = facility;
    }

    // Methods

    public static Facility create(String name, String type, Coordinates location, String description) {
        return new Facility(name, type, location, description);
    }
}
