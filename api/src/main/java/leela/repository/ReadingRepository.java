package leela.repository;


import leela.entity.Alert;
import leela.entity.Reading;

import java.util.List;

public interface ReadingRepository {
    public Reading ingestReading(Reading reading);
    public Alert storeAlert(Alert alert);

    public List<Reading> findAll();
}
