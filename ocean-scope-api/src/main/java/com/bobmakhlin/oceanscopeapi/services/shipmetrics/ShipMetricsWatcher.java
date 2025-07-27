package com.bobmakhlin.oceanscopeapi.services.shipmetrics;

import com.bobmakhlin.oceanscopeapi.repository.ShipRepository;
import com.bobmakhlin.oceanscopeapi.swagger.model.ShipMetrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShipMetricsWatcher {
    private final ShipRepository shipRepository;
    private final ShipMetricsService shipMetricsService;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(fixedRate = 5000)
    public void watchShipMetrics() {
        log.info("Watching $hip metrics...");

        var ships = shipRepository.findAll();
        var shipMetricsMap = new HashMap<UUID, ShipMetrics>();
        for (var ship : ships) {
            var currentMetrics = shipMetricsService.getShipMetrics(ship.getId());
            shipMetricsMap.put(ship.getId(), currentMetrics);
        }

        eventPublisher.publishEvent(new ShipMetricsBatchUpdatedEvent(shipMetricsMap));
    }
}
