package com.trungngo.brvtloginservice.controller;

import com.trungngo.brvtloginservice.model.Account;
import com.trungngo.brvtloginservice.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/login/")
public class LoginController {

    private final LoginServiceInterface loginService;

    @Autowired
    public LoginController(LoginServiceInterface accountService){
        this.loginService = accountService;
    }

    @GetMapping("/email")
    public RedirectView verifyAccountEmail() throws IOException {
        Account verifiedAccount = loginService.verifyAccountEmail();
        RedirectView redirectView = new RedirectView();
        if (verifiedAccount == null) {
            redirectView.setUrl("http://localhost:3000/fail");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            redirectView.setUrl("http://localhost:3000/success/" + timestamp);
        }
        return redirectView;
    }

}
