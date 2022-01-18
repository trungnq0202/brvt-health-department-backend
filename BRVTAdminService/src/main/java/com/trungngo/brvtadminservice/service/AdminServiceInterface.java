package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.model.Admin;

import java.io.IOException;

public interface AdminServiceInterface {

    Admin verifyAdminPassword(Admin admin) throws IOException;

    Admin createAdmin(Admin admin);

    public Admin findAdminById(Integer id);

    String deleteAdminById(Integer id);


    public Admin findAdminByEmail(String email);


}
