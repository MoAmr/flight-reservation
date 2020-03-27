package com.flight.reservation.services;

import com.flight.reservation.dto.ReservationRequest;
import com.flight.reservation.entities.Flight;
import com.flight.reservation.entities.Passenger;
import com.flight.reservation.entities.Reservation;
import com.flight.reservation.repos.FlightRepository;
import com.flight.reservation.repos.PassengerRepository;
import com.flight.reservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private PassengerRepository passengerRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        //Make Payment

        Long flightId = request.getFlightId();
        Optional<Flight> flight = flightRepo.findById(flightId);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());

        Passenger savedPassenger = passengerRepo.save(passenger);

        Reservation reservation = new Reservation();
        flight.ifPresent(f -> reservation.setFlight(f));
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepo.save(reservation);

        return savedReservation;
    }
}
