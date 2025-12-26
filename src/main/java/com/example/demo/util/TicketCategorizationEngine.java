package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class TicketCategorizationEngine {

    public String categorizeTicket(String description) {
        // Dummy logic: replace with your real categorization logic
        if (description.toLowerCase().contains("urgent")) {
            return "High Priority";
        } else {
            return "Normal";
        }
    }
}
