package com.trungngo.brvtadminservice.controller;

import com.trungngo.brvtadminservice.model.Admin;
import com.trungngo.brvtadminservice.model.Doctor;
import com.trungngo.brvtadminservice.model.Patient;
import com.trungngo.brvtadminservice.service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminServiceInterface adminService;

    @Autowired
    public AdminController(AdminServiceInterface adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/addAccount")
    public ResponseEntity addAccount(@RequestBody Admin admin) {
        Admin newAccount = adminService.createAdmin(admin);
        if (newAccount == null) {
            return new ResponseEntity<>(
                    "Failed to create new admin account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    newAccount,
                    HttpStatus.OK
            );
        }
    }

    @PostMapping("/auth")
    public ResponseEntity verifyAccountPassword(@RequestBody Admin admin) throws IOException {
        Admin verifiedAdmin = adminService.verifyAdminPassword(admin);
        if (verifiedAdmin == null) {
            return new ResponseEntity<>(
                    "Failed to authenticate account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    verifiedAdmin,
                    HttpStatus.OK
            );
        }
    }

    @PostMapping(path="/addPatient")
    public ResponseEntity addPatient(@RequestBody Patient patient) {
        Patient patientAdd = adminService.addPatient(patient);
        if(patientAdd == null) {
            return new ResponseEntity<>(
                    "Failed to add patient.",
                    HttpStatus.BAD_REQUEST
            );
        }
        else {
            return new ResponseEntity<>(
                    patientAdd,
                    HttpStatus.OK
            );
        }
    }

    @PostMapping(path="/addDoctor")
    public ResponseEntity addDoctor(@RequestBody Doctor doctor) {
        Doctor doctorAdd = adminService.addDoctor(doctor);
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

    @DeleteMapping(value = "/deleteAdmin/{id}")
    public String deleteAccount(@PathVariable Integer id){
        return adminService.deleteAdminById(id);
    }

    @DeleteMapping(value = "/deletePatient/{id}")
    public String deletePatient(@PathVariable Integer id){
        return adminService.deletePatientById(id);
    }

    @DeleteMapping(value = "/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable Integer id){
        return adminService.deleteDoctorById(id);
    }

}
