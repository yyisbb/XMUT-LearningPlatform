package cn.edu.xmut.learningplatform.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author zwj
 */
public class RandomStringUtil {
    // 数字字符数组
    private static final char[] DIGITS = "0123456789".toCharArray();
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[(length * 6 + 7) / 8];
        random.nextBytes(bytes);
        String randomString = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return randomString.substring(0, length);
    }


    public static String generateRandomNumberString(int length) {
        char[] randomChars = new char[length];

        for (int i = 0; i < length; i++) {
            // 随机生成数字字符数组中的索引
            int index = secureRandom.nextInt(DIGITS.length);
            // 将数字字符数组中对应索引的字符添加到char数组中
            randomChars[i] = DIGITS[index];
        }

        return new String(randomChars);
    }
}
