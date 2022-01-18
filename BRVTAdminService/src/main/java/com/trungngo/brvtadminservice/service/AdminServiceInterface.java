package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.model.Admin;
import com.trungngo.brvtadminservice.model.Doctor;
import com.trungngo.brvtadminservice.model.Patient;

import java.io.IOException;

public interface AdminServiceInterface {

    Admin verifyAdminPassword(Admin admin) throws IOException;

    Admin createAdmin(Admin admin);

    public Admin findAdminById(Integer id);

    String deleteAdminById(Integer id);

    String deleteDoctorById(Integer id);

    String deletePatientById(Integer id);

    Doctor addDoctor(Doctor doctor);

    Patient addPatient(Patient patient);

}
