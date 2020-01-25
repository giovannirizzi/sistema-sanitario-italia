/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.utils;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sistemasanitario.config.EmailConfig;

/**
 *
 * @author omar
 */
public class MailSender {

    public void sendEmail(String email, String subject, String message){
        
        final EmailConfig emailConfig;
        try {
            emailConfig = EmailConfig.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, "Error initialize email config", ex);
            return;
        }
        
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", emailConfig.getSmtpHost());
        properties.setProperty("mail.smtp.port", Integer.toString(emailConfig.getSmtpPort()));
        properties.setProperty("mail.smtp.socketFactory.port", Integer.toString(emailConfig.getSmtpPort()));
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", Boolean.toString(emailConfig.getSmtpAuth()));
        properties.setProperty("mail.smtp.starttls.enable", Boolean.toString(emailConfig.getSmtpTls()));
        properties.setProperty("mail.debug", Boolean.toString(emailConfig.getDebug()));

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(emailConfig.getUsername()));
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
