package org.fibi.usos.service.email;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String content);
}
