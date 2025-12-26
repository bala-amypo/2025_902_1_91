package com.example.demo.service;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;

public interface CategorizationEngineService {

    String categorizeTicket(Ticket ticket);

    void categorizeOpenTickets();

    CategorizationLog getLog(Long ticketId);
}
