package com.example.nodes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "initiative")
public class Initiative {

    @Id
    @Column(name="id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Hub location;

    @Column(name="subject", nullable = false)
    private String subject;

    @Column(name="approved", nullable = false)
    private boolean approved;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hub getLocation() {
        return location;
    }

    public void setLocation(Hub location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

