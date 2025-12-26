package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;
    private String matchType;
    private Integer priority;

    @ManyToOne
    private Category category;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (priority == null) priority = 1;
        createdAt = LocalDateTime.now();
    }

    public void setKeyword(String k) { this.keyword = k; }
    public String getKeyword() { return keyword; }

    public void setMatchType(String m) { this.matchType = m; }
    public void setPriority(Integer p) { this.priority = p; }
    public Integer getPriority() { return priority; }

    public void setCategory(Category c) { this.category = c; }
    public Category getCategory() { return category; }
}
