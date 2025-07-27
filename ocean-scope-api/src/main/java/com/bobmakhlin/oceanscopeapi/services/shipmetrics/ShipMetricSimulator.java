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
public class ShipMetricSimulator {
    private final ShipRepository shipRepository;
    private final ShipMetricService shipMetricService;

    private static final double KNOTS_TO_LATLNG = 0.000514; // approximation

    @Scheduled(fixedRate = 2500)
    public void simulateShipMetrics() {
        log.info("Simulating $hip metrics...");

        var ships = shipRepository.findAll();

        for (var ship : ships) {
            var currentMetrics = shipMetricService.getShipMetrics(ship.getId());

            // Move based on speed and heading.
            simulateMovement(ship.getId(), currentMetrics);
            // Occasionally change speed and heading.
            updateSpeed(ship.getId(), currentMetrics);
            updateHeading(ship.getId(), currentMetrics);
        }
    }

    private double maybeChange(double value, double min, double max, double range) {
        if (Math.random() < 0.3) { // 30% chance to change
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

        shipMetricService.updateCoordinates(shipId, newLat, newLng);
    }

    private void updateSpeed(UUID shipId, ShipMetrics currentMetrics) {
        var newSpeed = maybeChange(currentMetrics.getSpeed(), 5, 25, 1);
        shipMetricService.updateSpeed(shipId, newSpeed);
    }

    private void updateHeading(UUID shipId, ShipMetrics currentMetrics) {
        var newHeading = maybeChange(currentMetrics.getHeading(), 0, 359, 10);
        shipMetricService.updateHeading(shipId, newHeading);
    }
}
