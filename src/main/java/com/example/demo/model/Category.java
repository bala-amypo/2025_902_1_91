package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private String defaultUrgency;

    @OneToMany(mappedBy = "category")
    private List<UrgencyPolicy> urgencyPolicies;

    private LocalDateTime createdAt;

    // Constructors
    public Category() {}

    public Category(String categoryName, String defaultUrgency) {
        this.categoryName = categoryName;
        this.defaultUrgency = defaultUrgency;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getDefaultUrgency() { return defaultUrgency; }
    public void setDefaultUrgency(String defaultUrgency) { this.defaultUrgency = defaultUrgency; }

    public List<UrgencyPolicy> getUrgencyPolicies() { return urgencyPolicies; }
    public void setUrgencyPolicies(List<UrgencyPolicy> urgencyPolicies) { this.urgencyPolicies = urgencyPolicies; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
