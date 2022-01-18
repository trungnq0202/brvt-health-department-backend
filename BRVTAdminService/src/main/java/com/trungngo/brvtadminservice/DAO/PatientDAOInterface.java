package com.trungngo.brvtadminservice.DAO;

import com.trungngo.brvtadminservice.model.Patient;

import java.util.List;

public interface PatientDAOInterface {
    List<Patient> findAll();
    Patient save(Patient patient);
    Patient updateStatus(Patient patient, String status);
    Patient delete(Integer id);
    Patient findById(Integer id);
}
