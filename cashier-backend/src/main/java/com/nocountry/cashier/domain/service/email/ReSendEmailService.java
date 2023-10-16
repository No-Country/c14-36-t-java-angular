package com.nocountry.cashier.domain.service.email;

import com.nocountry.cashier.domain.usecase.email.EmailService;
import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReSendEmailService implements EmailService {
    private final JavaMailSender javaMailSender;
    private TemplateStrategy templateStrategy;


    @Override
    public Boolean sendEmail(String[] to, String subject, String textMessage) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Boolean send = Boolean.FALSE;
        try {
            helper.setTo(to);
            helper.setText(textMessage,true);
            helper.setSubject(subject);
            javaMailSender.send(message);
            send= Boolean.TRUE;
        } catch (MessagingException e){
            log.error("Hubo un error al enviar el mail: {}", e.getCause().getMessage());
        }

        return send;
    }

    @Override
    public Boolean sendEmailFile(String[] to, String subject, String textMessage, File... attachment) {
        return null;
    }

    @Override
    public void setTemplateStrategy(TemplateStrategy strategy) {
        this.templateStrategy= strategy;
    }

    @Override
    public String executeTemplate(String... values) {
        String logoutUrl = UriComponentsBuilder
                .fromHttpUrl("issuer" + "v2/logout?client_id={clientId}&returnTo={returnTo}")
                .encode()
                .buildAndExpand("clientId", "returnTo")
                .toUriString();
        return this.templateStrategy.replaceParameter(values);
    }
}
