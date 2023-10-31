package com.nocountry.cashier.domain.service.email;

import com.nocountry.cashier.domain.usecase.email.EmailService;
import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import com.nocountry.cashier.enums.EnumsEmail;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
    @Override
    public EnumsEmail getProviderEmail() {
        return EnumsEmail.RESEND;
    }

    @Override
    public void sendEmail(String[] to, String subject, String textMessage) {
        Resend resend = new Resend("re_token");
        //Boolean send = Boolean.FALSE;
        try {
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .from("@gmail.com")//necesita un domain
                    .to("")
                    .subject("it works!")
                    .html("<strong>hello world</strong>")
                    .build();
            log.info("Email enviado desde SPRING BOOT");
            //send = Boolean.TRUE;

            SendEmailResponse data = resend.emails().send(sendEmailRequest);
            log.info("RESPUESTA DE RESEND {}",data.getId());
        } catch (ResendException e) {
            log.error(e.getMessage());
        }

        //return send;
    }

    @Override
    public Boolean sendEmailFile(String[] to, String subject, String textMessage, Object attachment) {
        return null;
    }

    @Override
    public String generateEmailTemplate(TemplateStrategy templateStrategy, Object... values) {
        return templateStrategy.formatEmailTemplate(values);
    }
}
