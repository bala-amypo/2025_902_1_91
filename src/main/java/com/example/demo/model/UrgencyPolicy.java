package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class UrgencyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName;
    private String keyword;
    private String urgencyOverride;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "urgencyPolicies")
    private Set<Category> categories = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Set<Category> getCategories() { return categories; }

    public void setKeyword(String k) { this.keyword = k; }
    public String getKeyword() { return keyword; }

    public void setUrgencyOverride(String u) { this.urgencyOverride = u; }
    public String getUrgencyOverride() { return urgencyOverride; }

    public void setPolicyName(String p) { this.policyName = p; }
}
