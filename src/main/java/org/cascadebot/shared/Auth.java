/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.shared;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Auth {

    private JwtBuilder jwtEncoder;
    private JwtParser jwtVerifier;

    public Auth(String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        jwtEncoder = Jwts.builder().signWith(keySpec);
        jwtVerifier = Jwts.parser().setSigningKey(keySpec);
    }

    public String encode(String subject) {
        return jwtEncoder.setSubject(subject).compact();
    }

    public boolean verify(String encoded, String subject) {
        return jwtVerifier.parseClaimsJws(encoded).getBody().getSubject().equals(subject);
    }

    public static String toHex(byte[] data) {
        StringBuilder builder = new StringBuilder(data.length * 2);

        for (byte b : data) {
            builder.append(String.format("%02x", b)); // Converts byte into hex character and appends
        }

        return builder.toString();

    }
}
