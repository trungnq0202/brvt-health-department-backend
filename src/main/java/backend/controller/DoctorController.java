package backend.controller;

import backend.model.Doctor;
import backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(path = "doctors/{id}", method = RequestMethod.GET)
    public Doctor getDoctorByID(@PathVariable int id){
        return doctorService.getDoctor(id);
    }

    @RequestMapping(path = "doctors/validate/{name}/{password}", method = RequestMethod.GET)
    public String validateDoctor(@PathVariable String name, @PathVariable String password){
        return doctorService.validate(name, password);
    }

    @RequestMapping(path = "doctors/all", method = RequestMethod.GET)
    public List<Doctor> getAll(){
        return doctorService.getAllDoctors();
    }

    @RequestMapping(path = "doctors", method = RequestMethod.POST)
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorService.saveDoctor(doctor);
    }

    @RequestMapping(path = "doctors", method = RequestMethod.PUT)
    public Doctor updateDoctor(@RequestBody Doctor doctor){
        return doctorService.saveDoctor(doctor);
    }

    @RequestMapping(path = "doctors/{id}", method = RequestMethod.DELETE)
    public long deleteDoctor(@PathVariable long id){
        return doctorService.deleteDoctor(id);
    }
}