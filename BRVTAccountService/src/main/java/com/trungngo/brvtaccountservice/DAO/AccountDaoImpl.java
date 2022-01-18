package com.trungngo.brvtaccountservice.DAO;

import com.trungngo.brvtaccountservice.model.Account;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Qualifier("accountDAOImpl")
public class AccountDaoImpl implements AccountDAOInterface {

    private final EntityManager entityManager;

    @Autowired
    public AccountDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

    @Override
    public List<Account> getAllAccounts(int startAt, int maxResults){
        Query query = createQuery("FROM Account ORDER BY id")
                .setFirstResult(startAt)
                .setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public Account findAccountById(int id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> findAccountByUsername(String username) {
        Query query = createQuery("from Account where username LIKE :username")
                .setParameter("username", username);
        return query.getResultList();
    }
    @Override

    public List<Account> findAccountByEmail(String email) {
        Query query = createQuery("from Account where email LIKE :email")
                .setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public Account saveAccount(Account account) {
        return entityManager.merge(account);
    }

    @Override
    public void deleteAccountById(int id) {
        Query query = createQuery("delete from Account where id=:id").setParameter("id", id);
        query.executeUpdate();
    }
}
