package hu.siraknorbert.homeproject.helper;

import hu.siraknorbert.homeproject.constant.ConfigKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSenderHelper {

    @Value(ConfigKeyConstants.Keys.SPRING_MAIL_USERNAME)
    private String systemEmail;

    private final JavaMailSender mailSender;

    public void sendEmail(String sendToEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(systemEmail);
        message.setTo(sendToEmail);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
