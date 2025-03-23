package com.example.nodes.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_test")
@Convert(attributeName = "point", converter = Point.class)
public class Booking {

    @Id
    @Column(name="id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource")
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiative_id")
    private Initiative initiative;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "canceled", nullable = false)
    private boolean canceled = false;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(LocalDateTime  startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime  getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDateTime  endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime  getEndDate() {
        return endDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}