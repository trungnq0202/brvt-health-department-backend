package com.trungngo.brvtpatientservice.service;

import com.trungngo.brvtpatientservice.DAO.PatientDAOInterface;
import com.trungngo.brvtpatientservice.helper.SecurityHelper;
import com.trungngo.brvtpatientservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientServiceInterface {
    private final PatientDAOInterface patientDAO;

    @Autowired
    public PatientServiceImpl(@Qualifier("patientDAOImpl") PatientDAOInterface patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    @Transactional
    public Patient addPatient(Patient patient) {
        if(patient.getStatus() != null && patient.getIllnessLevel() != null)
            return patientDAO.save(patient);
        else return null;
    }

    @Override
    @Transactional
    public Patient findPatientById(Integer id) {
        return patientDAO.findById(id);
    }

    @Override
    @Transactional
    public Patient updateStatusById(Integer id, String status) {
        Patient patient = findPatientById(id);
        if(patient != null) return patientDAO.updateStatus(patient, status);
        else return null;
    }

    @Override
    @Transactional
    public List<Patient> findAllPatient() {
        return patientDAO.findAll();
    }

    @Override
    @Transactional
    public List<Patient> findAllPatientByDoctorId(int doctorId) {
        return patientDAO.findAllByDoctorId(doctorId);
    }

    @Override
    @Transactional
    public String deletePatientById(Integer id) {
        Patient patient = findPatientById(id);
        if(patient != null) {
            patientDAO.delete(id);
            return "Patient deleted.";
        }
        else return "No patient found to delete.";
    }

    @Override
    @Transactional
    public String getStatusById(Integer id) {
        Patient patient = patientDAO.findById(id);
        if(patient != null) return patient.getStatus();
        else return null;
    }

    @Override
    @Transactional
    public Patient updatePatient(Patient patient) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Patient curPatient = findPatientById(patient.getId());
        if (curPatient != null) {
            String password = patient.getPassword();
            patient.setPassword(SecurityHelper.encryptPassword(password));
            return patientDAO.update(patient);
        }
        return null;
    }

    @Override
    @Transactional
    public Patient updateAssignedDoctor(int patientId, int doctorId) {
        return patientDAO.updateAssignedDoctor(patientId, doctorId);
    }

}
