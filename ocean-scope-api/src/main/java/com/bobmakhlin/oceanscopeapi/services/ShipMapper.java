package com.bobmakhlin.oceanscopeapi.services;

import com.bobmakhlin.oceanscopeapi.domain.ShipEntity;
import com.bobmakhlin.oceanscopeapi.swagger.model.AddShip;
import com.bobmakhlin.oceanscopeapi.swagger.model.Ship;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipMapper {
    Ship shipEntityToShip(ShipEntity shipEntity);

    List<Ship> shipEntitiesToShips(List<ShipEntity> entities);

    ShipEntity addShipToShipEntity(AddShip addShip);
}
