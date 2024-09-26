package com.application.nodes.GroupManagement;

import com.application.nodes.UserManagement.User;

public class Message {
    private User writer; // The user who wrote the message
    private int id; // Unique identifier for the message
    private String text; // Content of the message

    // Constructor
    public Message(User writer, String text) {
        this.writer = writer;
        this.text = text;
        this.id = generateMessageId(); // Generate a unique message ID upon creation
    }

    // Generate a unique message ID (simple incrementer for example purposes)
    private int generateMessageId() {
        // Logic for generating a unique message ID (e.g., using a static variable)
        // This is just a placeholder; you would implement this based on your application's needs.
        return (int) (Math.random() * 10000); // Placeholder for generating unique ID
    }

    // Getters
    public User getWriter() {
        return writer; // Return the user who wrote the message
    }

    public int getId() {
        return id; // Return the message ID
    }

    public String getText() {
        return text; // Return the message text
    }
}
