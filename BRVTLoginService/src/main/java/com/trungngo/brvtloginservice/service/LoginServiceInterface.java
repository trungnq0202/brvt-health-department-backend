package com.trungngo.brvtloginservice.service;

import com.trungngo.brvtloginservice.model.Account;

import java.io.IOException;

public interface LoginServiceInterface {

    Account verifyAccountEmail() throws IOException;


}
