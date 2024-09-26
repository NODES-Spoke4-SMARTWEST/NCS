package com.application.nodes.UserManagement;

import com.application.nodes.GroupManagement.Group;
import com.application.nodes.MapManagement.Coordinates;

import java.util.ArrayList;

public class User {
    private int id; // User's unique identifier
    private Role type; // User's role (assuming Role is an enum or class)
    private String name; // User's name
    private String password; // User's password
    private boolean active; // User's active status
    private boolean banned; // User's banned status
    private boolean availability; // User's availability status
    private Coordinates location; // User's location
    private String description; // User's description
    private ArrayList<Competence> competences; // List of user's competences
    private ArrayList<Interest> interests; // List of user's interests
    private ArrayList<Group> listOfGroups; // List of groups the user belongs to

    // Constructor
    public User() {
        this.competences = new ArrayList<>(); // Initialize the competences list
        this.interests = new ArrayList<>(); // Initialize the interests list
    }

    // Public Methods
    public void create(int id, Role type, String name, String password, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.password = password;
        this.description = description;
        this.active = true; // Default to active
        this.banned = false; // Default to not banned
        this.availability = true; // Default to available
    }

    public void addCompetence(Competence competence) {
        if (competence != null) {
            competences.add(competence); // Add competence to the list
            System.out.println("Competence added: " + competence);
        } else {
            System.out.println("Cannot add null competence.");
        }
    }

    public void removeCompetence(Competence competence) {
        if (competences.remove(competence)) {
            System.out.println("Competence removed: " + competence);
        } else {
            System.out.println("Competence not found: " + competence);
        }
    }

    public void addInterest(Interest interest) {
        if (interest != null) {
            interests.add(interest); // Add interest to the list
            System.out.println("Interest added: " + interest);
        } else {
            System.out.println("Cannot add null interest.");
        }
    }

    public void removeInterest(Interest interest) {
        if (interests.remove(interest)) {
            System.out.println("Interest removed: " + interest);
        } else {
            System.out.println("Interest not found: " + interest);
        }
    }

    public void addLocation(Coordinates location) {
        this.location = location; // Set the user's location
        System.out.println("Location added: " + location);
    }

    public void removeLocation() {
        this.location = null; // Remove the user's location
        System.out.println("Location removed.");
    }

    public void editParameters(Boolean active, Boolean banned, Boolean availability) {
        if (active != null) {
            this.active = active; // Update active status if provided
        }
        if (banned != null) {
            this.banned = banned; // Update banned status if provided
        }
        if (availability != null) {
            this.availability = availability; // Update availability status if provided
        }
        System.out.println("User parameters updated: Active=" + this.active + ", Banned=" + this.banned + ", Availability=" + this.availability);
    }

    public void joinGroup(Group group) {
        if (!listOfGroups.contains(group)) {
            listOfGroups.add(group);
        }
    }

    public void leaveGroup(Group group) {
        listOfGroups.remove(group);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public Role getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isBanned() {
        return banned;
    }

    public boolean isAvailability() {
        return availability;
    }

    public Coordinates getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Competence> getCompetences() {
        return competences;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public ArrayList<Group> getListOfGroups() {
        return listOfGroups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", banned=" + banned +
                ", availability=" + availability +
                ", location=" + location +
                ", description='" + description + '\'' +
                ", competences=" + competences +
                ", interests=" + interests +
                '}';
    }
}
