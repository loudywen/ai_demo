package com.devon.demo.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by diwenlao on 2/21/17.
 */
public final class Utility {

    public static String encodeAuthorization(String userId, String password)
    {
        String authorization = (userId + ":" + password);
        return Base64.encodeBase64String(authorization.getBytes());
    }


    public static String decodeAuthorization(String encodedAuthorization)
    {
        byte[] decodedBytes = Base64.decodeBase64(encodedAuthorization);
        return new String(decodedBytes);
    }
}
