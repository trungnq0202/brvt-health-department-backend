package com.trungngo.brvtadminservice.controller;

import com.trungngo.brvtadminservice.model.Admin;
import com.trungngo.brvtadminservice.service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminServiceInterface adminService;

    @Autowired
    public AdminController(AdminServiceInterface adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addAccount(@RequestBody Admin admin) {
        Admin newAccount = adminService.createAdmin(admin);
        if (newAccount == null) {
            return new ResponseEntity<>(
                    "Failed to create new admin account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    newAccount,
                    HttpStatus.OK
            );
        }
    }

    @PostMapping("/auth")
    public ResponseEntity verifyAccountPassword(@RequestBody Admin admin) throws IOException {
        Admin verifiedAdmin = adminService.verifyAdminPassword(admin);
        if (verifiedAdmin == null) {
            return new ResponseEntity<>(
                    "Failed to authenticate account",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    verifiedAdmin,
                    HttpStatus.OK
            );
        }
    }

    @DeleteMapping(value = "/deleteAdmin/{id}")
    public String deleteAccount(@PathVariable Integer id){
        return adminService.deleteAdminById(id);
    }

}
