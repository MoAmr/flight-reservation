package com.flight.reservation.controllers;

import com.flight.reservation.entities.Flight;
import com.flight.reservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepo;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Optional<Flight> flight = flightRepo.findById(flightId);
        if (flight.isPresent()) {
            modelMap.addAttribute("flight", flight.get());
        }
        return "completeReservation";
    }
}
