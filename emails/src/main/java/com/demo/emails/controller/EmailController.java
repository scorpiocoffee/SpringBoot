package com.demo.emails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.receiver}")
    private String receiver;

    @RequestMapping("sendEmail")
    public String sendEmail() {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(receiver);
            msg.setSubject("This is test Email by Spring Boot");
            msg.setText("This is body");
            jms.send(msg);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendHtmlEmail")
    public String sendHtmlEmail() {
        try {
            MimeMessage msg = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom(from);
            helper.setTo(receiver);
            helper.setSubject("An email with HTML formatter");
            StringBuffer sb = new StringBuffer("<p style='color:#6db33f'>Use spring boot to send html email. </p>");
            helper.setText(sb.toString(), true);
            jms.send(msg);
            return "HTML Success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("sendEmailWithAttachment")
    public String sendEmailWithAttachment() {
        try {
            MimeMessage msg = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom(from);
            helper.setTo(receiver);
            helper.setSubject("An Email with attachment.");
            helper.setText("This is body");
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/file/test.txt"));
            helper.addAttachment("Test Attachment", file);
            jms.send(msg);
            return "Success with Attachment";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
