package com.app.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
