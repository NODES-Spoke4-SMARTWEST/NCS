package com.application.nodes.MapManagement;

import com.application.nodes.UserManagement.Competence;
import com.application.nodes.UserManagement.Interest;

import java.util.ArrayList;

public class District {
    private int id;
    private Area area;
    private String name;
    private ArrayList<Competence> competences;
    private ArrayList<Interest> interests;
    private ArrayList<Hub> hubs;

    public District() {
        this.competences = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.hubs = new ArrayList<>();
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Competence> getCompetences() {
        return competences;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public ArrayList<Hub> getHubs() {
        return hubs;
    }

    // Methods

    public void create() {
        // Implementation for creating the district
    }

    public void addCompetence(Competence competence) {
        this.competences.add(competence);
    }

    public void removeCompetence(Competence competence) {
        this.competences.remove(competence);
    }

    public void addInterest(Interest interest) {
        this.interests.add(interest);
    }

    public void removeInterest(Interest interest) {
        this.interests.remove(interest);
    }

    public void addHub(Hub hub) {
        this.hubs.add(hub);
    }

    public void removeHub(Hub hub) {
        this.hubs.remove(hub);
    }

    public void editArea(Area area) {
        this.area = area;
    }

    public Coordinates getCenterCoordinates() {
        return area.getCenterCoordinates(); // Adjust this based on your Area implementation
    }
}
