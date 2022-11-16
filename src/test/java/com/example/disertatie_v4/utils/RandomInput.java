package com.example.disertatie_v4.utils;

import java.util.Random;

public class RandomInput {
    public static final int NUM_0 = 48; // numeral '0'
    public static final int NUM_9 = 57; // numeral '9'
    public static final int LOWERCASE_Z = 122; // letter 'z'
    public static final int UPPERCASE_Z = 90; // letter 'z'
    public static final int LOWERCASE_A = 98; // letter 'a'
    public static final int UPPERCASE_A = 65; // letter 'a'
    private static final int DEFAULT_STRING_LENGTH = 10;

    private static String buildRandomString(int stringLength) {
        StringBuilder randomStringBuilder = new StringBuilder(stringLength);
        new Random().ints(NUM_0, LOWERCASE_Z + 1)
                .filter(i -> (i >= NUM_0 && i <= NUM_9)
                        || (i >= UPPERCASE_A && i <= UPPERCASE_Z)
                        || (i >= LOWERCASE_A && i <= LOWERCASE_Z))
                .limit(stringLength)
                .forEach(randomStringBuilder::appendCodePoint);
        return randomStringBuilder.toString();
    }

    public static String randomString(int stringLength) {
        return buildRandomString(stringLength);
    }

    public static String randomString() {
        return buildRandomString(DEFAULT_STRING_LENGTH);
    }
}
