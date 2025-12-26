package com.example.demo.service.impl;

import com.example.demo.repository.*;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorizationEngineServiceImpl {

    private final TicketRepository ticketRepo;
    private final CategoryRepository categoryRepo;
    private final CategorizationRuleRepository ruleRepo;
    private final UrgencyPolicyRepository urgencyRepo;
    private final CategorizationLogRepository logRepo;
    private final TicketCategorizationEngine engine;

    @Autowired
    public CategorizationEngineServiceImpl(TicketRepository ticketRepo,
                                           CategoryRepository categoryRepo,
                                           CategorizationRuleRepository ruleRepo,
                                           UrgencyPolicyRepository urgencyRepo,
                                           CategorizationLogRepository logRepo,
                                           TicketCategorizationEngine engine) {
        this.ticketRepo = ticketRepo;
        this.categoryRepo = categoryRepo;
        this.ruleRepo = ruleRepo;
        this.urgencyRepo = urgencyRepo;
        this.logRepo = logRepo;
        this.engine = engine;
    }

    // Add your service methods here (getLogsForTicket, categorizeTicket, etc.)
}
