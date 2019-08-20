/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.shared;

import io.jsonwebtoken.*;

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

    /**
     * Verifies the jwt and returns the claims
     *
     * @param encoded The jwt string
     * @return The claims or null if jwt was not valid
     */
    public Claims verify(String encoded) {
        try {
            return jwtVerifier.parseClaimsJws(encoded).getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    public static String toHex(byte[] data) {
        StringBuilder builder = new StringBuilder(data.length * 2);

        for (byte b : data) {
            builder.append(String.format("%02x", b)); // Converts byte into hex character and appends
        }

        return builder.toString();

    }
}
