package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CategorizationEngineService;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorizationEngineServiceImpl implements CategorizationEngineService {
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final UrgencyPolicyRepository policyRepository;
    private final CategorizationLogRepository logRepository;
    private final TicketCategorizationEngine engine;
    
    public CategorizationEngineServiceImpl(TicketRepository ticketRepository, CategoryRepository categoryRepository,
                                         CategorizationRuleRepository ruleRepository, UrgencyPolicyRepository policyRepository,
                                         CategorizationLogRepository logRepository, TicketCategorizationEngine engine) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.ruleRepository = ruleRepository;
        this.policyRepository = policyRepository;
        this.logRepository = logRepository;
        this.engine = engine;
    }
    
    @Override
    public Ticket categorizeTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        
        List<Category> categories = categoryRepository.findAll();
        List<CategorizationRule> rules = ruleRepository.findAll();
        List<UrgencyPolicy> policies = policyRepository.findAll();
        
        engine.categorize(ticket, categories, rules, policies);
        
        Ticket savedTicket = ticketRepository.save(ticket);
        
        // Create log entry if categorization was successful
        if (savedTicket.getAssignedCategory() != null) {
            CategorizationRule matchedRule = rules.stream()
                    .filter(rule -> rule.getCategory().equals(savedTicket.getAssignedCategory()))
                    .findFirst()
                    .orElse(null);
            
            if (matchedRule != null) {
                CategorizationLog log = new CategorizationLog(
                        savedTicket,
                        matchedRule,
                        matchedRule.getKeyword(),
                        savedTicket.getAssignedCategory().getCategoryName(),
                        savedTicket.getUrgencyLevel()
                );
                logRepository.save(log);
            }
        }
        
        return savedTicket;
    }
    
    @Override
    public List<CategorizationLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicketId(ticketId);
    }
    
    @Override
    public CategorizationLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}