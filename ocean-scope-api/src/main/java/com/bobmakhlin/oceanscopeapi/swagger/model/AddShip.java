package com.bobmakhlin.oceanscopeapi.swagger.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddShip {
    private String name;
    private ShipType type;
}
