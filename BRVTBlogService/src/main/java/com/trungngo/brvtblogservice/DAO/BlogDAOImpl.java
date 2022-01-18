package com.trungngo.brvtblogservice.DAO;

import com.trungngo.brvtblogservice.model.Blog;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Qualifier("blogDAOImpl")
public class BlogDAOImpl implements BlogDAOInterface{

    private final EntityManager entityManager;

    @Autowired
    public BlogDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Blog> findAll() {
        Query query = createQuery("from Blog");
        return query.getResultList();
    }

    @Override
    public Blog save(Blog blog) {
        return  entityManager.merge(blog);
    }

    @Override
    public Blog update(Blog blog) {
        return  entityManager.merge(blog);
    }

    @Override
    public Blog delete(Integer id) {
        Blog blog = findById(id);
        Query query = createQuery("delete from Blog where id=:id").setParameter("id", id);
        query.executeUpdate();
        return blog;
    }

    @Override
    public Blog findById(Integer id) {
        Query query = createQuery("from Blog where id=:id").setParameter("id", id);
        return (Blog) query.uniqueResult();
    }

    private Query createQuery(String stringQuery) {
        return (Query) entityManager.createQuery(stringQuery);
    }

}
