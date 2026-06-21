package com.bank.util;

public class ValidationUtil {

    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.matches("\\d{12}");
    }

    public static boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4}");
    }

    public static boolean isValidPassword(String password) {
        return password != null
                && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$");
    }

    public static boolean isValidContact(String contact) {
        if (contact == null || contact.trim().isEmpty()) {
            return false;
        }

        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phonePattern = "^[6-9]\\d{9}$";

        return contact.matches(emailPattern) || contact.matches(phonePattern);
    }

    public static boolean isValidAtmCardNumber(String atmCardNumber) {
        return atmCardNumber != null && atmCardNumber.matches("\\d{12,19}");
    }
}