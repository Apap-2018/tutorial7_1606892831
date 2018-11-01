package com.apap.tutorial7.service;

import java.util.Optional;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

    @Override
    public PilotModel addPilot(PilotModel pilot) {
        return pilotDb.save(pilot);
    }

    @Override
    public void deletePilotByLicenseNumber(String licenseNumber) {
        pilotDb.deleteByLicenseNumber(licenseNumber);
    }
    
    @Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDb.delete(pilot);
	}

    
    @Override
	public void updatePilot(long pilotId, PilotModel pilotBaru) {
//		PilotModel pilotLama = this.getPilotDetailByLicenseNumber(pilotBaru.getLicenseNumber());
//		pilotLama.setName(pilotBaru.getName());
//		pilotLama.setFlyHour(pilotBaru.getFlyHour());
	}

	@Override
	public Optional<PilotModel> getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public Optional<PilotModel> getPilotDetailById(long id) {
		// TODO Auto-generated method stub
		return pilotDb.findById(id);
	}
}