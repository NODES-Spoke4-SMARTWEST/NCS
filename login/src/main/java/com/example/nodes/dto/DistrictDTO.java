package com.example.nodes.dto;

import java.util.List;

public class DistrictDTO {
    private String name;
    private String color; // Color for the district polygon on the map
    private List<HubDTO> hubs;
    private double minimumRadius; // Minimum radius for the district

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<HubDTO> getHubs() {
        return hubs;
    }

    public void setHubs(List<HubDTO> hubs) {
        this.hubs = hubs;
    }

    public double getMinimumRadius() {
        return minimumRadius;
    }

    public void setMinimumRadius(double minimumRadius) {
        this.minimumRadius = minimumRadius;
    }
}
