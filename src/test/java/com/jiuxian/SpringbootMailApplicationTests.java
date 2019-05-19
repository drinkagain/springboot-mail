package com.jiuxian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Resource
    private JavaMailSender mailSender;

    @Test
    public void sendMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("857591294@qq.com");
        mailMessage.setTo("857591294@qq.com");
        mailMessage.setSubject("这是一封测试邮件");
        mailMessage.setSentDate(new Date());
        mailMessage.setText("Hello 你收到我的邮件了");
        mailSender.send(mailMessage);
    }

    @Test
    public void sendHtmlMail() throws MessagingException {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("857591294@qq.com");
        mimeMessageHelper.setTo("857591294@qq.com");
        mimeMessageHelper.setSubject("这是一封测试邮件");
        String sb = "<h1>SpirngBoot测试邮件HTML</h1>" +
                "<p style='color:#F00'>你是真的太棒了！</p>" +
                "<p style='text-align:right'>右对齐</p>";
        mimeMessageHelper.setText(sb, true);
        mailSender.send(mimeMailMessage);
    }

    @Test
    public void sendAttachmentMail() throws MessagingException {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("857591294@qq.com");
        mimeMessageHelper.setTo("857591294@qq.com");
        mimeMessageHelper.setSubject("这是一封测试邮件");
        mimeMessageHelper.setText("带附件的邮件");
        //文件路径
        FileSystemResource file = new FileSystemResource(new File("F:\\图库\\haokan.jpg"));
        mimeMessageHelper.addAttachment("mail.png", file);
        mailSender.send(mimeMailMessage);
    }

    @Test
    public void sendInlineMail() throws MessagingException {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("857591294@qq.com");
        mimeMessageHelper.setTo("857591294@qq.com");
        mimeMessageHelper.setSubject("这是一封测试邮件");
        mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
        //文件路径
        FileSystemResource file = new FileSystemResource(new File("F:\\图库\\haokan.jpg"));
        mimeMessageHelper.addInline("picture", file);
        mailSender.send(mimeMailMessage);
    }
}
