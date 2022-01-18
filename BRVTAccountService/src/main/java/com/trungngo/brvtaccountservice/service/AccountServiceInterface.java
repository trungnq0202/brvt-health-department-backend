package com.trungngo.brvtaccountservice.service;

import com.trungngo.brvtaccountservice.model.Account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface AccountServiceInterface {

    Account createAccount(Account account) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Account findAccountByUsername(String username);

    Account findAccountByEmail(String email);

    List<Account> getAllAccounts(int startAt, int maxResults);

    Account findAccountById(int id);

    Account updateAccount(Account account) throws InvalidKeySpecException, NoSuchAlgorithmException;

    String deleteAccountById(int id);

    Account verifyAccountPassword(Account account) throws IOException;

}
