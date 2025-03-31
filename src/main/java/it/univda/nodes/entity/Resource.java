package it.univda.nodes.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.TypeDef;
import org.locationtech.jts.geom.Point;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "resource")
@TypeDef(name = "point", typeClass = Point.class)
public class Resource implements Serializable {

    @Id
    @Column(name="id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="type", nullable = false, unique = false)
    private String type;

    @Column(name="description", nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_id")
    private Hub hub_id;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hub getLocation() {
        return hub_id;
    }

    public void setLocation(Hub hub) {
        this.hub_id = hub;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}