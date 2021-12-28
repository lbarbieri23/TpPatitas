package com.dds.services.email.model;

import java.util.List;

public class SmtpConfiguration {
    private String protocol;
    private String host;
    private Boolean authentication;
    private Integer port;
    private String mail;
    private String password;
    private Boolean startTls;
    private Boolean enableSSL;
    private Boolean debugEnabled = false;

    public SmtpConfiguration(String protocol, String host, Boolean authentication, Integer port, String mail, String password, Boolean startTls, Boolean enableSSL) {
        this.protocol = protocol;
        this.host = host;
        this.authentication = authentication;
        this.port = port;
        this.mail = mail;
        this.password = password;
        this.startTls = startTls;
        this.enableSSL = enableSSL;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStartTls() {
        return startTls;
    }

    public void setStartTls(Boolean startTls) {
        this.startTls = startTls;
    }

    public Boolean getEnableSSL() {
        return enableSSL;
    }

    public void setEnableSSL(Boolean enableSSL) {
        this.enableSSL = enableSSL;
    }

    public Boolean getDebugEnabled() {
        return debugEnabled;
    }

    public void setDebugEnabled(Boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }
}
