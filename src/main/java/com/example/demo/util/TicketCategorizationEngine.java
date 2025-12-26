package com.example.demo.util;

import com.example.demo.model.*;
import java.util.*;

public class TicketCategorizationEngine {

    public void categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs) {

        ticket.setUrgencyLevel("LOW");

        for (CategorizationRule rule : rules) {
            if (ticket.getDescription().toLowerCase().contains(rule.getKeyword().toLowerCase())) {
                ticket.setAssignedCategory(rule.getCategory());
                ticket.setUrgencyLevel(rule.getCategory().getDefaultUrgency());

                CategorizationLog log = new CategorizationLog();
                logs.add(log);
            }
        }

        for (UrgencyPolicy p : policies) {
            if (ticket.getDescription().toLowerCase().contains(p.getKeyword().toLowerCase())) {
                ticket.setUrgencyLevel(p.getUrgencyOverride());
            }
        }
    }
}
