package com.trungngo.brvtpatientservice.DAO;

import com.trungngo.brvtpatientservice.model.Patient;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Qualifier("patientDAOImpl")
public class PatientDAOImpl implements PatientDAOInterface {
    private final EntityManager entityManager;

    @Autowired
    public PatientDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Patient> findAll() {
        Query query = createQuery("from Patient");
        return query.getResultList();
    }

    @Override
    public List<Patient> findAllByDoctorId() {
        return null;
    }

    @Override
    public Patient save(Patient patient) {
        return entityManager.merge(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return entityManager.merge(patient);
    }

    @Override
    public Patient updateStatus(Patient patient, String status) {
        patient.setStatus(status);
        entityManager.merge(patient);
        return patient;
    }

    @Override
    public Patient delete(Integer id) {
        Patient patient = findById(id);
        Query query = createQuery("delete from Patient where id=:id").setParameter("id", id);
        query.executeUpdate();
        return patient;
    }

    @Override
    public Patient findById(Integer id) {
        Query query = createQuery("from Patient where id=:id").setParameter("id", id);
        return (Patient) query.uniqueResult();
    }

    @Override
    public Patient findByEmail(String email) {
        Query query = createQuery("from Patient where email LIKE :email")
                .setParameter("email", email);
        return (Patient) query.uniqueResult();
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public Patient updateAssignedDoctor(int patientId, int doctorId) {
        Patient foundPatient = findById(patientId);
        foundPatient.setDoctorId(doctorId);
        entityManager.merge(foundPatient);
        return foundPatient;
    }
}
