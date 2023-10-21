package com.nocountry.cashier.util;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.util
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
public final class Constant {
    // API VERSION
    public static final String API_VERSION = "v1/api";
    public static final String RESOURCE_USER = "/customers";
    public static final String RESOURCE_AUTH_CONFIRM = "/confirm?token=";
    public static final String RESOURCE_AUTH = "/login";
    public static final String RESOURCE_IMAGE = "/images";

    // TODO tamaño del QR
    public static final int WIDTH_QR = 1230;
    public static final int HEIGHT_QR = 1230;

    private Constant() {
        throw new IllegalStateException("Utility Class should not be instantiated!");
    }
}
