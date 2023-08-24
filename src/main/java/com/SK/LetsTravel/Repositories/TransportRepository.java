package com.SK.LetsTravel.Repositories;

import com.SK.LetsTravel.Models.Transport;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> {
}
