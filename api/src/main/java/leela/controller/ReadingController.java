package leela.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import leela.entity.Reading;
import leela.entity.Vehicle;
import leela.service.ReadingService;

import java.util.List;

@RestController
public class ReadingController {

    @Autowired
    private ReadingService service;

    @RequestMapping(method = RequestMethod.POST, value = "readings", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
    public Reading ingestReading(@RequestBody Reading reading){
        return service.ingestReading(reading);
    }

    @RequestMapping(method = RequestMethod.GET, value = "readings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findAll(){
        return service.findAll();
    }
}
