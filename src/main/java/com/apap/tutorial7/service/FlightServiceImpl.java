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
	public void updateFlight(FlightModel newflight) {
//		FlightModel old = this.getFlightDetailById(newflight.getId());
//		System.out.println(old.getFlightNumber());
//		old.setFlightNumber(newflight.getFlightNumber());
//		old.setOrigin(newflight.getOrigin());
//		old.setDestination(newflight.getDestination());
//		old.setTime(newflight.getTime());
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
}