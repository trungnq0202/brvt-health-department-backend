package com.trungngo.brvtblogservice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trungngo.brvtblogservice.model.Blog;
import com.trungngo.brvtblogservice.service.BlogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/blog")
public class BlogController {

    private final BlogServiceInterface blogService;

    @Autowired
    public BlogController(BlogServiceInterface blogService) {
        this.blogService = blogService;
    }

    @GetMapping(path="/findAll")
    public ResponseEntity findAllBlog() {
        return new ResponseEntity<>(
                blogService.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping(path="/add")
    public ResponseEntity addBlog(@RequestBody Blog blog) {
        Blog newBlog = blogService.addBlog(blog);
        if(newBlog == null) {
            return new ResponseEntity<>(
                    "Failed to add blog",
                    HttpStatus.BAD_REQUEST
            );
        }
        else {
            return new ResponseEntity<>(
                    newBlog,
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(path="/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id) {
        Blog blog = blogService.findBlogById(id);
        if(blog == null) return new ResponseEntity<>("Blog not found.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteBlog(@PathVariable Integer id){
        return blogService.deleteBlogById(id);
    }

}