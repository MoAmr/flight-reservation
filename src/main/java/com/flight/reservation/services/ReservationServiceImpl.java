package com.flight.reservation.services;

import com.flight.reservation.dto.ReservationRequest;
import com.flight.reservation.entities.Flight;
import com.flight.reservation.entities.Passenger;
import com.flight.reservation.entities.Reservation;
import com.flight.reservation.repos.FlightRepository;
import com.flight.reservation.repos.PassengerRepository;
import com.flight.reservation.repos.ReservationRepository;
import com.flight.reservation.util.EmailUtil;
import com.flight.reservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private PDFGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private static final String RESERVATIONS = "src/main/resources/reservations/";

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        LOGGER.info("Inside bookFlight()");

        //Make Payment

        Long flightId = request.getFlightId();
        LOGGER.info("Fetching the flight for flight id: " + flightId);
        Optional<Flight> flight = flightRepo.findById(flightId);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());

        LOGGER.info("Saving the passenger: " + passenger);
        Passenger savedPassenger = passengerRepo.save(passenger);

        Reservation reservation = new Reservation();
        flight.ifPresent(f -> reservation.setFlight(f));
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        LOGGER.info("Saving the reservation: " + reservation);
        Reservation savedReservation = reservationRepo.save(reservation);

        String filePath = RESERVATIONS + "reservation" + savedReservation.getId() + ".pdf";
        LOGGER.info("Generating the Itinerary");
        pdfGenerator.generateItinerary(savedReservation, filePath);
        LOGGER.info("Emailing the Itinerary");
        emailUtil.sendItinerary(passenger.getEmail(), filePath);

        return savedReservation;
    }
}
