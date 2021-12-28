package com.dds.services.email;

import com.dds.services.email.model.Attachment;
import com.dds.services.email.model.EmailMessage;
import com.dds.services.email.model.SmtpConfiguration;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class SmtpEmailService implements IEmailService {

    private SmtpConfiguration mailConfiguration;

    public SmtpEmailService(SmtpConfiguration smtpConfiguration) {
        this.mailConfiguration = smtpConfiguration;
    }


    private Session getMailSession() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.ssl.trust",mailConfiguration.getHost());
        props.setProperty("mail.transport.protocol", mailConfiguration.getProtocol());
        props.setProperty("mail.host", mailConfiguration.getHost());
        props.setProperty("mail.smtp.auth", mailConfiguration.getAuthentication().toString());
        props.setProperty("mail.smtp.port", mailConfiguration.getPort().toString());

        if (mailConfiguration.getStartTls()) {
            props.setProperty("mail.smtp.starttls.enable", "true");
        }
        if (mailConfiguration.getEnableSSL()) {
            props.setProperty("mail.smtp.EnableSSL.enable", "true");
        }

        Session mailSession = null;
        if (mailConfiguration.getAuthentication()) {
            mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailConfiguration.getMail(), mailConfiguration.getPassword());
                }
            });
        } else {
            mailSession = Session.getInstance(props);
        }

        return mailSession;
    }

    @Override
    public Boolean enviarMensaje(EmailMessage emailMessage) throws EmailException {
        Session session = getMailSession();
        session.setDebug(mailConfiguration.getDebugEnabled());
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailConfiguration.getMail()));
            if (emailMessage.getTo() != null && emailMessage.getTo().size() > 0) {
                msg.addRecipients(Message.RecipientType.TO, getAddress(emailMessage.getTo()));
            }
            if (emailMessage.getCc() != null && emailMessage.getCc().size() > 0) {
                msg.addRecipients(Message.RecipientType.CC, getAddress(emailMessage.getCc()));
            }
            if (emailMessage.getCco() != null && emailMessage.getCco().size() > 0) {
                msg.addRecipients(Message.RecipientType.BCC, getAddress(emailMessage.getCco()));
            }
            msg.setSubject(emailMessage.getSubject());

            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(emailMessage.getHtmlBody());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);

            if (emailMessage.getAttachments() != null && emailMessage.getAttachments().size() > 0) {
                for (Iterator<Attachment> it = emailMessage.getAttachments().iterator(); it.hasNext(); ) {
                    Attachment item = it.next();
                    MimeBodyPart att = new MimeBodyPart();
                    ByteArrayDataSource bds = new ByteArrayDataSource(item.getData(), "application/octet-stream");
                    att.setDataHandler(new DataHandler(bds));
                    att.setFileName(item.getFileName());
                    mp.addBodyPart(att);
                }
            }

            msg.setContent(mp);
            Transport.send(msg);
            return true;
        } catch (Exception ex) {
            throw new EmailException("No se pudo enviar el email. " + ex.getMessage());
        }

    }

    private Address[] getAddress(List<String> mails) throws Exception{
        List<Address> results = new ArrayList<>();
        for (Iterator<String> it = mails.iterator(); it.hasNext();) {
            String mail = it.next();
            results.add(new InternetAddress(mail));
        }

        return  results.toArray(new Address[results.size()]);
    }
}
