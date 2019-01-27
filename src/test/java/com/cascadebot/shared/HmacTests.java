package com.cascadebot.shared;

import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class HmacTests {
    @Test
    void hmacValidateTest() {
        try {
            Auth auth = new Auth("key1234"); //Not out actual key
            String hmacText = auth.hmacEncrypt("Test");
            assertTrue(auth.verifyEncrypt("Test", hmacText));
            assertFalse(auth.verifyEncrypt("Test2", hmacText));
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
    }
}
