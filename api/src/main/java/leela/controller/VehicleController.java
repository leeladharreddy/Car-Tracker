package leela.controller;


import org.springframework.web.bind.annotation.*;
import leela.entity.Reading;
import leela.entity.Vehicle;
import leela.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    VehicleService service;

    @RequestMapping(method = RequestMethod.PUT, value = "vehicles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
    public List<Vehicle> loadVehicles(@RequestBody List<Vehicle> vehicles){
        return service.loadVehicles(vehicles);
    }

    @RequestMapping(method = RequestMethod.GET, value="vehicles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> getAll(){
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "vehicles/{vin}")
    public Vehicle findOne(@PathVariable("vin") String v_id){
        return service.findOne(v_id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "vehicles/{vin}")
    public void delete(@PathVariable("vin") String v_id){
        service.delete(v_id);
    }


}
