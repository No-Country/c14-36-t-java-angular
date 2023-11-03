package com.nocountry.cashier.domain.service.sms;

import com.nocountry.cashier.domain.consume.connection.RapidApi;
import com.nocountry.cashier.domain.usecase.sms.SmsService;
import com.nocountry.cashier.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service.sms
 * @license Lrpa, zephyr cygnus
 * @since 28/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SmsServiceImpl implements SmsService {

    private final RapidApi rapidApi;
    private final UserRepository userRepository;

    @Override
    public void otpSent(String phoneNumber, String otp) {
        log.info("Sending OTP to {}", phoneNumber);
        rapidApi.setApiConnection(phoneNumber, otp);
    }

    @Override
    public void otpVerification(String otp) {

    }
}
