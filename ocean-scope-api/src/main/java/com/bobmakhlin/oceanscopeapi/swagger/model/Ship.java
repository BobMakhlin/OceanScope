package com.bobmakhlin.oceanscopeapi.swagger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ship {
    private UUID id;
    private String name;
    private ShipType type;
    private ShipMetrics metrics;
}
