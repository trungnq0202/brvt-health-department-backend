package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.DAO.DoctorDAOInterface;
import com.trungngo.brvtadminservice.DAO.PatientDAOInterface;
import com.trungngo.brvtadminservice.model.Doctor;
import com.trungngo.brvtadminservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorServiceInterface {
    private final DoctorDAOInterface doctorDAO;
    private final PatientDAOInterface patientDAO;
    @Autowired
    public DoctorServiceImpl(@Qualifier("doctorDAOImpl") DoctorDAOInterface doctorDAO,
                             @Qualifier("patientDAOImpl") PatientDAOInterface patientDAO) {
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @Override
    @Transactional
    public Doctor addDoctor(Doctor doctor) {
        if(doctor.getCareerLevel() != null && doctor.getPatientList() != null)
            return doctorDAO.save(doctor);
        else return null;
    }

    @Override
    @Transactional
    public Doctor findDoctorById(Integer id) {
        return doctorDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Doctor> findAllDoctor() {
        return doctorDAO.findAll();
    }

    @Override
    @Transactional
    public String deleteDoctorById(Integer id) {
        Doctor doctor = findDoctorById(id);
        if(doctor != null) {
            doctorDAO.delete(id);
            return "Doctor deleted.";
        }
        else return "No doctor found to delete.";
    }

    @Override
    @Transactional
    public List<Patient> getPatientListById(Integer id) {
        Doctor doctor = findDoctorById(id);
        if(doctor != null) return doctor.getPatientList();
        else return null;
    }

    @Override
    @Transactional
    public String getPatientStatusById(Integer doctorId, Integer patientId) {
        List<Patient> patientList = getPatientListById(doctorId);
        if(patientList == null) return null;
        for(Patient patient : patientList) {
            if(patient.getId() == patientId) return patient.getStatus();
        }
        return null;
    }
}
