package com.application.nodes.MapManagement;

public interface MapEventReceiver {
    void updateFacilityAdded(MapManager mapManager, Facility newFacility);
    void updateFacilityOpen(MapManager mapManager, Facility facility);
    void updateFacilityCanceled(MapManager mapManager, Facility facility);
}

