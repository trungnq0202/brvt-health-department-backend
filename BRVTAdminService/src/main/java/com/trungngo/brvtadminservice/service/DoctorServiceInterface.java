package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.model.Doctor;
import com.trungngo.brvtadminservice.model.Patient;

import java.util.List;

public interface DoctorServiceInterface {
    Doctor addDoctor(Doctor doctor);

    Doctor findDoctorById(Integer id);

    List<Doctor> findAllDoctor();

    String deleteDoctorById(Integer id);

    List<Patient> getPatientListById(Integer id);

    String getPatientStatusById(Integer doctorId, Integer patientId);
}
