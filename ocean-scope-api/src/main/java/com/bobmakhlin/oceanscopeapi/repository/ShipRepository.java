package com.bobmakhlin.oceanscopeapi.repository;

import com.bobmakhlin.oceanscopeapi.domain.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, UUID> {
    List<ShipEntity> findByNameContainingIgnoreCase(String name);
}