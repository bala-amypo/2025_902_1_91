package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.CategorizationLog;
import com.example.demo.repository.CategorizationLogRepository;
import com.example.demo.service.CategorizationEngineService;

@Service
public class CategorizationEngineServiceImpl implements CategorizationEngineService {

    private final CategorizationLogRepository logRepository;

    public CategorizationEngineServiceImpl(CategorizationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void runEngine(Long ticketId) {
        // logic can be empty for now
    }

    @Override
    public List<CategorizationLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicketId(ticketId);
    }

    @Override
    public CategorizationLog getLog(Long logId) {
        return logRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }
}
