package com.nocountry.cashier.domain.usecase.email;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase.email
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
public abstract class TemplateStrategy {

    public String buildTemplate() {
        return String.format("%s %s %s",header(),body(),footer());
    }
    public abstract String header();

    public abstract String body();

    public abstract String footer();
    public abstract String replaceParameter(Object... values);

}
