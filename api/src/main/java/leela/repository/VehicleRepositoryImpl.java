package leela.repository;


import org.springframework.stereotype.Repository;
import leela.entity.Alert;
import leela.entity.Reading;
import leela.entity.Tires;
import leela.entity.Vehicle;
import leela.service.VehicleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Vehicle> loadVehicles(List<Vehicle> vehicles) {
        for(Vehicle vehicle: vehicles){
            if(findOne(vehicle.getVin()) == null){
                em.persist(vehicle);
            }
            else{
                em.merge(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getAll() {
        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.getAll",Vehicle.class);
        return query.getResultList();
    }

    public Vehicle findOne(String v_id) {
        return em.find(Vehicle.class,v_id);
    }

    public void delete(Vehicle v) {
        em.remove(v);
    }


}
