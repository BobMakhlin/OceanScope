package com.bobmakhlin.oceanscopeapi.services;

import com.bobmakhlin.oceanscopeapi.repository.ShipRepository;
import com.bobmakhlin.oceanscopeapi.swagger.api.ShipApiDelegate;
import com.bobmakhlin.oceanscopeapi.swagger.model.AddShip;
import com.bobmakhlin.oceanscopeapi.swagger.model.Ship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipApiDelegateImpl implements ShipApiDelegate {

    private final ShipRepository shipRepository;
    private final ShipMapper shipMapper;

    @Override
    public List<Ship> getShips(String shipName) {
        var shipEntities = shipName == null
                ? shipRepository.findAll() : shipRepository.findByNameContainingIgnoreCase(shipName);
        return shipMapper.shipEntitiesToShips(shipEntities);
    }

    @Override
    public Ship addShip(AddShip addShip) {
        var shipEntity = shipMapper.addShipToShipEntity(addShip);
        var savedShipEntity = shipRepository.saveAndFlush(shipEntity);
        return shipMapper.shipEntityToShip(savedShipEntity);
    }

}
