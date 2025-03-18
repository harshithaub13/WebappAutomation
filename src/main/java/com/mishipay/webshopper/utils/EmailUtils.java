package com.mishipay.webshopper.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailUtils {

    public static void sendEmail(String reportFilePath) {
        // SMTP server details
        String host = "smtp.gmail.com"; // Change as per your email provider
        final String user = "harshitha@mishipay.com"; // Sender's email
        final String password = "ptsq pgpd tagy euxn"; // App password for security

        // Receiver's email
        String to = "harshitha@mishipay.com";

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Session for sending email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Read report content to include in the email body
            String reportContent = new String(Files.readAllBytes(Paths.get(reportFilePath)));

            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Automation Test Report");

            // Email body part (HTML content from the report)
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(reportContent, "text/html; charset=UTF-8");

            // Attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(reportFilePath); // Path to your test report
            attachmentPart.setFileName("emailable-report.html");

            // Combine parts
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart); // HTML content in the email body
            multipart.addBodyPart(attachmentPart); // Attachment

            // Set content
            message.setContent(multipart);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully with report in the body and as attachment...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email...");
        }
    }
}
