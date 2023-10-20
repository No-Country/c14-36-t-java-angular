package com.nocountry.cashier.domain.usecase.email;

import com.nocountry.cashier.enums.EnumsEmail;

import java.io.File;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
public interface EmailService {

    void sendEmail(String[] to, String subject, String textMessage);
    Boolean sendEmailFile(String[] to, String subject, String textMessage, Object attachment);
    EnumsEmail getProviderEmail();
    String generateEmailTemplate(TemplateStrategy templateStrategy, Object... values);
}
