package com.application.nodes.MapManagement;

import java.util.ArrayList;

public class Area {
    private ArrayList<Coordinates> coordinates; // List of points defining the area

    public Area() {
        this.coordinates = new ArrayList<>();
    }

    // Getters and Setters

    public ArrayList<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    // Methods

    public void addCoordinate(Coordinates coordinate) {
        this.coordinates.add(coordinate);
    }

    public void removeCoordinate(Coordinates coordinate) {
        this.coordinates.remove(coordinate);
    }

    public Coordinates getCenterCoordinates() {
        if (coordinates.isEmpty()) {
            return new Coordinates(0, 0); // Default if no coordinates
        }

        double sumX = 0;
        double sumY = 0;

        // Sum up all the coordinates
        for (Coordinates coord : coordinates) {
            sumX += coord.getX();
            sumY += coord.getY();
        }

        // Calculate the average for the center coordinates
        return new Coordinates(sumX / coordinates.size(), sumY / coordinates.size());
    }
}
