/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author xuanluan
 */
public class StringVerify {

    public static boolean isValidEmail(String text) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(EMAIL_PATTERN).matcher(text).matches();
    }

    public static boolean isValidPhoneNumber(String text) {
        String PHONE_NUMBER_PATTERN = "^(0\\d{9})|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return Pattern.compile(PHONE_NUMBER_PATTERN).matcher(text).matches();
    }

    public static boolean isValidCMND(String text) {
        String CMND_PATTERN = "[0-9]{9}";
        return Pattern.compile(CMND_PATTERN).matcher(text).matches();
    }
}
