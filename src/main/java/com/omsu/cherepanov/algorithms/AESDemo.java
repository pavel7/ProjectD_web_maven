package com.omsu.cherepanov.algorithms;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Павел on 31.05.2014.
 */

public class AESDemo {

    byte[] seed;

    public void AesCrypt(String password) {
        seed = password.getBytes();
    }

    public void AesCrypt(byte[] password) {
        seed = password;
    }

    public String encrypt(String cleartext) {
        byte[] rawKey = new byte[0];
        try {
            rawKey = getRawKey(seed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = new byte[0];
        try {
            result = encrypt(rawKey, cleartext.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return toHex(result);

    }

    public String decrypt(String encrypted) {
        byte[] rawKey = new byte[0];

        try {
            rawKey = getRawKey(seed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] enc = toByte(encrypted);
        byte[] result = new byte[0];
        try {
            result = decrypt(rawKey, enc);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(result);

    }

    private static byte[] getRawKey(byte[] password) throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(password);
        kgen.init(256, sr);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(byte[] buffer) {
        return DatatypeConverter.printBase64Binary(buffer);
    }

    public static byte[] toByte(String hex) {
        return DatatypeConverter.parseBase64Binary(hex);
    }

}
