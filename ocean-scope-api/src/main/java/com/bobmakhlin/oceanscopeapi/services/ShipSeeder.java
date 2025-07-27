package com.bobmakhlin.oceanscopeapi.services;

import com.bobmakhlin.oceanscopeapi.swagger.api.ShipApiDelegate;
import com.bobmakhlin.oceanscopeapi.swagger.model.AddShip;
import com.bobmakhlin.oceanscopeapi.swagger.model.ShipType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
@Slf4j
public class ShipSeeder implements CommandLineRunner {
    private final ShipApiDelegate shipApiDelegate;

    @Override
    public void run(String... args) {
        if (shouldSeed()) {
            log.info("Seeding ship database...");
            shipApiDelegate.addShip(AddShip.builder().type(ShipType.CONTAINER).name("Ever Given").build());
            shipApiDelegate.addShip(AddShip.builder().type(ShipType.TANKER).name("Titanic").build());
            shipApiDelegate.addShip(AddShip.builder().type(ShipType.TANKER).name("HMS Victory").build());
            log.info("Seeding ship database complete.");
        }
    }

    private boolean shouldSeed() {
        return shipApiDelegate.getShips(null).isEmpty();
    }
}
