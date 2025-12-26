package com.example.demo.service.impl;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;
import com.example.demo.service.CategorizationEngineService;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorizationEngineServiceImpl implements CategorizationEngineService {

    @Autowired
    private TicketCategorizationEngine engine;

    // Dummy list of logs
    private final List<CategorizationLog> logs = new ArrayList<>();

    @Override
    public String categorizeTicket(Ticket ticket) {
        // Example: use engine to categorize ticket
        return engine.categorize(ticket, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), logs);
    }

    @Override
    public void categorizeOpenTickets() {
        // Dummy logic: categorize some open tickets
        List<Ticket> openTickets = new ArrayList<>(); // fetch from repository in real scenario
        for (Ticket ticket : openTickets) {
            categorizeTicket(ticket);
        }
    }

    @Override
    public CategorizationLog getLog(Long ticketId) {
        // Dummy retrieval
        return logs.stream()
                .filter(log -> log.getTicket() != null && ticketId.equals(log.getTicket().getId()))
                .findFirst()
                .orElse(null);
    }
}
