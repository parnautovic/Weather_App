package com.example.servis3.service.Impl;

import com.example.servis3.ctrl.MessageReceiver;
import com.example.servis3.dto.MsgDto;
import com.example.servis3.service.MsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MsgHandlerImpl implements MsgHandler {

    private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @Async
    @Override
    public void doSomething(MsgDto msgDto) {
        logger.info(msgDto.getBody());

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String username = "jovana.ivanovic.MM@gmail.com", password = "scorpionsby2344";
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("jovana.ivanovic.MM@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(msgDto.getTo()));
            message.setSubject(msgDto.getTitle());
            String msg = msgDto.getBody();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
