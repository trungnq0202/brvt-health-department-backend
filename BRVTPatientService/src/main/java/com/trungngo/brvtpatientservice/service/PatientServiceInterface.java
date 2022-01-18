package com.trungngo.brvtpatientservice.service;

import com.trungngo.brvtpatientservice.model.Patient;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface PatientServiceInterface {
    Patient addPatient(Patient patient);

    Patient findPatientById(Integer id);

    Patient updateStatusById(Integer id, String status);

    List<Patient> findAllPatient();

    List<Patient> findAllPatientByDoctorId(int doctorId);

    String deletePatientById(Integer id);

    String getStatusById(Integer id);

    Patient updatePatient(Patient doctor) throws InvalidKeySpecException, NoSuchAlgorithmException;

    Patient updateAssignedDoctor(int patientId, int doctorId);
}
