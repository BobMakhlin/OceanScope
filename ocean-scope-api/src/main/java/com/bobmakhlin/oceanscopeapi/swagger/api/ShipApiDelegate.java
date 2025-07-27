package com.bobmakhlin.oceanscopeapi.swagger.api;

import com.bobmakhlin.oceanscopeapi.swagger.model.AddShip;
import com.bobmakhlin.oceanscopeapi.swagger.model.Ship;

import java.util.List;

public interface ShipApiDelegate {
    List<Ship> getShips(String query);

    Ship addShip(AddShip addShip);
}
