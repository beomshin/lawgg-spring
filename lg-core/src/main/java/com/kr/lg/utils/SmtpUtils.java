package com.kr.lg.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@Slf4j
@RequiredArgsConstructor
public class SmtpUtils {


    @Value("${spring.mail.google.host}")
    private String host;

    @Value("${spring.mail.google.username}")
    private String username;

    @Value("${spring.mail.google.password}")
    private String password;

    @Value("${spring.mail.google.port}")
    private int port;

    @Async
    public void postAsync(String receiver, String title, String body) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); // 메일 받는이
            message.setSubject(title); //메일 제목
            message.setContent(body, "text/html;charset=UTF-8"); // 메일 내용
            StopWatch stopWatch = new StopWatch();
            log.debug("이메일 전송 =============>");
            stopWatch.start(); // watch start
            Transport.send(message); ////전송
            stopWatch.stop(); // watch stop
            log.debug("이메일 전송 성공 / 전송 하는 시간 [{}]ms =============> ", stopWatch.getTotalTimeMillis() );
        } catch (Exception e) {
            log.error("이메일 전송 실패 =============>");
            log.error("{}", e);
        }
    }

}
