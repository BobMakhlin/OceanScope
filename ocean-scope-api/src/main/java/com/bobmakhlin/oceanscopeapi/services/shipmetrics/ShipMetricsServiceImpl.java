package com.bobmakhlin.oceanscopeapi.services.shipmetrics;

import com.bobmakhlin.oceanscopeapi.swagger.model.ShipMetrics;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ShipMetricsServiceImpl implements ShipMetricsService {
    private final ConcurrentHashMap<UUID, ShipMetrics> shipMetrics = new ConcurrentHashMap<>();

    @Override
    public ShipMetrics getShipMetrics(UUID shipId) {
        if (shipMetrics.containsKey(shipId)) {
            return shipMetrics.get(shipId);
        }

        // Fake data.
        var metrics = ShipMetricsFactory.generateRandomMetrics();
        shipMetrics.put(shipId, metrics);
        return metrics;
    }

    @Override
    public boolean updateCoordinates(UUID shipId, double lat, double lng) {
        var metrics = shipMetrics.get(shipId);
        if (metrics == null) {
            return false;
        }
        metrics.setLat(lat);
        metrics.setLng(lat);
        return true;
    }

    @Override
    public boolean updateSpeed(UUID shipId, double speed) {
        var metrics = shipMetrics.get(shipId);
        if (metrics == null) {
            return false;
        }
        metrics.setSpeed(speed);
        return true;
    }

    @Override
    public boolean updateHeading(UUID shipId, double heading) {
        var metrics = shipMetrics.get(shipId);
        if (metrics == null) {
            return false;
        }
        metrics.setHeading(heading);
        return true;
    }

    private static class ShipMetricsFactory {
        public static ShipMetrics generateRandomMetrics() {
            return ShipMetrics.builder()
                    .lat(randomLat())
                    .lng(randomLng())
                    .speed(randomSpeed())
                    .heading(randomHeading())
                    .build();
        }

        private static double randomLat() {
            return randomDouble(-90, 90);
        }

        private static double randomLng() {
            return randomDouble(-180, 180);
        }

        private static double randomSpeed() {
            return randomDouble(5, 25);
        }

        private static double randomHeading() {
            return randomDouble(0, 359.9);
        }

        private static double randomDouble(double min, double max) {
            return ThreadLocalRandom.current().nextDouble(min, max);
        }
    }
}
