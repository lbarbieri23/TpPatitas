package com.dds.mail;

import com.dds.helper.PasswordValidatorTest;
import com.dds.services.email.IEmailService;
import com.dds.services.email.SmtpEmailService;
import com.dds.services.email.model.Attachment;
import com.dds.services.email.model.EmailMessage;
import com.dds.services.email.model.SmtpConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MailTest {
    IEmailService mailService = null;

    @Before
    public void prepareService() {
        Properties p = MailProperties.getProperties();
        String protocol = (String) p.get("mail.protocol");
        String host = (String) p.get("mail.host");
        Boolean authentication = Boolean.parseBoolean((String) p.get("mail.authentication"));
        Integer port = Integer.parseInt((String) p.get("mail.port"));
        String mail = (String) p.get("mail.mail");
        String password = (String) p.get("mail.password");
        Boolean startTls = Boolean.parseBoolean((String) p.get("mail.smtp.starttls.enable"));
        Boolean  enableSSL = Boolean.parseBoolean((String) p.get("mail.smtp.EnableSSL.enable"));

        SmtpConfiguration configuration =new SmtpConfiguration(protocol, host, authentication, port, mail, password, startTls, enableSSL);
        configuration.setDebugEnabled(true);
        mailService = new SmtpEmailService(configuration);
    }


    @Test
    public void sendEmail() throws  Exception {
        EmailMessage message = new EmailMessage();
        message.agregarPara("boyediego@hotmail.com");
        message.setSubject("Prueba");
        message.setHtmlBody("<b>Hola mundo!</b>");
        message.setFromName("Patitas test");
        Boolean enviado = mailService.enviarMensaje(message);
        assertTrue(enviado);
    }

    @Test
    public void sendEmailWithAttach() throws  Exception {
        Attachment attach = new Attachment("test1", Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("top-list-passwords.txt").toURI())));
        EmailMessage message = new EmailMessage();
        message.agregarPara("boyediego@hotmail.com");
        message.setSubject("Prueba de archivo adjunto");
        message.setHtmlBody("<b>Hola mundo!</b>");
        message.setFromName("Patitas test");
        message.agregarAdjunto(attach);
        Boolean enviado = mailService.enviarMensaje(message);
        assertTrue(enviado);
    }


}
