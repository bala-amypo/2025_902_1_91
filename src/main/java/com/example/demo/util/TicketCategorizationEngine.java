package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class TicketCategorizationEngine {
    
    public void categorize(Ticket ticket, List<Category> categories, List<CategorizationRule> rules, List<UrgencyPolicy> policies, List<CategorizationLog> logs) {
        // Default urgency
        ticket.setUrgencyLevel("LOW");
        
        // Find matching rule with highest priority
        CategorizationRule matchedRule = null;
        int highestPriority = 0;
        
        for (CategorizationRule rule : rules) {
            if (matches(ticket, rule) && rule.getPriority() > highestPriority) {
                matchedRule = rule;
                highestPriority = rule.getPriority();
            }
        }
        
        // Apply category and urgency from matched rule
        if (matchedRule != null) {
            ticket.setAssignedCategory(matchedRule.getCategory());
            ticket.setUrgencyLevel(matchedRule.getCategory().getDefaultUrgency());
            
            // Create log entry
            CategorizationLog log = new CategorizationLog();
            log.setTicket(ticket);
            log.setAppliedRule(matchedRule);
            log.setMatchedKeyword(matchedRule.getKeyword());
            log.setAssignedCategory(matchedRule.getCategory().getCategoryName());
            log.setAssignedUrgency(matchedRule.getCategory().getDefaultUrgency());
            logs.add(log);
        }
        
        // Check for urgency policy overrides
        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription().toLowerCase().contains(policy.getKeyword().toLowerCase())) {
                ticket.setUrgencyLevel(policy.getUrgencyOverride());
                break;
            }
        }
    }
    
    private boolean matches(Ticket ticket, CategorizationRule rule) {
        String description = ticket.getDescription().toLowerCase();
        String keyword = rule.getKeyword().toLowerCase();
        
        switch (rule.getMatchType()) {
            case "EXACT":
                return description.equals(keyword);
            case "CONTAINS":
                return description.contains(keyword);
            case "REGEX":
                return Pattern.matches(keyword, description);
            default:
                return false;
        }
    }
}