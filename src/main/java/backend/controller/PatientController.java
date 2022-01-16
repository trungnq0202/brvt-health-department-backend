package backend.controller;

import backend.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import backend.model.Patient;
import backend.service.PatientService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(path = "patients", method = RequestMethod.POST)
    public Patient addPatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @RequestMapping(path = "patients/validate/{name}/{password}", method = RequestMethod.GET)
    public String validatePatient(@PathVariable String name, @PathVariable String password){
        return patientService.validate(name, password);
    }

    @RequestMapping(path = "patients/all", method = RequestMethod.GET)
    public List<Patient> getAll(){
        return patientService.getAllPatients();
    }

    @RequestMapping(path = "patients/{id}", method = RequestMethod.GET)
    public Patient getPatientByID(@PathVariable int id) {
        Patient patient = redisService.getPatient(id);
        if (patient == null) {
            return patientService.getPatient(id);
        }
        else {
            return patientService.getPatient(patient.getID());
        }
    }

    @RequestMapping(path = "patients", method = RequestMethod.PUT)
    public Patient updatePatient(@RequestBody Patient patient){
        redisService.savePatient(patient);
        Patient newPatient = redisService.getPatient(patient.getID());
        return patientService.savePatient(newPatient);
    }

    @RequestMapping(path = "patients/{id}", method = RequestMethod.DELETE)
    public long deletePatient(@PathVariable long id){
        long deleteId = redisService.deletePatient(id);
        return patientService.deletePatient(deleteId);
    }
}

