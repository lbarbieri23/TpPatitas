package com.dds.services.email.model;

import java.util.ArrayList;
import java.util.List;

public class EmailMessage {

    private String fromName;
    private List<String> to;
    private List<String> cc;
    private List<String> cco;
    private List<Attachment> attachments;
    private String subject;
    private String htmlBody;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getCco() {
        return cco;
    }

    public void setCco(List<String> cco) {
        this.cco = cco;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public void agregarAdjunto(Attachment adjunto) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }

        attachments.add(adjunto);
    }

    public void agregarPara(String email) {
        if (this.to == null) {
            this.to  = new ArrayList<>();
        }

        to.add(email);
    }

    public void agregarCopia(String email) {
        if (this.cc == null) {
            this.cc  = new ArrayList<>();
        }

        cc.add(email);
    }

    public void agregarCopiaOculta(String email) {
        if (this.cco == null) {
            this.cco  = new ArrayList<>();
        }

        cco.add(email);
    }
}
