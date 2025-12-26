package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String categoryName;
    
    private String description;
    
    private String defaultUrgency;
    
    private LocalDateTime createdAt;
    
    @ManyToMany
    @JoinTable(
        name = "category_urgency_policies",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "urgency_policy_id")
    )
    private Set<UrgencyPolicy> urgencyPolicies = new HashSet<>();
    
    public Category() {}
    
    public Category(String categoryName, String description, String defaultUrgency) {
        this.categoryName = categoryName;
        this.description = description;
        this.defaultUrgency = defaultUrgency;
    }
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDefaultUrgency() { return defaultUrgency; }
    public void setDefaultUrgency(String defaultUrgency) { this.defaultUrgency = defaultUrgency; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public Set<UrgencyPolicy> getUrgencyPolicies() { return urgencyPolicies; }
    public void setUrgencyPolicies(Set<UrgencyPolicy> urgencyPolicies) { this.urgencyPolicies = urgencyPolicies; }
}