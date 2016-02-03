package mf.uz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DigestUtils.class);

    private static byte[] getBytes(String text) {
        return text.getBytes();
    }

    public static String digest(byte[] bytes, String algorithm) {
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes, 0, bytes.length);
            byte[] mdbytes = md.digest();
            for (byte mdbyte : mdbytes) {
                result.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
            }
            md.reset();
        } catch (NoSuchAlgorithmException e) {
            LOG.error(null, e);
        }
        return result.toString();
    }

    public static String digest(String text, String algorithm) {
        return digest(text.getBytes(), algorithm);
    }

    public static String md5(byte[] bytes) {
        return digest(bytes, "MD5");
    }

    public static String md5(String text) {
        return md5(getBytes(text));
    }

    public static String sha(byte[] bytes) {
        return digest(bytes, "SHA");
    }

    public static String sha(String text) {
        return sha(getBytes(text));
    }

    public static String sha256(byte[] bytes) {
        return digest(bytes, "SHA-256");
    }

    public static String sha256(String text) {
        return sha256(getBytes(text));
    }
}
