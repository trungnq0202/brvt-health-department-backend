package com.trungngo.brvtadminservice.DAO;

import com.trungngo.brvtadminservice.model.Admin;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("adminDAOImpl")
public class AdminDAOImpl implements AdminDAOInterface{

    private final EntityManager entityManager;

    @Autowired
    public AdminDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Admin> findAll() {
        Query query = createQuery("select * from Admin");
        return query.getResultList();
    }

    @Override
    public Admin save(Admin admin) {
        return entityManager.merge(admin);
    }

    @Override
    public Admin update(Admin admin) {
        return null;
    }

    @Override
    public Admin delete(Integer id) {
        Admin admin = findById(id);
        Query query = createQuery("delete from Admin where id=:id").setParameter("id", id);
        query.executeUpdate();
        return admin;
    }

    @Override
    public Admin findById(Integer id) {
        Query query = createQuery("from Admin where id=:id").setParameter("id", id);
        return (Admin) query.uniqueResult();
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }
}
