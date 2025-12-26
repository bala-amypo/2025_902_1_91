package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class TicketCategorizationEngine {
    
    public void categorize(Ticket ticket, List<Category> categories, List<CategorizationRule> rules, List<UrgencyPolicy> policies, List<CategorizationLog> logs) {
        CategorizationRule matchedRule = findMatchingRule(ticket, rules);
        
        if (matchedRule != null) {
            ticket.setAssignedCategory(matchedRule.getCategory());
            ticket.setUrgencyLevel(matchedRule.getCategory().getDefaultUrgency());
            
            // Check for urgency override
            String urgencyOverride = findUrgencyOverride(ticket, policies);
            if (urgencyOverride != null) {
                ticket.setUrgencyLevel(urgencyOverride);
            }
            
            // Create log entry
            CategorizationLog log = new CategorizationLog(
                ticket,
                matchedRule,
                matchedRule.getKeyword(),
                matchedRule.getCategory().getCategoryName(),
                ticket.getUrgencyLevel()
            );
            logs.add(log);
        } else {
            ticket.setUrgencyLevel("LOW");
        }
    }
    
    private CategorizationRule findMatchingRule(Ticket ticket, List<CategorizationRule> rules) {
        return rules.stream()
                .sorted((r1, r2) -> Integer.compare(r2.getPriority(), r1.getPriority()))
                .filter(rule -> matchesRule(ticket, rule))
                .findFirst()
                .orElse(null);
    }
    
    private boolean matchesRule(Ticket ticket, CategorizationRule rule) {
        String description = ticket.getDescription().toLowerCase();
        String keyword = rule.getKeyword().toLowerCase();
        
        switch (rule.getMatchType()) {
            case "EXACT":
                return description.equals(keyword);
            case "CONTAINS":
                return description.contains(keyword);
            case "REGEX":
                return Pattern.compile(keyword).matcher(description).find();
            default:
                return false;
        }
    }
    
    private String findUrgencyOverride(Ticket ticket, List<UrgencyPolicy> policies) {
        String description = ticket.getDescription().toLowerCase();
        
        return policies.stream()
                .filter(policy -> description.contains(policy.getKeyword().toLowerCase()))
                .map(UrgencyPolicy::getUrgencyOverride)
                .findFirst()
                .orElse(null);
    }
}