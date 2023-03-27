package com.zerobase.cms.user.client.service;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class EmailSendServiceTest {
    @Autowired
    private MailgunClient mailgunClient;

    @Test
    public void EmailTest() {
        SendMailForm form = SendMailForm.builder()
                .from("parkjw573@naver.com")
                .to("parkjw573@naver.com")
                .subject("오류의 연속..")
                .text("어서 썩 물러나라!!").build();
        ResponseEntity<String> response = mailgunClient.sendEmail(form);
        assertEquals("통과!", HttpStatus.OK ,response.getStatusCode());
    }
}
