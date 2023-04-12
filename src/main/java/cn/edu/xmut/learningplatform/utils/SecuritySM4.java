package cn.edu.xmut.learningplatform.utils;

import org.bouncycastle.util.encoders.Hex;

import javax.xml.bind.DatatypeConverter;

public class SecuritySM4 {
    /**
     * SM4加密
     *
     * @param str 参数
     * @param key 随机码
     * @return
     */
    public static String EncryptStr(String str, String key) {
        return DatatypeConverter.printHexBinary(new SM4_Context().EncryptByte(str.getBytes(), key.getBytes()));
    }

    /**
     * 解密
     *
     * @param cipherStrings 密文
     * @param keyStr        随机码
     * @return
     */
    public static String DecryptStr(String cipherStrings, String keyStr) {
        String result = "";
        SM4_Context aa = new SM4_Context();
        byte[] dd = "00".getBytes();

        byte[] cipherText = Hex.decode(cipherStrings);
        byte[] keyBytes = keyStr.getBytes();

        try {

            dd = aa.DecryptStrByte(cipherText, keyBytes);
            result = new String(dd);
        } catch (Exception e) {
        }
        return result;
    }
}
