package com.codurance.dip;

public class ConsoleEmailSender implements EmailSender {
    @Override
    public void send(Email email) {
        System.out.print(
                "To:" + email.getTo()
                        + ", Subject: " + email.getSubject()
                        + ", Message: " + email.getMessage()
        );
    }
}