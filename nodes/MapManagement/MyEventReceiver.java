package com.application.nodes.MapManagement;

public class MyEventReceiver implements MapEventReceiver {
    @Override
    public void updateFacilityAdded(MapManager mapManager, Facility newFacility) {
        System.out.println("Received notification: Facility added - " + newFacility.getName());
    }

    @Override
    public void updateFacilityOpen(MapManager mapManager, Facility facility) {
        System.out.println("Received notification: Facility opened - " + facility.getName());
    }

    @Override
    public void updateFacilityCanceled(MapManager mapManager, Facility facility) {
        System.out.println("Received notification: Facility canceled - " + facility.getName());
    }
}


/*
    // Example usage
    MapManager mapManager = new MapManager();
    MyEventReceiver eventReceiver = new MyEventReceiver();
mapManager.addEventReceiver(eventReceiver);

// Now, you can specify facility details and see notifications
        mapManager.specifyFacilityDetails("Community Center", "Public", new Coordinates(12.34, 56.78), "A place for community activities.");
        mapManager.confirmFacility(mapManager.getCurrentFacility());
        mapManager.cancelFacility(mapManager.getCurrentFacility());

*/