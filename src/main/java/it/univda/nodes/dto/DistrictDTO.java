package it.univda.nodes.dto;

import it.univda.nodes.entity.Competence;
import it.univda.nodes.entity.Interest;

import java.util.List;

public class DistrictDTO {
    private String name;
    private String color; // Color for the district polygon on the map
    private List<HubDTO> hubs;
    private double minimumRadius; // Minimum radius for the district

    private List<Competence> competences;

    private List<Interest> interests;

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

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
}
