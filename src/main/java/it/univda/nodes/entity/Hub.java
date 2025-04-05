package it.univda.nodes.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import java.util.Base64;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hub")
@Convert(attributeName = "point", converter = Point.class)
public class Hub {

    @Id
    @Column(name="id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, unique = false)
    private String name;

    @Column(name="description", nullable = true, columnDefinition = "text")
    private String description;

    @Column(name="active", nullable = false)
    private boolean active;

    @Column(name="type")
    private String type;

    @ManyToMany
    @JoinTable(
            name = "hub_district",
            joinColumns = @JoinColumn(name = "hub_id"),
            inverseJoinColumns = @JoinColumn(name = "district_id")
    )
    private Set<District> districts;

    @Column(name="latitude", nullable = false)
    private double latitude;

    @Column(name="longitude", nullable = false)
    private double longitude;

    @OneToMany(mappedBy = "hub_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> resources;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageAsBase64() {
        return (image != null) ? Base64.getEncoder().encodeToString(image) : null;
    }
}

/*@Entity
@Table(name = "hub")
@TypeDef(name = "point", typeClass = Point.class)
public class Hub {

    @Id
    @Column(name="id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = true)
    private String description;

    @Column(name="active", nullable = false)
    private boolean active;

    @Column(name="type")
    private String type;

    @ManyToMany
    @JoinTable(
            name = "hub_district",
            joinColumns = @JoinColumn(name = "hub_id"),
            inverseJoinColumns = @JoinColumn(name = "district_id")
    )
    private Set<District> districts;

    @Column(name="latitude", nullable = false)
    private double latitude;

    @Column(name="longitude", nullable = false)
    private double longitude;

    @OneToMany(mappedBy = "hub", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> resources;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}*/
