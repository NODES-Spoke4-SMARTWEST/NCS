package com.application.nodes.UserManagement;

public class UserManager {
    private Notification currentNotification; // The current notification for the user
    private User currentUser; // The current user managed by this manager
    private UserEventReciver eventReceiver; // Receiver for user events

    // Constructor
    public UserManager(UserEventReciver eventReceiver) {
        this.eventReceiver = eventReceiver; // Initialize the event receiver
    }

    // Public Methods
    public User getCurrentUser() {
        return currentUser; // Return the current user
    }

    public void addNotification(User sender, String text) {
        // Create a new notification
        Notification notification = new Notification(sender);
        notification.create(text); // Set the notification text

        // Set the current notification
        this.currentNotification = notification;

        // Notify the event receiver that a new notification has been added
        notifyNotificationAdded(notification);
    }

    // Private method to notify event receiver about the added notification
    private void notifyNotificationAdded(Notification newN) {
        if (eventReceiver != null) {
            eventReceiver.updateNotificationAdded(this, newN); // Notify the receiver
        }
    }

    // Setters and Getters
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser; // Set the current user
    }
}
