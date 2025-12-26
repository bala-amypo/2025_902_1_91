package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "urgency_policies")
public class UrgencyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName;
    private String keyword;
    private String urgencyOverride;

    @ManyToMany
    @JoinTable(name = "urgency_policy_categories",
               joinColumns = @JoinColumn(name = "urgency_policy_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    private LocalDateTime createdAt;

    // Constructors
    public UrgencyPolicy() {}

    public UrgencyPolicy(String policyName, String keyword, String urgencyOverride) {
        this.policyName = policyName;
        this.keyword = keyword;
        this.urgencyOverride = urgencyOverride;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public String getUrgencyOverride() { return urgencyOverride; }
    public void setUrgencyOverride(String urgencyOverride) { this.urgencyOverride = urgencyOverride; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
