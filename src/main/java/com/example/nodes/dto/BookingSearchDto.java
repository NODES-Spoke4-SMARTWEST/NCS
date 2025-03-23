package com.example.nodes.dto;

import java.util.List;

public class BookingSearchDto {
    private boolean locationChecked;
    private Long locationId;
    private boolean typeChecked;
    private List<Long> resourceIds;

    // Getters and Setters

    public void setLocationChecked(boolean locationChecked) {
        this.locationChecked = locationChecked;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<Long> getResourceIds() {
        return resourceIds;
    }

    public void setTypeChecked(boolean typeChecked) {
        this.typeChecked = typeChecked;
    }

    public boolean isLocationChecked() {
        return locationChecked;
    }
}
