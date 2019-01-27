package com.cascadebot.shared;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Auth {

    private Mac hmac;

    public Auth(String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac = Mac.getInstance("HmacSHA512");
        hmac.init(keySpec);
    }

    public String hmacEncrypt(String text) {
        return toHex(hmac.doFinal(text.getBytes(StandardCharsets.UTF_8)));
    }

    public boolean verifyEncrypt(String text, String hmacText) {
        return toHex(hmac.doFinal(text.getBytes(StandardCharsets.UTF_8))).equals(hmacText);
    }

    private static String digits = "0123456789abcdef";

    public static String toHex(byte[] data) {
        return toHex(data, data.length);
    }

    public static String toHex(byte[] data, int length) {
        StringBuilder buf = new StringBuilder();

        for(int i = 0; i != length; i++) {
            int v = data[i] & 0xff;
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }

        return buf.toString();

    }
}
