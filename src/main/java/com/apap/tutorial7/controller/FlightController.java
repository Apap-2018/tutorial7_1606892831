package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    	@RequestParam("destination") String destination,
    	@RequestParam("origin") String origin,
    	@RequestParam("time") Date time ){
    	FlightModel flight = flightService.getFlightDetailById(flightId).get();
    	if(flight.equals(null)) {
    		return "Couldn't find your flight";
    	}
    	
    	flight.setDestination(destination);
    	flight.setOrigin(origin);
    	flight.setTime(time);
//    	flightService.updateflight(flightId,flight);
    	return "flight update success";
   
    }
    
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }
    
    @GetMapping(value="/airport/{city}")
    public String getStatus(@PathVariable("city") String city) throws Exception{
    	String path = Setting.flightUrl + "/flight?city="+ city;
    	return restTemplate.getForEntity(path, String.class).getBody();
    	
    }
        
}