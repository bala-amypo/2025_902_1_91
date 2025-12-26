package com.example.demo.service.impl;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.CategorizationEngineService;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationEngineServiceImpl implements CategorizationEngineService {

    private final TicketRepository ticketRepository;
    private final TicketCategorizationEngine ticketEngine;

    @Autowired
    public CategorizationEngineServiceImpl(TicketRepository ticketRepository,
                                           TicketCategorizationEngine ticketEngine) {
        this.ticketRepository = ticketRepository;
        this.ticketEngine = ticketEngine;
    }

    /**
     * Categorize all open tickets
     */
    @Override
    public void categorizeOpenTickets() {
        List<Ticket> openTickets = ticketRepository.findAllByStatus("OPEN");

        for (Ticket ticket : openTickets) {
            String category = ticketEngine.categorizeTicket(ticket.getDescription());
            ticket.setCategory(category);
            ticketRepository.save(ticket);
        }
    }

    /**
     * Categorize a single ticket
     */
    @Override
    public String categorizeTicket(Ticket ticket) {
        String category = ticketEngine.categorizeTicket(ticket.getDescription());
        ticket.setCategory(category);
        ticketRepository.save(ticket);
        return category;
    }
}
