package root.service;

import org.springframework.web.bind.annotation.PathVariable;
import root.entity.Reading;
import root.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    public List<Vehicle> loadVehicles(List<Vehicle> vehicles);

    public List<Vehicle> getAll();
    public Vehicle findOne(String v_id);

    public void delete(String v_id);
}
