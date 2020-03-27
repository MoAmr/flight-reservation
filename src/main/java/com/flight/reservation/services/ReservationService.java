package com.flight.reservation.services;

import com.flight.reservation.dto.ReservationRequest;
import com.flight.reservation.entities.Reservation;

public interface ReservationService {

    public Reservation bookFlight(ReservationRequest request);
}
