package com.application.nodes.ResourcesManagement;

import com.application.nodes.CalendarManagement.Item;

public interface ResourceEventReceiver {
    void updateBookingConfirmed(ResourceManager resMgr, Item currentItem);
}
