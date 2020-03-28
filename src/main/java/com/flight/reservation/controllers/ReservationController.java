package com.flight.reservation.controllers;

import com.flight.reservation.dto.ReservationRequest;
import com.flight.reservation.entities.Flight;
import com.flight.reservation.entities.Reservation;
import com.flight.reservation.repos.FlightRepository;
import com.flight.reservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private ReservationService reservationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        LOGGER.info("Inside showCompleteReservation() invoked with the flight id: " + flightId);
        Optional<Flight> flight = flightRepo.findById(flightId);
        if (flight.isPresent()) {
            modelMap.addAttribute("flight", flight.get());
        }
        LOGGER.info("Flight is: " + flight);
        return "completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request, ModelMap modelMap) {
        LOGGER.info("Inside completeReservation() " + request);
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg",
                "Reservation created successfully and the id is: " + reservation.getId());
        return "reservationConfirmation";
    }
}
