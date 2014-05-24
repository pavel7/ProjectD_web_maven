package com.omsu.cherepanov.algorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Павел on 24.05.2014.
 */
public class HashText {

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
