package com.example.demo.service;

import java.util.List;
import com.example.demo.model.CategorizationLog;

public interface CategorizationEngineService {

    void runEngine(Long ticketId);

    List<CategorizationLog> getLogsForTicket(Long ticketId);

    CategorizationLog getLog(Long logId);
}
