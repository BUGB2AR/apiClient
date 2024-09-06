package com.providertechapi.cliente.domain.validation;

import java.util.regex.Pattern;

public class CpfValidator {

    private static final Pattern REPEATED_CPF_PATTERN = Pattern.compile("^(\\d)\\1{10}$");

    public static boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (REPEATED_CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }

        if (cpf.length() != 11) {
            return false;
        }

        int firstDigit = calculateDigit(cpf, 10);
        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit) {
            return false;
        }

        int secondDigit = calculateDigit(cpf, 11);
        return Character.getNumericValue(cpf.charAt(10)) == secondDigit;
    }

    private static int calculateDigit(String cpf, int length) {
        int sum = 0;
        for (int i = 0; i < length - 1; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (length - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
