package com.example.demo.service.impl;

import com.example.demo.service.CategorizationEngineService;
import org.springframework.stereotype.Service;

@Service
public class CategorizationEngineServiceImpl
        implements CategorizationEngineService {

    @Override
    public String categorizeTicket(String description) {

        if (description == null) {
            return "GENERAL";
        }

        String text = description.toLowerCase();

        if (text.contains("power") || text.contains("electric")) {
            return "ELECTRICAL";
        }
        if (text.contains("network") || text.contains("internet")) {
            return "NETWORK";
        }
        if (text.contains("hardware")) {
            return "HARDWARE";
        }

        return "GENERAL";
    }
}
rm src/main/java/com/example/demo/controller/CategorizationEngineController.java
