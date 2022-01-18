package com.trungngo.brvtadminservice.service;

import com.trungngo.brvtadminservice.DAO.AdminDAOInterface;
import com.trungngo.brvtadminservice.helper.SecurityHelper;
import com.trungngo.brvtadminservice.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class AdminServiceImpl implements AdminServiceInterface{

    private final AdminDAOInterface adminDAO;

    @Autowired
    public AdminServiceImpl(@Qualifier("adminDAOImpl") AdminDAOInterface adminDAO){
        this.adminDAO = adminDAO;
    }

    @Override
    @Transactional
    public Admin findAdminById(Integer id)  {
        return adminDAO.findById(id);
    }

    @Override
    @Transactional
    public Admin findAdminByEmail(String email)  {
        return adminDAO.findByEmail(email);
    }


    @Override
    @Transactional
    public Admin verifyAdminPassword(Admin admin) throws IOException {
        Admin foundAdmin = findAdminByEmail(admin.getEmail());
        if (foundAdmin != null) {
            if (SecurityHelper.verifyPassword(admin.getPassword(), foundAdmin.getPassword())) {
                return foundAdmin;
            }
//            if (admin.getPassword().equals(foundAdmin.getPassword())) {
//                return foundAdmin;
//            }
        }
        return null;
    }

    @Override
    @Transactional
    public Admin createAdmin(Admin admin){
        String email = admin.getEmail();
        String password = admin.getPassword();
        if (email != null && !email.equals("") && password != null && !password.equals("")) {
            admin.setPassword(SecurityHelper.encryptPassword(password));
            return adminDAO.save(admin);
        }
        return null;
    }

    @Override
    @Transactional
    public String deleteAdminById(Integer id) {
        Admin currentAdmin = findAdminById(id);
        if (currentAdmin != null) {
            adminDAO.delete(id);
            return "Deleted admin id " + id;
        } else {
            return "Admin id not found";
        }
    }

}
