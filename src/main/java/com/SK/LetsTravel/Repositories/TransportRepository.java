package com.SK.LetsTravel.Repositories;

import com.SK.LetsTravel.Models.Transport;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> {

    @Query(value = "select * from transport where company_name =:compName and YEAR(journey_date)=:year and MONTH(journey_date)=:month", nativeQuery = true)
    public Transport findTransportByCompanyName(String compName, Integer month, Integer year);
}
