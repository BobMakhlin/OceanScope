package com.bobmakhlin.oceanscopeapi.swagger.api;

import com.bobmakhlin.oceanscopeapi.swagger.model.Ship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
@Tag(name = "Ships", description = "Operations related to ship tracking and simulation")
public class ShipController {

    private final ShipApiDelegate delegate;

    public ShipController(ShipApiDelegate delegate) {
        this.delegate = delegate;
    }

    @GetMapping
    @Operation(
            summary = "Get all ships",
            description = "Returns a list of all simulated ships. Optionally filter by name.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of ships",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ship.class))
                    )
            }
    )
    public List<Ship> getShips(
            @Parameter(
                    name = "shipName",
                    description = "Substring match for ship name (case-insensitive)",
                    in = ParameterIn.QUERY
            )
            @RequestParam(required = false) String shipName
    ) {
        return delegate.getShips(shipName);
    }
}