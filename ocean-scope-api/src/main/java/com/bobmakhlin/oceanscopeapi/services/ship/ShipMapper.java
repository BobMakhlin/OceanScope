package com.bobmakhlin.oceanscopeapi.services.ship;

import com.bobmakhlin.oceanscopeapi.domain.ShipEntity;
import com.bobmakhlin.oceanscopeapi.swagger.model.AddShip;
import com.bobmakhlin.oceanscopeapi.swagger.model.Ship;
import com.bobmakhlin.oceanscopeapi.swagger.model.ShipMetrics;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipMapper {
    @Mapping(target = "metrics", expression = "java(shipMetrics)")
    Ship shipEntityToShip(ShipEntity shipEntity, @Context ShipMetrics shipMetrics);

    List<Ship> shipEntitiesToShips(List<ShipEntity> entities);

    ShipEntity addShipToShipEntity(AddShip addShip);
}
