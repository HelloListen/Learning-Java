package com.juvenxu.mvnbook.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

/**
 * Created by zhangbin on 16/2/12.
 */
public class AccountEmailServiceTest {
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception {
        ServerSetup serverSetup = new ServerSetup(1025,(String)null,"smtp");
        greenMail = new GreenMail(serverSetup);
        greenMail.setUser("348283277@qq.com", "62168267zb");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";

        accountEmailService.sendMail("348283277@qq.com", subject, htmlText);

        greenMail.waitForIncomingEmail(2000, 1);

        Message[] msgs = greenMail.getReceivedMessages();
        Assert.assertEquals(1, msgs.length);
        Assert.assertEquals(subject, msgs[0].getSubject());
        Assert.assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception {
        greenMail.stop();
    }
}
