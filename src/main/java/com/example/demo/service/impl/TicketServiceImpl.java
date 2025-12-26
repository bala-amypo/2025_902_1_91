package com.example.demo.service.impl;

import com.example.demo.model.Ticket;
import com.example.demo.util.TicketCategorizationEngine;

public class TicketServiceImpl {

    private final TicketCategorizationEngine engine;

    public TicketServiceImpl(TicketCategorizationEngine engine) {
        this.engine = engine;
    }

    public void processTicket(Ticket ticket) {
        // Example: use description or title
        String desc = ticket.getDescription();
        System.out.println("Processing ticket: " + desc);
    }
}
