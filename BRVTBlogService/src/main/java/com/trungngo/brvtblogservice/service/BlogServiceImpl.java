package com.trungngo.brvtblogservice.service;

import com.trungngo.brvtblogservice.DAO.BlogDAOInterface;
import com.trungngo.brvtblogservice.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogServiceInterface{

    private final BlogDAOInterface blogDAO;

    @Autowired
    public BlogServiceImpl(@Qualifier("blogDAOImpl") BlogDAOInterface blogDAO) {
        this.blogDAO = blogDAO;
    }

    @Override
    @Transactional
    public Blog addBlog(Blog blog) {
        return blogDAO.save(blog);
    }

    @Override
    @Transactional
    public Blog findBlogById(Integer id) {
        return blogDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Blog> findAll() {
        return blogDAO.findAll();
    }

    @Override
    @Transactional
    public String deleteBlogById(Integer id) {
        Blog foundBlog = findBlogById(id);
        if(foundBlog != null) {
            blogDAO.delete(id);
            return "Blog deleted.";
        }
        else return "No Blog found to delete.";
    }

}
