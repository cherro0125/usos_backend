package org.fibi.usos.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderStandardService implements EmailSenderService {

    private JavaMailSender javaMailSender;
    private static Logger logger = LoggerFactory.getLogger(EmailSenderStandardService.class);

    public EmailSenderStandardService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("usos@fibi.com");
            helper.setFrom("usos@fibi.com");
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            logger.trace(e.getLocalizedMessage(),e);
        }
        javaMailSender.send(mail);
    }
}
