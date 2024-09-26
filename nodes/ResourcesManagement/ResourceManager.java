package com.application.nodes.ResourcesManagement;

import com.application.nodes.CalendarManagement.Item;
import com.application.nodes.MapManagement.Facility;
import com.application.nodes.MapManagement.Hub;
import com.application.nodes.MapManagement.Map;

import java.util.Date;

public class ResourceManager {
    private BookingForm currentBookingForm; // Current booking form
    private ResourceEventReceiver eventReceiver; // Event receiver for resource events
    private Item currentItem; // Currently selected item
    private Hub currentHub; // Currently selected hub
    private Facility currentFacility; // Currently selected facility
    private Map currentMap; // Currently selected map

    // Constructor
    public ResourceManager(ResourceEventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver; // Initialize the event receiver
    }

    // Public Methods
    public void askBook() {
        System.out.println("Please provide booking details.");
        // Logic to prompt for booking details
    }

    public void selectBookingLocation(Hub hub, Facility facility) {
        this.currentHub = hub; // Set the current hub
        this.currentFacility = facility; // Set the current facility
        System.out.println("Selected booking location: Hub - " + hub.getName() + ", Facility - " + facility.getName());
    }

    public void chooseResource(Resource type, int quantity, Date start, Date end) {
        // Logic for choosing a resource
        System.out.println("Chosen resource: " + type.getName() + ", Quantity: " + quantity + ", Start: " + start + ", End: " + end);
        currentItem = new Item(); // Assuming Item is not created based on the resource type
        notifyBookingConfirmed(currentItem); // Notify booking confirmation
    }

    public void choosesResource(Resource type, int quantity, Date start, Date end) {
        // Assuming similar to chooseResource but maybe with different logic
        chooseResource(type, quantity, start, end);
    }

    public void selectTheBookingLocation(Hub hub, Facility facility) {
        selectBookingLocation(hub, facility);
    }

    public void chooseOneResource(Resource resource) {
        // Logic to choose one specific resource
        System.out.println("Chosen one resource: " + resource.getName());
        currentItem = new Item(); // Create an item not based on the resource
        notifyBookingConfirmed(currentItem); // Notify booking confirmation
    }

    public void sendCardInfo(String number, int code, String owner) {
        // Logic to handle credit card information
        System.out.println("Card information received for owner: " + owner);
        // Process card information for booking
    }

    // Private Method for Notification
    private void notifyBookingConfirmed(Item currentItem) {
        if (eventReceiver != null) {
            eventReceiver.updateBookingConfirmed(this, currentItem); // Notify the event receiver
            System.out.println("Booking confirmed for item: " + currentItem.getName());
        }
    }

    // Getters and Setters
    public BookingForm getCurrentBookingForm() {
        return currentBookingForm;
    }

    public void setCurrentBookingForm(BookingForm currentBookingForm) {
        this.currentBookingForm = currentBookingForm;
    }

    public ResourceEventReceiver getEventReceiver() {
        return eventReceiver;
    }

    public void setEventReceiver(ResourceEventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public Hub getCurrentHub() {
        return currentHub;
    }

    public Facility getCurrentFacility() {
        return currentFacility;
    }

    public Map getCurrentMap() {
        return currentMap;
    }
}
