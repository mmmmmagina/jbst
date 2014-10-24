package com.jbst.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Util {

    private static Logger logger = LoggerFactory.getLogger(Util.class);

    public static String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    public static String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    public static String HMAC_SHA512_ALGORITHM = "HmacSHA512";
    public static Gson gson = new Gson();

    public static Optional<String> md5(String src) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] b = src.getBytes("UTF-8");
            m.update(b, 0, b.length);
            return Optional.of(new BigInteger(1, m.digest()).toString(16)
                .toUpperCase());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("md5 encode error");
            return null;
        }
    }

    public static String combineToEncryptParams(HashMap<String, String> params) {
        SortedMap<String, String> sm = new TreeMap<String, String>(params);
        final Joiner.MapJoiner mapJoiner = Joiner.on("&")
            .withKeyValueSeparator("=");
        return mapJoiner.join(sm);
    }

    private static String byteArrayToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static Optional<String> generateSignature(String data,
        String secret, Optional<String> algorithm) {
        String algo = HMAC_SHA1_ALGORITHM;
        if (algorithm.isPresent())
            algo = algorithm.get();

        try {
            Mac mac = Mac.getInstance(algo);
            mac.init(new SecretKeySpec(secret.getBytes(), algo));
            byte[] rawHmac = mac.doFinal(data.getBytes());
            return Optional.of(byteArrayToHex(rawHmac));
        } catch (InvalidKeyException e) {
            logger.error("secret key not invalid");
            return null;
        } catch (NoSuchAlgorithmException e) {
            logger.error("no such algorithm : " + algorithm.get());
            return null;
        }
    }

    public static double roundDouble(double src, int len) {
        return new BigDecimal(src).setScale(len, BigDecimal.ROUND_HALF_UP)
            .doubleValue();
    }

    public static JsonElement stringToGson(String src) {
        return new JsonParser().parse(src);
    }

}
