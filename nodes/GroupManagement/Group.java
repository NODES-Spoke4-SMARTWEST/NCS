package com.application.nodes.GroupManagement;

import com.application.nodes.UserManagement.User;

import java.util.ArrayList;

public class Group {
    private int id; // Group ID
    private String name; // Name of the group
    private String purpose; // Purpose of the group
    private User creator; // Creator of the group
    private ArrayList<User> listOfParticipants; // List of participants in the group
    private ArrayList<Message> chat; // List of messages in the group's chat

    // Constructor
    public Group(String name, String purpose) {
        this.name = name;
        this.purpose = purpose;
        this.listOfParticipants = new ArrayList<>();
        this.chat = new ArrayList<>();
    }

    // Create a group with the specified creator
    public void create(User creator) {
        this.creator = creator; // Set the creator of the group
        this.id = generateGroupId(); // Assume a method to generate a unique group ID
        this.listOfParticipants.add(creator); // Add the creator as the first participant
    }

    // Add a message to the chat
    public void addMessage(User sender, String text) {
        Message message = new Message(sender, text); // Create a new message
        chat.add(message); // Add the message to the chat
    }

    // Add a participant to the group
    public void addParticipant(User user) {
        if (!listOfParticipants.contains(user)) {
            listOfParticipants.add(user); // Add the user if not already a participant
        }
    }

    // Remove a participant from the group
    public void removeParticipant(User user) {
        listOfParticipants.remove(user); // Remove the user from participants
    }

    // Edit group details
    public void editGroup(String name, String description) {
        this.name = name; // Set the new name
        this.purpose = description; // Set the new description (purpose)
    }

    // Generate a unique group ID (simple incrementer for example purposes)
    private int generateGroupId() {
        // Logic for generating a unique group ID (e.g., using a static variable)
        // This is just a placeholder; you would implement this based on your application's needs.
        return (int) (Math.random() * 10000); // Placeholder for generating unique ID
    }

    // Getters
    public int getId() {
        return id; // Return the group ID
    }

    public String getName() {
        return name; // Return the group name
    }

    public String getPurpose() {
        return purpose; // Return the group's purpose
    }

    public User getCreator() {
        return creator; // Return the creator of the group
    }

    public ArrayList<User> getListOfParticipants() {
        return listOfParticipants; // Return the list of participants
    }

    public ArrayList<Message> getChat() {
        return chat; // Return the chat messages
    }
}
