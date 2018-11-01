package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteFlightByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }

	@Override
	public Optional<FlightModel> getFlightDetailById(long flightId) {
		// TODO Auto-generated method stub
		return flightDb.findById(flightId);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.delete(flight);
	}

	@Override
	public List<FlightModel> findAll() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}

	@Override
	public void updateflight(FlightModel flight) {
		// TODO Auto-generated method stub
		FlightModel flightBaru = flightDb.getOne(flight.getId());
		flightBaru.setDestination(flight.getDestination());
		flightBaru.setOrigin(flight.getOrigin());
		flightBaru.setTime(flight.getTime());
	}
}