package backend.controller;

import backend.model.Patient;
import backend.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(path = "/redis")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(path = "patients/{id}", method = RequestMethod.GET)
    public Patient getPatientByID(@PathVariable int id){
        return redisService.getPatient(id);
    }

    @RequestMapping(path = "patients/all", method = RequestMethod.GET)
    public Map<String, Patient> getAll(){
        return redisService.getAllPatients();
    }
}
