package com.trungngo.brvtdoctorservice.service;

import com.trungngo.brvtdoctorservice.model.Doctor;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface DoctorServiceInterface {
    Doctor addDoctor(Doctor doctor);

    Doctor findDoctorById(Integer id);

    List<Doctor> findAllDoctor();

    String deleteDoctorById(Integer id);

    Doctor updateDoctor(Doctor doctor) ;

    Doctor findDoctorByEmail(String email);

    Doctor verifyDoctorPassword(Doctor doctor) throws IOException;


//    List<Patient> getPatientListById(Integer id);

//    String getPatientStatusById(Integer doctorId, Integer patientId);
}
