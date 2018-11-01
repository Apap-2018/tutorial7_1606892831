package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping(value="/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
    	return flightService.addFlight(flight);
    }
    
    @GetMapping(value="/view/{flightNumber}")
    public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
    	FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber).get();
    	return flight;
    }
    
    @GetMapping(value="/all")
    public List<FlightModel> flightAll() {
    	List<FlightModel> flight = flightService.findAll();
    	return flight;
    }
    
    @DeleteMapping(value="/{flightId}")
    public String deletePilot(@PathVariable("flightId") long flightId) {
    	FlightModel flight = flightService.getFlightDetailById(flightId).get();
    		flightService.deleteFlight(flight);
    		return "flight has been deleted";
    }
    
    @PutMapping(value="/update/{flightId}")
    public String updateFlightSubmit(@PathVariable("flightId") long flightId,
    	@RequestParam("destination") Optional<String> destination,
    	@RequestParam("origin") Optional<String> origin,
    	@RequestParam("time") @DateTimeFormat(pattern="yyyy-MM-dd") Optional<Date>time ){
    	FlightModel flight = flightService.getFlightDetailById(flightId).get();
    	if(flight.equals(null)) {
    		return "Couldn't find your flight";
    	}
    	
    	else {
    		if(destination.isPresent()) {
    			flight.setDestination(destination.get());
    		}
    		if(origin.isPresent()) {
    			flight.setOrigin(origin.get());
    		}
    		if(time.isPresent()) {
    			flight.setTime(time.get());
    		}
    		flightService.updateflight(flight);
        	return "flight update success";
    	}
    }
    
    @GetMapping()
    public Object airport(@RequestParam("city") String city) throws Exception {
    	String path = Setting.airportUrl+"&term="+city+"&country=ID";
    	RestTemplate rest = new RestTemplate();
    	Object airport = rest.getForObject(path, Object.class);
    	return airport;
    }
}