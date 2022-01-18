package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.DAO.AdminDAOInterface;
import com.trungngo.brvtadminservice.DAO.DoctorDAOInterface;
import com.trungngo.brvtadminservice.DAO.PatientDAOInterface;
import com.trungngo.brvtadminservice.model.Admin;
import com.trungngo.brvtadminservice.model.Doctor;
import com.trungngo.brvtadminservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class AdminServiceImpl implements AdminServiceInterface{

    private final AdminDAOInterface adminDAO;
    private final PatientDAOInterface patientDAO;
    private final DoctorDAOInterface doctorDAO;

    @Autowired
    public AdminServiceImpl(@Qualifier("adminDAOImpl") AdminDAOInterface adminDAO,
                            @Qualifier("doctorDAOImpl") DoctorDAOInterface doctorDAO,
                            @Qualifier("patientDAOImpl") PatientDAOInterface patientDAO){
        this.adminDAO = adminDAO;
        this.patientDAO = patientDAO;
        this.doctorDAO = doctorDAO;
    }

    @Override
    @Transactional
    public Admin findAdminById(Integer id)  {
        return adminDAO.findById(id);
    }

    @Override
    @Transactional
    public Admin verifyAdminPassword(Admin admin) throws IOException {
        Admin foundAdmin = findAdminById(admin.getId());
        if (foundAdmin != null) {
//            if (SecurityHelper.verifyPassword(admin.getPassword(), foundAdmin.getPassword())) {
//                return foundAdmin;
//            }
            if (admin.getPassword().equals(foundAdmin.getPassword())) {
                return foundAdmin;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Admin createAdmin(Admin admin){
        String username = admin.getUsername();
        String password = admin.getPassword();
        if (username != null && !username.equals("") && password != null && !password.equals("")) {
            return adminDAO.save(admin);
        }
        return null;
    }

    @Override
    @Transactional
    public String deleteAdminById(Integer id) {
        Admin currentAdmin = findAdminById(id);
        if (currentAdmin != null) {
            adminDAO.delete(id);
            return "Deleted admin id " + id;
        } else {
            return "Admin id not found";
        }
    }

    @Override
    public String deleteDoctorById(Integer id) {
        Doctor doctor = doctorDAO.findById(id);
        if(doctor != null) {
            doctorDAO.delete(id);
            return "Doctor deleted.";
        }
        else return "No doctor found to delete.";
    }

    @Override
    public String deletePatientById(Integer id) {
        Patient patient = patientDAO.findById(id);
        if(patient != null) {
            patientDAO.delete(id);
            return "Patient deleted.";
        }
        else return "No patient found to delete.";
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorDAO.save(doctor);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientDAO.save(patient);
    }

}
