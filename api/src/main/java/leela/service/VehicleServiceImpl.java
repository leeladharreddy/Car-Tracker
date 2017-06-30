package root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.entity.Alert;
import root.entity.Reading;
import root.entity.Tires;
import root.entity.Vehicle;
import root.exceptions.ResourceNotFoundException;
import root.repository.VehicleRepository;
import root.service.VehicleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Transactional
    public List<Vehicle> loadVehicles(List<Vehicle> vehicles) {
        return repository.loadVehicles(vehicles);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAll() {
        return repository.getAll();
    }

    @Transactional(readOnly = true)
    public Vehicle findOne(String v_id) {
        Vehicle v = repository.findOne(v_id);
        if(v == null){
            throw new ResourceNotFoundException("Vehicle with vin "+v_id+" does not exist");
        }
        return v;
    }

    @Transactional
    public void delete(String v_id) {
        Vehicle v = repository.findOne(v_id);
        if(v == null){
            throw new ResourceNotFoundException("Vehicle with vin: "+ v_id + " does not exist");
        }
        repository.delete(v);
    }


}
