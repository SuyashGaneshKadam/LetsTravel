package com.SK.LetsTravel.Repositories;

import com.SK.LetsTravel.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
