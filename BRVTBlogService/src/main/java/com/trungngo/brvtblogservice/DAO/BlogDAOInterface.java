package com.trungngo.brvtblogservice.DAO;

import com.trungngo.brvtblogservice.model.Blog;

import java.util.List;

public interface BlogDAOInterface {

    List<Blog> findAll();
    Blog save(Blog blog);
    Blog update(Blog blog);
    Blog delete(Integer id);
    Blog findById(Integer id);

}
