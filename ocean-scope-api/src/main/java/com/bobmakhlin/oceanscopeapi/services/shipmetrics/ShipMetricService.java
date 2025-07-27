package com.bobmakhlin.oceanscopeapi.services.shipmetrics;

import com.bobmakhlin.oceanscopeapi.swagger.model.ShipMetrics;

import java.util.UUID;

public interface ShipMetricService {
    ShipMetrics getShipMetrics(UUID shipId);

    boolean updateCoordinates(UUID shipId, double lat, double lng);

    boolean updateSpeed(UUID shipId, double speed);

    boolean updateHeading(UUID shipId, double heading);
}
