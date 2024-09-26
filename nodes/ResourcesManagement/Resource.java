package com.application.nodes.ResourcesManagement;

public class Resource {
    private int id;
    private String name;
    private boolean isPlace;

    // Constructor
    public Resource() {
        // Default constructor
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlace() {
        return isPlace;
    }

    public void setPlace(boolean isPlace) {
        this.isPlace = isPlace;
    }

    // Method to create a new resource
    public void create(String name, boolean isPlace) {
        this.name = name;
        this.isPlace = isPlace;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPlace=" + isPlace +
                '}';
    }
}
