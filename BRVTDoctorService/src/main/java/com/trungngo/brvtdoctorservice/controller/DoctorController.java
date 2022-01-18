package com.trungngo.brvtdoctorservice.controller;

import com.trungngo.brvtdoctorservice.model.Doctor;
import com.trungngo.brvtdoctorservice.service.DoctorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping(path="/findByEmail/{email}")
    public ResponseEntity findDoctorByEmail(@PathVariable String email) {
        return new ResponseEntity<>(
                doctorService.findDoctorByEmail(email),
                HttpStatus.OK
        );
    }

    @PostMapping("/auth")
    public ResponseEntity verifyAccountPassword(@RequestBody Doctor doctor) throws IOException {
        Doctor verifiedDoctor = doctorService.verifyDoctorPassword(doctor);
        if (verifiedDoctor == null) {
            return new ResponseEntity<>(
                    "Failed to authenticate account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    verifiedDoctor,
                    HttpStatus.OK
            );
        }
    }

}
