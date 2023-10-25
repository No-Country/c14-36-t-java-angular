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
    public static final String RESOURCE_AUTH= "/auth";
    public static final String RESOURCE_REGISTER= "/register";
    public static final String RESOURCE_USER= "/customers";
    public static final String RESOURCE_ACCOUNT= "/accounts";
    public static final String RESOURCE_CARD= "/cards";

    public static final String RESOURCE_IMAGE= "/images";
    public static final String RESOURECE_TRANSACTION= "/transactions";


    private Constant(){
        throw new IllegalStateException("Utility Class should not be instantiated!");
    }
}
