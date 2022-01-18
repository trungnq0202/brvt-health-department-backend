package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.model.Patient;

import java.util.List;

public interface PatientServiceInterface {
    Patient addPatient(Patient patient);

    Patient findPatientById(Integer id);

    Patient updateStatusById(Integer id, String status);

    List<Patient> findAllPatient();

    String deletePatientById(Integer id);

    String getStatusById(Integer id);
}
