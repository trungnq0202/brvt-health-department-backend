package com.trungngo.brvtdoctorservice.service;

import com.trungngo.brvtdoctorservice.DAO.DoctorDAOInterface;
import com.trungngo.brvtdoctorservice.helper.SecurityHelper;
import com.trungngo.brvtdoctorservice.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorServiceInterface {
    private final DoctorDAOInterface doctorDAO;

    @Autowired
    public DoctorServiceImpl(@Qualifier("doctorDAOImpl") DoctorDAOInterface doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    @Transactional
    public Doctor addDoctor(Doctor doctor) {
        String email = doctor.getEmail();
        Doctor existDoc = doctorDAO.findByEmail(email);
        if (existDoc == null) {
            String password = doctor.getPassword();
            doctor.setPassword(SecurityHelper.encryptPassword(password));
            return doctorDAO.save(doctor);
        }
        return null;
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
    public Doctor updateDoctor(Doctor doctor) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Doctor curDoctor = findDoctorById(doctor.getId());
        if (curDoctor != null) {
            String password = doctor.getPassword();
            doctor.setPassword(SecurityHelper.encryptPassword(password));
            return doctorDAO.update(doctor);
        }
        return null;
    }

}
