package com.application.nodes.UserManagement;

public enum Role {
    AGENT,
    ADMIN,
    BUSINESS;

    // You can add additional methods if needed
    public String getRoleDescription() {
        switch (this) {
            case AGENT:
                return "Agent Role: Responsible for managing user interactions.";
            case ADMIN:
                return "Admin Role: Responsible for system administration.";
            case BUSINESS:
                return "Business Role: Responsible for business operations.";
            default:
                return "Unknown Role";
        }
    }
}
