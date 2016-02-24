package com.juvenxu.mvnbook.account.persist;

/**
 * Created by zhangbin on 16/2/12.
 */
public interface AccountPersistService {
    Account createAccount(Account account) throws AccountPersistException;

    Account readAccount(String id) throws AccountPersistException;

    Account updateAccount(Account account) throws AccountPersistException;

    void deleteAccount(String id) throws AccountPersistException;
}

