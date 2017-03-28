package controllers;

import services.MailService;

public class MailServiceMock extends MailService {

    private String to;

    private String msg;

    @Override
    public void sendMail(String from, String to, String subject, String msg) {
        storeVariables(from, to, subject, msg);
    }

    @Override
    public void sendHtmlMail(String from, String to, String subject, String msg) {
        storeVariables(from, to, subject, msg);
    }

    private void storeVariables(String from, String to, String subject, String msg) {
        this.to = to;
        this.msg = msg;
    }

    public String getTo() {
        return to;
    }

    public String getMsg() {
        return msg;
    }
}
