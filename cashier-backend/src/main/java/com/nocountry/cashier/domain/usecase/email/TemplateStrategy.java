package com.nocountry.cashier.domain.usecase.email;

import com.nocountry.cashier.enums.EnumsTemplate;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */

public abstract class TemplateStrategy {

    public String buildTemplate() {
        return header()+body()+footer();
    }
    public abstract String header();

    public abstract String body();

    public abstract String footer();
    public abstract EnumsTemplate getTemplateEmail();
    public abstract String formatEmailTemplate(Object... values);

}
