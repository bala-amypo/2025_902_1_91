package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketCategorizationEngine {

    public String categorize(Ticket ticket,
                             List<Category> categories,
                             List<CategorizationRule> rules,
                             List<UrgencyPolicy> policies,
                             List<CategorizationLog> logs) {
        // Example dummy logic: assign first category or "Uncategorized"
        String category = categories.isEmpty() ? "Uncategorized" : categories.get(0).getName();
        ticket.setCategory(category);

        // Optional logging
        if (logs != null) {
            CategorizationLog log = new CategorizationLog();
            log.setTicket(ticket);
            log.setAppliedRule(null); // replace with actual rule if needed
            logs.add(log);
        }

        return category;
    }
}
