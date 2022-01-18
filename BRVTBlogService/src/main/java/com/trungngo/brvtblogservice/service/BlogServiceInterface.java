package com.trungngo.brvtblogservice.service;

import com.trungngo.brvtblogservice.model.Blog;

import java.util.List;

public interface BlogServiceInterface {

    Blog addBlog(Blog blog);
    Blog findBlogById(Integer id);
    List<Blog> findAll();
    String deleteBlogById(Integer id);


}
