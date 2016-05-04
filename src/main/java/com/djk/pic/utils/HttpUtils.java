package com.djk.pic.utils;

/**
 * HttpUtils
 * http工具类
 *
 * @author dujinkai
 * @date 2016/5/4
 */
public final class HttpUtils {

    private static final HttpUtils httpUtils = new HttpUtils();

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        return httpUtils;
    }


    public byte[] downloadPic(String url) {
        return null;
    }

}
