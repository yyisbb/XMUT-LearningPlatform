package cn.edu.xmut.learningplatform.utils;

import java.util.Random;

public class StudentIdUtil {
    public static String generateNumberWithPrefix(String prefix, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        int prefixLength = prefix.length();
        int numberLength = length - prefixLength;
        Random random = new Random();
        for (int i = 0; i < numberLength; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
