package com.IAM.shoutOut.authorization.model;

public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;

    public NotificationEmail(String subject, String recipient, String body) {
        this.subject = subject;
        this.recipient = recipient;
        this.body = body;
    }
    public NotificationEmail() { }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getRecipient() { return recipient; }

    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }
}
