package com.flight.reservation.repos;

import com.flight.reservation.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
