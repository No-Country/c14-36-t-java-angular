package com.nocountry.cashier.domain.service.email.template;

import com.nocountry.cashier.domain.usecase.email.TemplateStrategy;
import org.springframework.stereotype.Service;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Service
public class TemplateEmailConfirmation extends TemplateStrategy {
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
    public String replaceParameter(Object... values) {
        return String.format(buildTemplate(), values);
    }


}
