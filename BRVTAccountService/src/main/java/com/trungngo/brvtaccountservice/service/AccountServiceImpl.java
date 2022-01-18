package com.trungngo.brvtaccountservice.service;
import com.trungngo.brvtaccountservice.DAO.AccountDAOInterface;
import com.trungngo.brvtaccountservice.helper.SecurityHelper;
import com.trungngo.brvtaccountservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountServiceInterface {

    private final AccountDAOInterface accountDAO;

    @Autowired
    public AccountServiceImpl(@Qualifier("accountDAOImpl") AccountDAOInterface accountDAO){
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        String email = account.getEmail();
        if (email != null && !email.equals("")) {
            List<Account> foundAccounts = accountDAO.findAccountByEmail(email);
            if (foundAccounts.size() == 0) {
                account.setEmail(email.trim());
                String password = account.getPassword();
                if (password != null && !password.equals("")) {
                    account.setPassword(SecurityHelper.encryptPassword(password));
                    return accountDAO.saveAccount(account);
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Account findAccountByUsername(String username)  {
        List<Account> foundAccounts = accountDAO.findAccountByUsername(username);
        if (foundAccounts.size() > 0) {
            return foundAccounts.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Account findAccountByEmail(String email) {
        List<Account> foundAccounts = accountDAO.findAccountByEmail(email);
        if (foundAccounts.size() > 0) {
            return foundAccounts.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts(int startAt, int maxResults) {
        return accountDAO.getAllAccounts(startAt, maxResults);
    }

    @Override
    @Transactional
    public Account updateAccount(Account account)  {
        return accountDAO.saveAccount(account);
    }

    @Override
    @Transactional
    public String deleteAccountById(int id) {
        Account currentAccount = findAccountById(id);
        if (currentAccount != null) {
            accountDAO.deleteAccountById(id);
            return "Deleted account id " + id;
        } else {
            return "Account id not found";
        }
    }

    @Override
    @Transactional
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    @Transactional
    public Account verifyAccountPassword(Account account) {
        Account foundAccount = findAccountById(account.getId());
        if (foundAccount != null) {
            if (SecurityHelper.verifyPassword(account.getPassword(), foundAccount.getPassword())) {
                return foundAccount;
            }
        }
        return null;
    }

}
