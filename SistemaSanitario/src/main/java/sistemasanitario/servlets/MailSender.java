/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author omar
 */
public class MailSender {
    final String host = "smtp.gmail.com";
    final String port = "465";
    final String username = "omarbattan@gmail.com";
    final String passwordAcc = "dbfcdokqbhuspuop";
    
    
    
    void sendEmail(String email, String subject, String message){
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.socketFactory.port", port);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");

        
        Session session = Session.getInstance(props, new Authenticator() {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, passwordAcc);
        }
        });
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject(subject);
            msg.setText(message);
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (MessagingException me) {
            //TODO: log the exception
            me.printStackTrace(System.err);
        }
    }
}
