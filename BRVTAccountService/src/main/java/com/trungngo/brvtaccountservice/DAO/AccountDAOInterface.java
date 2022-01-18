package com.trungngo.brvtaccountservice.DAO;

import com.trungngo.brvtaccountservice.model.Account;

import java.util.List;

public interface AccountDAOInterface {

    List<Account> getAllAccounts(int startAt, int maxResults);

    Account findAccountById(int id);

    List<Account> findAccountByUsername(String username);

    List<Account> findAccountByEmail(String email);

    Account saveAccount(Account account);

    void deleteAccountById(int id);

}
