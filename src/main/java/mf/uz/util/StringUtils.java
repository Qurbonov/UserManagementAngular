package mf.uz.util;

import java.util.Locale;

public class StringUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String lettersOnly(String value) {
        char[] result = new char[value.length()];
        int index = 0;
        for (char c : value.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                result[index++] = c;
            }
        }
        return String.valueOf(result).substring(0, index);
    }

    public static String toPascalCase(String text) {
        return toPascalCase(text, Locale.getDefault());
    }

    public static String toPascalCase(String text, Locale locale) {
        return text.substring(0, 1).toUpperCase(locale) + text.substring(1).toLowerCase(locale);
    }

    public static String toNormalCase(String text) {
        return toNormalCase(text, Locale.getDefault());
    }

    public static String toNormalCase(String text, Locale locale) {
        return text.substring(0, 1).toUpperCase(locale) + text.substring(1);
    }

    public static String getFixedLengthText(String text, int length) {
        return text == null ? null : text.length() < length ? text : text.substring(0, length).concat("...");
    }

    public static String getFullName(String firstName, String middleName, String lastName) {
        StringBuilder result = new StringBuilder("");
        if (!isEmpty(lastName)) {
            result.append(lastName);
        }
        if (!isEmpty(firstName)) {
            if (!isEmpty(result.toString())) {
                result.append(" ");
            }
            result.append(firstName);
        }
        if (!isEmpty(middleName)) {
            if (!isEmpty(result.toString())) {
                result.append(" ");
            }
            result.append(middleName);
        }
        return result.toString();
    }

    public static String getShortName(String firstName, String middleName, String lastName) {
        StringBuilder result = new StringBuilder("");
        if (!isEmpty(lastName)) {
            result.append(lastName);
        }
        if (!isEmpty(firstName)) {
            if (!isEmpty(result.toString())) {
                result.append(" ");
            }
            result.append(firstName.substring(0, 1)).append(".");
        }
        if (!isEmpty(middleName)) {
            if (!isEmpty(result.toString())) {
                result.append(" ");
            }
            result.append(middleName.substring(0, 1)).append(".");
        }
        return result.toString();
    }
}
