package com.trungngo.brvthealthreport.DAO;

import com.trungngo.brvthealthreport.model.HealthReport;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("healthReportDAOImpl")
public class HealthReportDAOImpl implements HealthReportDAOInterface{

    private final EntityManager entityManager;

    @Autowired
    public HealthReportDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<HealthReport> findAll() {
        Query query = createQuery("from HealthReport");
        return query.getResultList();
    }

    @Override
    public List<HealthReport> findAllByPatientId(Integer id) {
        Query query = createQuery("from HealthReport where patient_id=:id").setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<HealthReport> findByStatus(String status) {
        Query query = createQuery("from HealthReport where status=:status").setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public HealthReport save(HealthReport healthReport) {
        return entityManager.merge(healthReport);
    }

    @Override
    public HealthReport update(HealthReport healthReport) {
        return entityManager.merge(healthReport);
    }

    @Override
    public HealthReport delete(Integer id) {
        HealthReport healthReport = findById(id);
        Query query = createQuery("delete from HealthReport where id=:id").setParameter("id", id);
        query.executeUpdate();
        return healthReport;
    }

    @Override
    public HealthReport findById(Integer id) {
        Query query = createQuery("from HealthReport where id=:id").setParameter("id", id);
        return (HealthReport) query.uniqueResult();
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

}
