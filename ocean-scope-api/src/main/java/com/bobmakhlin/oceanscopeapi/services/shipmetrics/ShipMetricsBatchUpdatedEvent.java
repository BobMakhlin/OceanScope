package com.bobmakhlin.oceanscopeapi.services.shipmetrics;

import com.bobmakhlin.oceanscopeapi.swagger.model.ShipMetrics;

import java.util.Map;
import java.util.UUID;

public record ShipMetricsBatchUpdatedEvent(Map<UUID, ShipMetrics> shipMetrics) {
}
