package com.bobmakhlin.oceanscopeapi.swagger.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipMetrics {
    private double lat;
    private double lng;
    private double speed;
    private double heading;
}
