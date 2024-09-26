package com.application.nodes.MapManagement;

public class Hub {
    private int id;
    private Coordinates location;
    private String name;
    private boolean shelter;
    private String security;
    private String informationOrGuidelines;

    public Hub() {
        // Default constructor
    }

    public Hub(String name, Coordinates location, boolean shelter) {
        this.name = name;
        this.location = location;
        this.shelter = shelter;
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

    public boolean isShelter() {
        return shelter;
    }

    public void setShelter(boolean shelter) {
        this.shelter = shelter;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getInformationOrGuidelines() {
        return informationOrGuidelines;
    }

    public void setInformationOrGuidelines(String informationOrGuidelines) {
        this.informationOrGuidelines = informationOrGuidelines;
    }

    // Methods

    public static Hub create(String name, Coordinates location, boolean shelter) {
        return new Hub(name, location, shelter);
    }

    public void editSecurity(String text) {
        this.security = text;
    }

    public void editInfo(String text) {
        this.informationOrGuidelines = text;
    }
}
