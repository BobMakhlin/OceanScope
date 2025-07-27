package com.bobmakhlin.oceanscopeapi.services.shipmetrics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShipMetricsWebSocketPublisher {
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleMetricsBatchUpdate(ShipMetricsBatchUpdatedEvent event) {
        log.info("[Web Socket] Publishing $hip metrics...");
        messagingTemplate.convertAndSend("/topic/ships/all", event.shipMetrics());
    }
}
