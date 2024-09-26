package com.application.nodes.CalendarManagement;

import com.application.nodes.MapManagement.Coordinates;

import java.util.ArrayList;
import java.util.Date;

public class CalendarManager {
    private ArrayList<Item> currentCalendar; // List of current items (events/initiatives)
    private Item currentItem; // Currently selected item
    private CalendarEventReceiver eventReceiver; // Receiver for calendar events
    private Initiative currentInitiative; // Currently selected initiative

    // Constructor
    public CalendarManager(CalendarEventReceiver eventReceiver) {
        this.currentCalendar = new ArrayList<>();
        this.eventReceiver = eventReceiver;
    }

    // Method to ask for adding a new initiative
    public void askAddInitiative() {
        // Logic for asking to add an initiative
        // This could trigger a form or dialog in a UI context
    }

    // Method to specify details for an initiative
    public void specifyAnnouncementDetails(String name, Date start, Date end, Coordinates location, String description) {
        Initiative initiative = new Initiative();
        initiative.create(name, start, end, location, description);
        confirmInitiative(initiative);
    }

    // Confirm an initiative and notify
    public void confirmInitiative(Initiative initiative) {
        currentInitiative = initiative;
        //currentCalendar.add(initiative);
        notifyInitiativeAdded(initiative);
    }

    // Cancel an initiative and notify
    public void cancelInitiative(Initiative initiative) {
        initiative.setCanceled();
        notifyInitiativeCanceled(initiative);
    }

    // Set the current initiative
    public void setCurrentInitiative(Initiative newInitiative) {
        this.currentInitiative = newInitiative;
    }

    // Set the current form (if needed for UI purposes)
    public void setCurrentForm(Object currentInitiativeForm) {
        // Logic to set the current form (if applicable)
    }

    // Manage resources related to initiatives
    public void askManageResources() {
        // Logic to manage resources
    }

    // Choose an initiative item
    public void chooseInitiative(Item item) {
        setCurrentItem(item);
        // Further logic as required
    }

    // Cancel an initiative item
    public void cancelsInitiative(Item item) {
        item.setCanceled();
        notifyItemCanceled(item);
    }

    // Choose a booking item
    public void chooseBooking(Item item) {
        setCurrentItem(item);
        // Further logic as required
    }

    // Cancel a booking item
    public void cancelBooking(Item item) {
        item.setCanceled();
        notifyItemCanceled(item);
    }

    // Edit the calendar (logic specific to the application context)
    public void askEditCalendar() {
        // Logic for editing the calendar
    }

    // Approve an initiative
    public void approveInitiative(Item item) {
        notifyInitiativeApproved(currentInitiative);
    }

    // Disapprove an initiative
    public void disapproveInitiative(Item item) {
        notifyInitiativeDisapproved(currentInitiative);
    }

    // Notify when an initiative is added
    private void notifyInitiativeAdded(Initiative newInitiative) {
        eventReceiver.updateInitiativeAdded(this, newInitiative);
    }

    // Notify when an initiative is opened
    private void notifyInitiativeOpen(Initiative initiative) {
        eventReceiver.updateInitiativeOpen(this, initiative);
    }

    // Notify when an initiative is canceled
    private void notifyInitiativeCanceled(Initiative initiative) {
        eventReceiver.updateInitiativeCanceled(this, initiative);
    }

    // Notify when an item is canceled
    private void notifyItemCanceled(Item currentItem) {
        eventReceiver.updateItemCanceled(this, currentItem);
    }

    // Notify when an initiative is approved
    private void notifyInitiativeApproved(Initiative initiative) {
        eventReceiver.updateInitiativeApproved(this, initiative);
    }

    // Notify when an initiative is disapproved
    private void notifyInitiativeDisapproved(Initiative initiative) {
        eventReceiver.updateInitiativeDisapproved(this, initiative);
    }

    // Set the current calendar (list of items)
    public void setCurrentCalendar(ArrayList<Item> listOfItems) {
        this.currentCalendar = listOfItems;
    }

    // Set the current item
    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }
}
