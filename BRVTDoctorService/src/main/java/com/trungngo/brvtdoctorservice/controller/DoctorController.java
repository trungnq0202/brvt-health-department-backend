package com.trungngo.brvtdoctorservice.controller;

import com.trungngo.brvtdoctorservice.model.Doctor;
import com.trungngo.brvtdoctorservice.service.DoctorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(path = "/doctor")
public class DoctorController {
    private final DoctorServiceInterface doctorService;

    @Autowired
    public DoctorController(DoctorServiceInterface doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(path="/add")
    public ResponseEntity addDoctor(@RequestBody Doctor doctor) {
        Doctor doctorAdd = doctorService.addDoctor(doctor);
        if(doctorAdd == null) {
            return new ResponseEntity<>(
                    "Failed to add doctor.",
                    HttpStatus.BAD_REQUEST
            );
        }
        else {
            return new ResponseEntity<>(
                    doctorAdd,
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(path="/{id}")
    public ResponseEntity getDoctorById(@PathVariable Integer id) {
        Doctor doctor = doctorService.findDoctorById(id);
        if(doctor == null) return new ResponseEntity<>("Doctor not found.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping(path="/findAll")
    public ResponseEntity findAllDoctors() {
        return new ResponseEntity<>(
                doctorService.findAllDoctor(),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "")
    public ResponseEntity updateDoctor(@RequestBody Doctor doctor) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Doctor updatedDoctor = doctorService.updateDoctor(doctor);
        if (updatedDoctor == null) {
            return new ResponseEntity<>(
                    "Failed to update account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    updatedDoctor,
                    HttpStatus.OK
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteDoctorById(@PathVariable Integer id){
        return doctorService.deleteDoctorById(id);
    }


//    @GetMapping(path="/{id}/getPatientList")
//    public ResponseEntity getPatientListById(@PathVariable Integer id) {
//        List<Patient> patientList = doctorService.getPatientListById(id);
//        if(patientList == null) return new ResponseEntity<>("Doctor not found.", HttpStatus.BAD_REQUEST);
//        else return new ResponseEntity<>(patientList, HttpStatus.OK);
//    }

//    @GetMapping(path="/{doctorId}/getPatientStatus/{patientId}")
//    public ResponseEntity getPatientListById(@PathVariable Integer doctorId, @PathVariable Integer patientId) {
//        String status = doctorService.getPatientStatusById(doctorId, patientId);
//        if(status == null) return new ResponseEntity<>("Doctor not found or this doctor is not in charge of this patient.", HttpStatus.BAD_REQUEST);
//        else return new ResponseEntity<>(status, HttpStatus.OK);
//    }

}
