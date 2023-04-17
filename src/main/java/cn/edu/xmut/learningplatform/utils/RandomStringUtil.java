package cn.edu.xmut.learningplatform.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class RandomStringUtil {
    public static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[(length * 6 + 7) / 8];
        random.nextBytes(bytes);
        String randomString = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return randomString.substring(0, length);
    }
}
