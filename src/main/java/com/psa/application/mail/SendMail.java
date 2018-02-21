package com.psa.application.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMail 
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendMail(String to, String cc, String bcc, String subject, String body) throws MessagingException
	{
		String mailSendResult ="Mail Sending Failed";
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		message.setContent("<html><body>"+body+"</body</html>","text/html");
		helper.setBcc(bcc);
		helper.setCc(cc);
		helper.setTo(to);
		
		javaMailSender.send(message);
		mailSendResult ="Mail Sending Successful";
		return mailSendResult;
	}
}
