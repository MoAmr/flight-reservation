package com.flight.reservation.controllers;

import com.flight.reservation.dto.ReservationUpdateRequest;
import com.flight.reservation.entities.Reservation;
import com.flight.reservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

    @RequestMapping("/reservations/{id}")
    public Optional<Reservation> findReservation(@PathVariable("id") Long id) {
        LOGGER.info("Inside findReservation() for id: " + id);
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation;
    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
        LOGGER.info("Inside updateReservation() for: " + request);
        Optional<Reservation> reservation = reservationRepo.findById(request.getId());
        reservation.ifPresent(r -> {
            r.setNumberOfBags(request.getNumberOfBags());
            r.setCheckedIn(request.getCheckedIn());
            reservationRepo.save(r);
            LOGGER.info("Saving Reservation: " + r);
        });
        return reservation.get();
    }
}
