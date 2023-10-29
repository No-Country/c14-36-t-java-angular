package com.nocountry.cashier.domain.service.email.template;

import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import com.nocountry.cashier.enums.EnumsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Component
public class TemplatePasswordRecovery extends TemplateStrategy {
    @Override
    public String header() {
        return null;
    }

    @Override
    public String body() {
        return null;
    }

    @Override
    public String footer() {
        return null;
    }

    @Override
    public EnumsTemplate getTemplateEmail() {
        return EnumsTemplate.RESTORE_PASSWORD;
    }

    @Override
    public String formatEmailTemplate(Object... values) {
        return String.format(buildTemplate(), values);
    }
}
