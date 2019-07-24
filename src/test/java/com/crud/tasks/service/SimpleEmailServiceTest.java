package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;


    @Mock
    private JavaMailSender javaMailSender;


    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "test@test.com", "Test", "Test message");

/*
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessagePreparator createMessage
            return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo("test@test.com");
            mimeMessageHelper.setSubject("Test");
            mimeMessageHelper.setText("Test message");
            };*/

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if (!mail.getToCc().equals("")) {
            mailMessage.setCc(mail.getToCc());
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());


        //When

        simpleEmailService.send(new Mail("test@test.com", "test@test.com", "Test", "Test message"));

        //Then

        verify(javaMailSender, times(1)).send(mailMessage);
    }





}
