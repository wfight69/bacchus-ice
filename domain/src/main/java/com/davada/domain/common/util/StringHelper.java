package com.davada.domain.common.util;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class StringHelper {
    //
    private static final String DEFAULT_HASH_ALGORITHM = "SHA-256";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    private static final Logger logger = LoggerFactory.getLogger(StringHelper.class);

    protected StringHelper() {
        // Nothing to do
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        return defaultIfEmpty(trim(str), null);
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return str != null && str.length() > 0 ? str : defaultStr;
    }

    public static String concat(String ... values) {
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            if (value != null) {
                builder.append(value);
            }
        }
        return builder.toString();
    }

    public static String urlFormat(String baseUrl, String path, Object ... values) {
        return format(baseUrl + path, values);
    }

    public static String formatWithOptions(FormatOptions options, String text, Object ... values) {
        if (text.indexOf("{0}") >= 0) {
            for (int i = 0; i < values.length; i++) {
                text = text.replaceAll("\\{" + i + "\\}", values[i] != null ? values[i].toString() : options.nullToEmpty ? "" : "null");
            }
        } else {
            if (values != null && values.length > 0) {
                for (Object value : values) {
                    text = text.replaceFirst("\\{\\}", value != null ? value.toString() : options.nullToEmpty ? "" : "null");
                }
            }
        }
        return text;
    }

    public static String format(String text, Object ... values) {
        return formatWithOptions(FormatOptions.builder().build(), text, values);
    }

    public static String hash(String plain) {
        return hash(plain, DEFAULT_HASH_ALGORITHM);
    }

    public static String hash(String plain, String algorithm) {
        StringBuilder tokenBuilder = new StringBuilder();
        if (!StringUtils.isEmpty(plain)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                messageDigest.update(plain.getBytes());
                byte[] digestBytes = messageDigest.digest();

                for (byte digestByte : digestBytes) {
                    tokenBuilder.append(Integer.toHexString(0xFF & digestByte));
                }

            } catch (NoSuchAlgorithmException e) {
                logger.error("Exception caught.", e);
            }
        }

        return tokenBuilder.toString();
    }

    public static String join(List<String> values) {
        return join(values, ',');
    }

    public static String join(List<String> values, char joinChar) {
        StringBuilder builder = new StringBuilder();
        if (values != null && !values.isEmpty()) {
            Iterator<String> iterator = values.iterator();
            boolean first = true;
            while (iterator.hasNext()) {
                String value = iterator.next();
                if (!isEmpty(value)) {
                    if (!first) {
                        builder.append(joinChar);
                    }
                    builder.append(value);
                    first = false;
                }
            }
        }

        return builder.toString();
    }

    public static String formatPhoneNo(String phoneNo) {
        if (phoneNo == null || phoneNo.length() == 0) {
            return "";
        }

        if (phoneNo.contains("-")) {
            phoneNo = phoneNo.replaceFirst("=", "");
        }

        int length = phoneNo.length();
        if (length == 8) {
            return phoneNo.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        } else if (length == 12) {
            return phoneNo.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }
        return phoneNo.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }

    public static String maskPhoneNo(String phoneNo, boolean withDashedFormat) {
        if (withDashedFormat) {
            phoneNo = formatPhoneNo(phoneNo);
        }

        String maskedPhoneNo = "";
        if (phoneNo != null) {
            if (phoneNo.length() > 4) {
                int phoneNoLength = phoneNo.length();
                maskedPhoneNo = phoneNo.substring(0, phoneNoLength - 4).replaceAll("\\d", "*").concat(phoneNo.substring(phoneNoLength - 4));
            } else {
                maskedPhoneNo = phoneNo;
            }
        }
        return maskedPhoneNo;
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }

        if (str.toUpperCase().contains(searchStr.toUpperCase())) {
            return true;
        }

        return false;
    }

    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUMBER_PATTERN.matcher(strNum).matches();
    }

    public static String padLeft(String value, int length, char paddingChar) {
        if (value.length() < length) {
            final int count = length - value.length();
            final StringBuilder builder = new StringBuilder(length);
            for (int i = 0; i < count; i++) {
                builder.append(paddingChar);
            }
            builder.append(value);
            return builder.toString();
        }
        return value;
    }

    public static String padRight(String value, int length, char paddingChar) {
        if (value.length() < length) {
            final StringBuilder builder = new StringBuilder(length);
            builder.append(value);
            final int count = length - value.length();
            for (int i = 0; i < count; i++) {
                builder.append(paddingChar);
            }
            return builder.toString();
        }
        return value;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FormatOptions {
        private boolean nullToEmpty;
    }
}
