/**
 * 
 */
package com.utec.voting.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author kevin_orellana
 *
 */
public class EmailClient {
	Session mailSession;
 
    public void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }
 
    public MimeMessage draftEmailMessage(String emailBody,String[] toEmails) throws AddressException, MessagingException, UnsupportedEncodingException
    {
        String emailSubject = "Correo confidencial";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        emailMessage.setFrom(new InternetAddress("no_reply@system.com", "NoReply-System Voting"));
        emailMessage.setReplyTo(InternetAddress.parse("no_reply@system.com", false));
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }
 
    public void sendEmail(MimeMessage emailMessage) throws AddressException, MessagingException, UnsupportedEncodingException
    {
    	
        EmailClient javaEmail = new EmailClient();
        javaEmail.setMailServerProperties();
        /**
         * Sender's credentials
         * */
        String fromUser = "ko200596@gmail.com";
        String fromUserEmailPassword = "keboleqfcgwinapf";
 
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Send the mail
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
}