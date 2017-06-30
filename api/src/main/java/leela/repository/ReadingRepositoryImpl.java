package leela.repository;


import org.springframework.stereotype.Repository;
import leela.entity.Alert;
import leela.entity.Reading;
import leela.entity.Tires;
import leela.entity.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository{

    @PersistenceContext
    private EntityManager em;

    public Reading ingestReading(Reading reading) {

        Tires tires = new Tires();

        tires.setFrontLeft(reading.getTires().getFrontLeft());
        tires.setFrontRight(reading.getTires().getFrontRight());
        tires.setRearLeft(reading.getTires().getRearLeft());
        tires.setRearRight(reading.getTires().getRearRight());

        em.persist(tires);
        reading.setTires(tires);
        em.persist(reading);

        return reading;
    }

    public Alert storeAlert(Alert alert) {
        em.persist(alert);
        return alert;
    }

    public List<Reading> findAll() {
        TypedQuery<Reading> query = em.createNamedQuery("Reading.getAll",Reading.class);
        return query.getResultList();
    }

}
