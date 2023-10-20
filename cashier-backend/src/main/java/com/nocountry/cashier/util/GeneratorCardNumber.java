package com.nocountry.cashier.util;

import java.security.SecureRandom;

public class GeneratorCardNumber {public static String generate(String bin, int length) {

    // The number of random digits that we need to generate is equal to the
    // total length of the card number minus the start digits given by the
    // user, minus the check digit at the end.
    SecureRandom random = new SecureRandom();//System.currentTimeMillis()
    int randomNumberLength = length - (bin.length() + 1);


    StringBuilder builder = new StringBuilder(bin);
    for (int i = 0; i < randomNumberLength; i++) {
        int digit = random.nextInt(10);
        builder.append(digit);
    }

    int numberVerification = getCheckDigit(builder.toString());
    builder.append(numberVerification);

    return builder.toString();
}

    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }



    public static boolean luhnTest2(String number) {

        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return mod == 0;

    }

    public static String getCode(){
    int codeInt = (int)(Math.random() * 1000);
    String securityCode = String.valueOf(codeInt);
        if(securityCode.length()<3) {
            if (securityCode.length() == 2) securityCode = "0".concat(securityCode);
            if (securityCode.length() == 1) securityCode = "00".concat(securityCode);
        }

    return securityCode;
    }
}
