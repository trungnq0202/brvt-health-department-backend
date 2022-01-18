package com.trungngo.brvtadminservice.DAO;

import com.trungngo.brvtadminservice.model.Admin;

import java.util.List;

public interface AdminDAOInterface {
    List<Admin> findAll();
    Admin save(Admin admin);
    Admin update(Admin admin);
    Admin delete(Integer id);
    Admin findById(Integer id);
}
