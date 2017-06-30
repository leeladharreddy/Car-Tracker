package root.service;


import root.entity.Reading;

import java.util.List;

public interface ReadingService {

    public Reading ingestReading(Reading reading);

    public List<Reading> findAll();
}
