package com.bobmakhlin.oceanscopeapi.services.ship;

import com.bobmakhlin.oceanscopeapi.repository.ShipRepository;
import com.bobmakhlin.oceanscopeapi.services.shipmetrics.ShipMetricsService;
import com.bobmakhlin.oceanscopeapi.swagger.api.ShipApiDelegate;
import com.bobmakhlin.oceanscopeapi.swagger.model.AddShip;
import com.bobmakhlin.oceanscopeapi.swagger.model.Ship;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipServiceImpl implements ShipApiDelegate {

    private final ShipRepository shipRepository;
    private final ShipMetricsService shipMetricsService;
    private final ShipMapper shipMapper;

    @Override
    public List<Ship> getShips(String shipName) {
        log.info("Getting ships. shipName = {}", shipName);
        var shipEntities = shipName == null ? shipRepository.findAll() : shipRepository.findByNameContainingIgnoreCase(shipName);
        var ships = shipMapper.shipEntitiesToShips(shipEntities);
        ships.forEach(ship -> ship.setMetrics(shipMetricsService.getShipMetrics(ship.getId())));

        return ships;
    }

    @Override
    public Ship addShip(AddShip addShip) {
        var shipEntity = shipMapper.addShipToShipEntity(addShip);
        var savedShipEntity = shipRepository.saveAndFlush(shipEntity);
        var metrics = shipMetricsService.getShipMetrics(savedShipEntity.getId());

        return shipMapper.shipEntityToShip(savedShipEntity, metrics);
    }

}
