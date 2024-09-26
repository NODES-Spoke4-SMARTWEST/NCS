package com.application.nodes.BullettinBoardManagement;

import com.application.nodes.UserManagement.Competence;
import com.application.nodes.UserManagement.Interest;

import java.util.ArrayList;

public class AnnouncementForm {
    private ArrayList<Competence> competences; // List of competences
    private ArrayList<Interest> interests; // List of interests

    // Constructor
    public AnnouncementForm() {
        this.competences = new ArrayList<>(); // Initialize the list of competences
        this.interests = new ArrayList<>(); // Initialize the list of interests
    }

    // Create a new announcement form (potentially initializes fields or other setup)
    public void create() {
        // Logic to create or reset the announcement form
        competences.clear(); // Clear any existing competences
        interests.clear(); // Clear any existing interests
        System.out.println("Announcement form created.");
    }

    // Add a competence to the list
    public void addCompetence(Competence competence) {
        if (!competences.contains(competence)) {
            competences.add(competence); // Add the competence if it's not already present
            System.out.println("Competence added: " + competence);
        } else {
            System.out.println("Competence already exists: " + competence);
        }
    }

    // Remove a competence from the list
    public void removeCompetence(Competence competence) {
        if (competences.remove(competence)) {
            System.out.println("Competence removed: " + competence);
        } else {
            System.out.println("Competence not found: " + competence);
        }
    }

    // Add an interest to the list
    public void addInterest(Interest interest) {
        if (!interests.contains(interest)) {
            interests.add(interest); // Add the interest if it's not already present
            System.out.println("Interest added: " + interest);
        } else {
            System.out.println("Interest already exists: " + interest);
        }
    }

    // Remove an interest from the list
    public void removeInterest(Interest interest) {
        if (interests.remove(interest)) {
            System.out.println("Interest removed: " + interest);
        } else {
            System.out.println("Interest not found: " + interest);
        }
    }

    // Getters for competences and interests
    public ArrayList<Competence> getCompetences() {
        return competences; // Return the list of competences
    }

    public ArrayList<Interest> getInterests() {
        return interests; // Return the list of interests
    }
}
