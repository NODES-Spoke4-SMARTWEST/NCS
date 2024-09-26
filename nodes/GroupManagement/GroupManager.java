package com.application.nodes.GroupManagement;

import com.application.nodes.UserManagement.User;

import java.util.ArrayList;

public class GroupManager {
    private Group currentGroup; // The currently managed group
    private ArrayList<GroupEventReciver> eventRecivers; // List of event receivers for group events

    // Constructor
    public GroupManager() {
        this.eventRecivers = new ArrayList<>(); // Initialize the event receiver list
    }

    // Add a new group with the specified user as the creator
    public void addGroup(User myUser, User user) {
        Group newGroup = new Group("New Group", "Description"); // Create a new group
        newGroup.create(myUser); // Set the creator of the group
        currentGroup = newGroup; // Set as the current group
        notifyGroupAdded(newGroup); // Notify listeners about the new group
    }

    // Request to add a group (this could trigger UI actions)
    public void askToAddGroup() {
        // Implementation for asking to add a group (e.g., prompting the user)
        System.out.println("Requesting to add a new group...");
    }

    // Request to add an agent (this could trigger UI actions)
    public void askToAddAgent() {
        // Implementation for asking to add an agent
        System.out.println("Requesting to add an agent...");
    }

    // Select an agent by name (could involve searching a list of agents)
    public void selectAgentName(String name) {
        // Implementation for selecting an agent by name
        System.out.println("Selecting agent: " + name);
    }

    // Request to edit the current group (this could trigger UI actions)
    public void askToEditGroup() {
        // Implementation for asking to edit the group
        System.out.println("Requesting to edit the current group...");
    }

    // Edit the current group's name and description
    public void editGroup(String name, String description) {
        if (currentGroup != null) {
            currentGroup.editGroup(name, description); // Edit the group's details
            notifyGroupEdited(currentGroup); // Notify listeners about the group edit
        }
    }

    // Notify listeners that a new group has been added
    private void notifyGroupAdded(Group newGroup) {
        for (GroupEventReciver reciver : eventRecivers) {
            reciver.updateGroupAdded(this, newGroup); // Call the method on each event receiver
        }
    }

    // Notify listeners that a participant has been added to the current group
    private void notifyParticipantAdded(User user) {
        if (currentGroup != null) {
            for (GroupEventReciver reciver : eventRecivers) {
                reciver.updateParticipantAdded(this, currentGroup, user); // Notify about participant addition
            }
        }
    }

    // Notify listeners that the current group has been edited
    private void notifyGroupEdited(Group currentGroup) {
        for (GroupEventReciver reciver : eventRecivers) {
            reciver.updateGroupEdited(this, currentGroup); // Notify about group edit
        }
    }

    // Getters and Setters
    public Group getCurrentGroup() {
        return currentGroup; // Return the current group
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup = currentGroup; // Set the current group
    }

    public void addEventReciver(GroupEventReciver reciver) {
        eventRecivers.add(reciver); // Add an event receiver to the list
    }
}
