package com.nocountry.cashier.domain.service.email;

import com.nocountry.cashier.domain.usecase.email.EmailService;
import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import com.nocountry.cashier.enums.EnumsEmail;
import com.nocountry.cashier.enums.EnumsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service.email
 * @license Lrpa, zephyr cygnus
 * @since 17/10/2023
 */
@Service
public class EmailFactoryService {
    private final Map<EnumsEmail, EmailService> emailEmailServiceMap;
    private final Map<EnumsTemplate, TemplateStrategy> strategyTemplate;

    @Autowired
    public EmailFactoryService(Set<EmailService> emailServices, Set<TemplateStrategy> strategyTemplates) {
        this.emailEmailServiceMap = new EnumMap<>(EnumsEmail.class);
        this.strategyTemplate = new EnumMap<>(EnumsTemplate.class);
        emailServices.forEach(emailService -> this.emailEmailServiceMap.put(emailService.getProviderEmail(), emailService));
        strategyTemplates.forEach(templateStrategy -> this.strategyTemplate.put(templateStrategy.getTemplateEmail(), templateStrategy));
    }

    public void generateEmail(EnumsTemplate template, EnumsEmail templateEmail, String[] to,String subject, Object... values) {
        var emailService = this.emailEmailServiceMap.get(templateEmail);
        var templateStrategy = this.strategyTemplate.get(template);
        // ? me traigo el HTML si es de tipo password o de confirmation
        String emailTemplate = emailService.generateEmailTemplate(templateStrategy, values);
        emailService.sendEmail(to, subject, emailTemplate);
    }

}
