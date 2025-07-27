package com.bobmakhlin.oceanscopeapi.services.shipmetrics;

import com.bobmakhlin.oceanscopeapi.repository.ShipRepository;
import com.bobmakhlin.oceanscopeapi.swagger.model.ShipMetrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipMetricsSimulator {
    private static final double SPEED_CHANGE_CHANCE = 0.3; // 30%
    private static final double HEADING_CHANGE_CHANCE = 0.001; // 0.1%

    private final ShipRepository shipRepository;
    private final ShipMetricsService shipMetricsService;

    private static final double KNOTS_TO_LATLNG = 0.000514; // approximation

    @Scheduled(fixedRateString = "${ship.metrics.simulation.interval}")
    public void simulateShipMetrics() {
        log.info("Simulating $hip metrics...");

        var ships = shipRepository.findAll();

        for (var ship : ships) {
            var currentMetrics = shipMetricsService.getShipMetrics(ship.getId());

            // Move based on speed and heading.
            simulateMovement(ship.getId(), currentMetrics);
            updateSpeed(ship.getId(), currentMetrics);
            updateHeading(ship.getId(), currentMetrics);
        }
    }

    private double maybeChange(double chance, double value, double min, double max, double range) {
        if (Math.random() < chance) {
            double delta = (Math.random() - 0.5) * 2 * range;
            value += delta;
        }
        return Math.clamp(value, min, max);
    }

    private void simulateMovement(UUID shipId, ShipMetrics currentMetrics) {
        var distance = currentMetrics.getSpeed() * KNOTS_TO_LATLNG;
        var headingRad = Math.toRadians(currentMetrics.getHeading());
        var deltaLat = distance * Math.cos(headingRad);
        var deltaLng = distance * Math.sin(headingRad);

        var newLat = Math.clamp(currentMetrics.getLat() + deltaLat, -90, 90);
        var newLng = Math.clamp(currentMetrics.getLng() + deltaLng, -180, 180);

        shipMetricsService.updateCoordinates(shipId, newLat, newLng);
    }

    private void updateSpeed(UUID shipId, ShipMetrics currentMetrics) {
        var newSpeed = maybeChange(SPEED_CHANGE_CHANCE, currentMetrics.getSpeed(), 5, 25, 1);
        shipMetricsService.updateSpeed(shipId, newSpeed);
    }

    private void updateHeading(UUID shipId, ShipMetrics currentMetrics) {
        var newHeading = maybeChange(HEADING_CHANGE_CHANCE, currentMetrics.getHeading(), 0, 359, 10);
        shipMetricsService.updateHeading(shipId, newHeading);
    }
}
