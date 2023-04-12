package cn.edu.xmut.learningplatform.utils;

class SM4_Context {

    public int mode = 1;
    public long[] sk = new long[32];
    public boolean isPadding = true;

    protected SM4_Context() {
    }

    /**
     * 加密
     *
     * @param plainText 参数
     * @param keyBytes  随机码
     * @return
     */
    public byte[] EncryptByte(byte[] plainText, byte[] keyBytes) {
        if (keyBytes != null && keyBytes.length == 16) {
            if (plainText != null && plainText.length > 0) {
                try {
                    SM4_Context ctx = new SM4_Context();
                    ctx.isPadding = true;
                    ctx.mode = 1;
                    SM4 sm4 = new SM4();
                    sm4.sm4_setkey_enc(ctx, keyBytes);
                    byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText);
                    return encrypted;
                } catch (Exception var6) {
                    var6.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param cipherText 密文
     * @param keyBytes   随机码
     * @return
     */
    public byte[] DecryptStrByte(byte[] cipherText, byte[] keyBytes) {
        if (keyBytes != null && keyBytes.length == 16) {
            if (cipherText != null && cipherText.length > 0 && cipherText.length % 16 == 0) {
                try {
                    SM4_Context ctx = new SM4_Context();
                    ctx.isPadding = true;
                    ctx.mode = 0;
                    SM4 sm4 = new SM4();
                    sm4.sm4_setkey_dec(ctx, keyBytes);
                    byte[] decrypted = sm4.sm4_crypt_ecb(ctx, cipherText);
                    int decryptedLen = decrypted.length;

                    for (int i = decrypted.length - 1; i >= 0 && decrypted[i] == 0; --decryptedLen) {
                        --i;
                    }

                    byte[] temp = new byte[decryptedLen];
                    System.arraycopy(decrypted, 0, temp, 0, decryptedLen);
                    return temp;
                } catch (Exception var8) {
                    var8.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    protected byte[] encryptData_CBC(byte[] ivBytes, byte[] keyBytes, byte[] plainText) {
        if (keyBytes != null && keyBytes.length != 0 && keyBytes.length % 16 == 0) {
            if (plainText != null && plainText.length > 0) {
                if (ivBytes != null && ivBytes.length > 0) {
                    try {
                        SM4_Context ctx = new SM4_Context();
                        ctx.isPadding = true;
                        ctx.mode = 1;
                        SM4 sm4 = new SM4();
                        sm4.sm4_setkey_enc(ctx, keyBytes);
                        byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText);
                        return encrypted;
                    } catch (Exception var7) {
                        var7.printStackTrace();
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    protected byte[] decryptData_CBC(byte[] ivBytes, byte[] keyBytes, byte[] cipherText) {
        if (keyBytes != null && keyBytes.length != 0 && keyBytes.length % 16 == 0) {
            if (cipherText != null && cipherText.length > 0) {
                if (ivBytes != null && ivBytes.length > 0) {
                    try {
                        SM4_Context ctx = new SM4_Context();
                        ctx.isPadding = true;
                        ctx.mode = 0;
                        SM4 sm4 = new SM4();
                        sm4.sm4_setkey_dec(ctx, keyBytes);
                        byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, cipherText);
                        return decrypted;
                    } catch (Exception var7) {
                        var7.printStackTrace();
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
