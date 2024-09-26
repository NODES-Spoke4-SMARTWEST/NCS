package com.application.nodes.CalendarManagement;

public interface CalendarEventReceiver {
    // Method called when a new initiative is added
    void updateInitiativeAdded(CalendarManager calendarMgr, Initiative newInitiative);

    // Method called when an initiative is marked as open
    void updateInitiativeOpen(CalendarManager calendarMgr, Initiative initiative);

    // Method called when an initiative is canceled
    void updateInitiativeCanceled(CalendarManager calendarMgr, Initiative initiative);

    // Method called when an initiative is approved
    void updateInitiativeApproved(CalendarManager calendarMgr, Initiative initiative);

    // Method called when an initiative is disapproved
    void updateInitiativeDisapproved(CalendarManager calendarMgr, Initiative initiative);

    // Method called when an item is canceled
    void updateItemCanceled(CalendarManager calendarMgr, Item currentItem);
}
