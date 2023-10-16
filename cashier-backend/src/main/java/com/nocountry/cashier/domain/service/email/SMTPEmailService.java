package com.nocountry.cashier.domain.service.email;

import com.nocountry.cashier.domain.usecase.email.EmailService;
import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import lombok.extern.slf4j.Slf4j;
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
public class SMTPEmailService implements EmailService {
    @Override
    public Boolean sendEmail(String[] to, String subject, String textMessage) {
        return null;
    }

    @Override
    public Boolean sendEmailFile(String[] to, String subject, String textMessage, File... attachment) {
        return null;
    }

    @Override
    public void setTemplateStrategy(TemplateStrategy strategy) {

    }

    @Override
    public String executeTemplate(String... values) {
        return null;
    }
}
