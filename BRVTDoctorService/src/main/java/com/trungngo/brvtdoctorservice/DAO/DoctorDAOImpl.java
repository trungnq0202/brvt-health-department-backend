package com.trungngo.brvtdoctorservice.DAO;

import com.trungngo.brvtdoctorservice.model.Doctor;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("doctorDAOImpl")
public class DoctorDAOImpl implements DoctorDAOInterface {
    private final EntityManager entityManager;

    @Autowired
    public DoctorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Doctor> findAll() {
        Query query = createQuery("From Doctor");
        return query.getResultList();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return entityManager.merge(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return entityManager.merge(doctor);
    }

    @Override
    public Doctor delete(Integer id) {
        Doctor doctor = findById(id);
        Query query = createQuery("delete from Doctor where id=:id").setParameter("id", id);
        query.executeUpdate();
        return doctor;
    }

    @Override
    public Doctor findById(Integer id) {
        Query query = createQuery("from Doctor where id=:id").setParameter("id", id);
        return (Doctor) query.uniqueResult();
    }

    @Override
    public Doctor findByEmail(String email) {
        Query query = createQuery("from Doctor where email=:email")
                .setParameter("email", email);
        return (Doctor) query.uniqueResult();
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }
}
