package com.cascadebot.shared;

import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class HmacTests {

    // Used https://www.freeformatter.com/hmac-generator.html to generate HMacs

    @Test
    void hmacValidateTest() {
        try {
            Auth auth = new Auth("key1234");

            String hmacText = auth.hmacEncrypt("Test", 1L);
            assertEquals(hmacText, "e60313610635674f2961c017ce6aae1aaa48e291061788addcac7b5ed428cc3ba6c5907b10a64d4a115a12a1ed168cb5eb68ee42b512e5c40b7fb1fb49a9a0a1");

            hmacText = auth.hmacEncrypt("Test", Long.MAX_VALUE);
            assertEquals(hmacText, "3341b6ada4be6744d3fb7658ddc229050dee320ec04dd546708a82f3e34679a3f2da10fc3644316d2807296668c45fd81881bb00d32453f30b07b2c515c1629f");

            assertTrue(auth.verifyEncrypt("Test", "e60313610635674f2961c017ce6aae1aaa48e291061788addcac7b5ed428cc3ba6c5907b10a64d4a115a12a1ed168cb5eb68ee42b512e5c40b7fb1fb49a9a0a1", 1L));
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
    }
}
