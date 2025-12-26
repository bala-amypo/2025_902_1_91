package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categorization")
public class CategorizationEngineController {

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final UrgencyPolicyRepository urgencyPolicyRepository;
    private final CategorizationLogRepository logRepository;

    public CategorizationEngineController(
            TicketRepository ticketRepository,
            CategoryRepository categoryRepository,
            CategorizationRuleRepository ruleRepository,
            UrgencyPolicyRepository urgencyPolicyRepository,
            CategorizationLogRepository logRepository) {

        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.ruleRepository = ruleRepository;
        this.urgencyPolicyRepository = urgencyPolicyRepository;
        this.logRepository = logRepository;
    }

    @PostMapping("/run/{ticketId}")
    public Ticket runCategorization(@PathVariable Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        List<Category> categories = categoryRepository.findAll();
        List<CategorizationRule> rules = ruleRepository.findAll();
        List<UrgencyPolicy> policies = urgencyPolicyRepository.findAll();
        List<CategorizationLog> logs = new ArrayList<>();

        TicketCategorizationEngine engine = new TicketCategorizationEngine();
        engine.categorize(ticket, categories, rules, policies, logs);

        ticketRepository.save(ticket);
        logRepository.saveAll(logs);

        return ticket;
    }
}
