package root.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.entity.Alert;
import root.entity.Reading;
import root.entity.Tires;
import root.entity.Vehicle;
import root.exceptions.ResourceNotFoundException;
import root.repository.ReadingRepository;
import root.repository.VehicleRepository;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public Reading ingestReading(Reading reading) {
        Vehicle vehicle = vehicleRepository.findOne(reading.getVin());
        Tires tires = reading.getTires();

        if(vehicle == null){
            throw new ResourceNotFoundException("Vehicle with vin id: " + reading.getVin() + " does not exist.");
        }
        else{
            if(reading.getEngineRpm() > vehicle.getRedLineRpm()){
                storeAlert(reading, "HIGH","engineRpm > readlineRpm");
            }
            if(reading.getFuelVolume() < 0.1*vehicle.getMaxFuelVolume()){
                storeAlert(reading, "MEDIUM","fuelVolume < 10% of maxFuelVolume");
            }
            if(!(tires.getFrontLeft() >= 32 && tires.getFrontLeft() <= 36) ||
                    !(tires.getFrontRight() >= 32 && tires.getFrontRight() <= 36) ||
                    !(tires.getRearLeft() >= 32 && tires.getRearLeft() <= 36)   ||
                    !(tires.getRearRight() >= 32 && tires.getRearRight() <= 36)){

                storeAlert(reading, "LOW","tire pressure of any tire < 32psi || > 36psi");

            }
            if(reading.isEngineCoolantLow() == true || reading.isCheckEngineLightOn() == true){
                storeAlert(reading, "LOW","engineCoolantLow = true || checkEngineLightOn = true");
            }
        }
        return readingRepository.ingestReading(reading);
    }

    @Transactional(readOnly = true)
    public List<Reading> findAll() {
        return readingRepository.findAll();
    }

    public void storeAlert(Reading reading, String priority, String rule){
        Alert alert = new Alert();
        alert.setPriority(priority);
        alert.setRule(rule);
        alert.setReading_id(reading.getId());
        alert.setVin(reading.getVin());
        readingRepository.storeAlert(alert);
    }
}
