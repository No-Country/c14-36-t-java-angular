package com.nocountry.cashier.domain.usecase.sms;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service.sms
 * @license Lrpa, zephyr cygnus
 * @since 28/10/2023
 */
public interface SmsService {
    void otpSent(String phoneNumber, String otp);
    void otpVerification(String otp);
}
