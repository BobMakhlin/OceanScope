package com.bobmakhlin.oceanscopeapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private ShipEntityType type;
}
