package com.application.nodes.UserManagement;

import java.util.Date;

public class Notification {
    private User sender; // User who sent the notification
    private boolean open; // Status of the notification (open or closed)
    private Date date; // Date of the notification
    private String text; // Notification message text

    // Constructor
    public Notification(User sender) {
        this.sender = sender; // Initialize the sender
        this.open = false; // Default to not opened
        this.date = new Date(); // Set the date to the current date and time
        this.text = ""; // Initialize text as empty
    }

    // Public Methods
    public void create(String text) {
        this.text = text; // Set the notification text
        this.open = true; // Mark as open when created
    }

    // Method to mark the notification as opened
    public void markAsRead() {
        this.open = false; // Mark the notification as read (closed)
    }

    // Getters
    public User getSender() {
        return sender;
    }

    public boolean isOpen() {
        return open;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "sender=" + sender.getName() + // Assuming User has a getName() method
                ", open=" + open +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
