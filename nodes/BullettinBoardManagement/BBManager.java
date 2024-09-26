package com.application.nodes.BullettinBoardManagement;

import com.application.nodes.MapManagement.Coordinates;
import com.application.nodes.UserManagement.Competence;
import com.application.nodes.UserManagement.Interest;
import com.application.nodes.UserManagement.UserManager;

import java.util.ArrayList;
import java.util.Date;

public class BBManager {
    private UserManager userMgr; // Reference to the UserManager
    private Announcement currentAnnouncement; // Currently selected announcement
    private ArrayList<BBEventReciver> eventReceivers; // List of event receivers

    // Constructor
    public BBManager() {
        this.eventReceivers = new ArrayList<>(); // Initialize the list of event receivers
    }

    // Method to ask for adding a new announcement
    public void askAddAnnouncement() {
        // Logic for asking to add an announcement
        System.out.println("Request to add a new announcement.");
    }

    // Method to specify details for a new announcement
    public void specifyAnnouncementDetails(String name, Date date, Coordinates location,
                                           ArrayList<Competence> competences,
                                           ArrayList<Interest> interests, String description) {
        // Create a new announcement
        Announcement newAnnouncement = new Announcement();
        newAnnouncement.create(name, date, location, competences, interests, description);
        confirmAnnouncement(newAnnouncement); // Confirm the new announcement
    }

    // Method to confirm the addition of an announcement
    public void confirmAnnouncement(Announcement announcement) {
        this.currentAnnouncement = announcement; // Set the current announcement
        notifyAnnouncementAdded(announcement); // Notify event receivers
        System.out.println("Announcement confirmed: " + announcement.getName());
    }

    // Method to cancel an existing announcement
    public void cancelAnnouncement(Announcement announcement) {
        if (announcement != null) {
            announcement.setCanceled(); // Mark the announcement as canceled
            notifyAnnouncementCanceled(announcement); // Notify event receivers
            System.out.println("Announcement canceled: " + announcement.getName());
        }
    }

    // Method to notify when a new announcement is added
    public void notifyAnnouncementAdded(Announcement newA) {
        for (BBEventReciver receiver : eventReceivers) {
            receiver.updateAnnouncementAdded(this, newA); // Notify each receiver
        }
    }

    // Method to notify when an announcement is opened
    public void notifyAnnouncementOpen(Announcement announcement) {
        for (BBEventReciver receiver : eventReceivers) {
            receiver.updateAnnouncementOpen(this, announcement); // Notify each receiver
        }
    }

    // Method to notify when an announcement is canceled
    public void notifyAnnouncementCanceled(Announcement announcement) {
        for (BBEventReciver receiver : eventReceivers) {
            receiver.updateAnnouncementCanceled(this, announcement); // Notify each receiver
        }
    }

    // Setter for the current announcement
    public void setCurrentAnnouncement(Announcement newA) {
        this.currentAnnouncement = newA; // Set the new current announcement
    }

    // Setter for the current form (assumed to be a type of announcement form)
    public void setCurrentForm(Object currentAForm) {
        // Logic to set the current announcement form
        System.out.println("Current form set.");
    }

    // Getter for the current announcement
    public Announcement getCurrentAnnouncement() {
        return currentAnnouncement; // Return the current announcement
    }

    // Method to add a new event receiver
    public void addEventReceiver(BBEventReciver receiver) {
        eventReceivers.add(receiver); // Add the receiver to the list
    }
}
