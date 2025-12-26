package com.example.demo.util;

import com.example.demo.model.*;

import java.util.List;

public class TicketCategorizationEngine {

    // New method matching your controller
    public void categorize(Ticket ticket,
                           List<Category> categories,
                           List<CategorizationRule> rules,
                           List<UrgencyPolicy> urgencies,
                           List<CategorizationLog> logs) {

        // Simple example logic
        for (CategorizationRule rule : rules) {
            for (Category category : categories) {
                if (ticket.getDescription().contains(rule.getKeyword())) {
                    ticket.setAssignedCategory(category);  // assign category
                    ticket.setUrgencyLevel(category.getDefaultUrgency()); // assign urgency
                    break;
                }
            }
        }
    }
}
