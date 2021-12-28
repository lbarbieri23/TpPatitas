package com.dds.services.email;

import com.dds.services.email.model.EmailMessage;

public interface IEmailService {

    Boolean enviarMensaje(EmailMessage message) throws EmailException;

}
