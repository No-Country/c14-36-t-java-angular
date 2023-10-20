package com.nocountry.cashier.domain.service.email;

import com.nocountry.cashier.domain.usecase.email.EmailService;
import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import com.nocountry.cashier.enums.EnumsEmail;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GmailEmailService implements EmailService {
    private final JavaMailSender javaMailSender;
    @Value("${gmail.sender}")
    private String sender;

    @Value("${path.directory.file_email}")
    private String filePath;
    @Override
    public EnumsEmail getProviderEmail() {
        return EnumsEmail.GMAIL;
    }

    @Override
    @Async(value = "asyncTaskExecutor")
    public void sendEmail(String[] to, String subject, String textMessage) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message);
        try {
            mail.setFrom(new InternetAddress(sender));
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(textMessage, true);
            javaMailSender.send(message);
            log.info("Email enviado desde SPRING BOOT");
        } catch (MessagingException e) {
            log.error("Hubo un error al enviar el mail: {}", e.getCause().getMessage());
        }
    }

    @Override
    public Boolean sendEmailFile(String[] to, String subject, String textMessage, Object attachment) {
        Boolean send = Boolean.FALSE;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mail = new MimeMessageHelper(message);
            File file = getFile((MultipartFile) attachment);

            mail.setFrom(new InternetAddress(sender,"Cashier"));
            mail.setTo(to);
            mail.addAttachment(file.getName(),file);
            mail.setSubject(subject);
            mail.setText(textMessage, true);
            javaMailSender.send(message);
            log.info("Email enviado desde SPRING BOOT");
            send = Boolean.TRUE;
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Hubo un error al enviar el mail: {}", e.getCause().getMessage());
        }
        return send;
    }

    private File getFile(MultipartFile file){

        try(InputStream inputStream= file.getInputStream()){
            String filename= Objects.requireNonNull(file.getOriginalFilename()).replace(" ","").strip();
            Path path = Paths.get(filePath).resolve(filename).toAbsolutePath();
            // * se crea el directorio si no existe
            Files.createDirectories(path);
            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);
            return path.toFile();
        } catch (IOException e) {
            log.error("No se pudo copiar guardar el archivo {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }
    @Override
    public String generateEmailTemplate(TemplateStrategy templateStrategy, Object... values) {
         return templateStrategy.formatEmailTemplate(values);
    }
}
