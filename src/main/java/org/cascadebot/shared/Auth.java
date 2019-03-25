/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.shared;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class Auth {

    private Mac hmac;

    public Auth(String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac = Mac.getInstance("HmacSHA512");
        hmac.init(keySpec);
    }

    public String hmacEncrypt(String text) {
        return hmacEncrypt(text, getTimeCounter());
    }

    public String hmacEncrypt(String text, Long timeCounter) {
        text += "-" + timeCounter;
        return toHex(hmac.doFinal(text.getBytes(StandardCharsets.UTF_8)));
    }

    public boolean verifyEncrypt(String text, String hmacText) {
        return verifyEncrypt(text, hmacText, getTimeCounter());
    }

    public boolean verifyEncrypt(String text, String hmacText, Long timeCounter) {
        text += "-" + timeCounter;
        return toHex(hmac.doFinal(text.getBytes(StandardCharsets.UTF_8))).equals(hmacText);
    }

    private Long getTimeCounter() {
        return System.currentTimeMillis() / TimeUnit.DAYS.toMillis(1);
    }

    public static String toHex(byte[] data) {
        StringBuilder builder = new StringBuilder(data.length * 2);

        for (byte b : data) {
            builder.append(String.format("%02x", b)); // Converts byte into hex character and appends
        }

        return builder.toString();

    }
}
