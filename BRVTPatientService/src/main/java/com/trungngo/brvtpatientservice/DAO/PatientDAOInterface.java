package com.trungngo.brvtpatientservice.DAO;

import com.trungngo.brvtpatientservice.model.Patient;

import java.util.List;

public interface PatientDAOInterface {
    List<Patient> findAll();
    List<Patient> findAllByDoctorId();
    Patient save(Patient patient);
    Patient update(Patient patient);
    Patient updateStatus(Patient patient, String status);
    Patient delete(Integer id);
    Patient findById(Integer id);
    Patient findByEmail(String email);
    Patient updateAssignedDoctor(int patientId, int doctorId);
}
