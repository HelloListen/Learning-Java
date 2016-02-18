package com.juvenxu.mvnbook.account.email;

/**
 * Created by zhangbin on 16/2/12.
 */
public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
