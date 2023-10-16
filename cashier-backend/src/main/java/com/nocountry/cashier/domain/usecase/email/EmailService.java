package com.nocountry.cashier.domain.usecase.email;

import java.io.File;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
public interface EmailService {

    Boolean sendEmail(String[] to, String subject, String textMessage);
    Boolean sendEmailFile(String[] to, String subject, String textMessage, File... attachment);
    void setTemplateStrategy(TemplateStrategy strategy);
    String executeTemplate(String... values);
}
