package com.trungngo.brvtadminservice.DAO;

import com.trungngo.brvtadminservice.model.Doctor;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Qualifier("doctorDAOImpl")
public class DoctorDAOImpl implements DoctorDAOInterface {
    private final EntityManager entityManager;

    @Autowired
    public DoctorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Doctor> findAll() {
        Query query = createQuery("select * from Doctor");
        return query.getResultList();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return entityManager.merge(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return null;
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

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }
}
