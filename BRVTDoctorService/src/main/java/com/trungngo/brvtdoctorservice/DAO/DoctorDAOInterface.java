package com.trungngo.brvtdoctorservice.DAO;

import com.trungngo.brvtdoctorservice.model.Doctor;

import java.util.List;

public interface DoctorDAOInterface {
    List<Doctor> findAll();
    Doctor save(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor delete(Integer id);
    Doctor findById(Integer id);
    Doctor findByEmail(String email);
}
