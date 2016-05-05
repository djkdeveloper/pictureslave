package com.djk.pic.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpUtils
 * http工具类
 *
 * @author dujinkai
 * @date 2016/5/4
 */
public final class HttpUtils {

    /**
     * 调试日志
     */
    private static final Logger DEBUG = Logger.getLogger(HttpUtils.class);

    private static final HttpUtils httpUtils = new HttpUtils();

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        return httpUtils;
    }


    /**
     * 下载图片
     *
     * @param httpUrl 图片地址
     * @return 返回图片流
     * @throws MalformedURLException
     */
    public byte[] downloadPic(String httpUrl) throws MalformedURLException {

        LogUtils.info(DEBUG, () -> "Begin to  download pic and url :" + httpUrl);

        URL url = new URL(httpUrl);
        InputStream inStream = null;
        HttpURLConnection conn = null;

        try {
            // 打开连接
            conn = (HttpURLConnection) url.openConnection();

            // 设置超时时间 30秒
            conn.setConnectTimeout(1000 * 30);

            // 获得输入流
            inStream = conn.getInputStream();

            byte[] bytes = StreamUtils.getInstance().stream2Bytes(inStream);

            inStream.close();
            conn.disconnect();
            LogUtils.info(DEBUG, () -> "download pic :" + httpUrl + "success...");
            return bytes;
        } catch (IOException e) {
            LogUtils.error(DEBUG, () -> "Down load from " + httpUrl + "fail", e);
            throw new RuntimeException("Down load from " + httpUrl + "fail", e);
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }

                if (conn != null) {
                    conn.disconnect();
                }

                url = null;
                conn = null;
                inStream = null;
            } catch (Exception e2) {
                LogUtils.error(DEBUG, () -> "close resource fail", e2);
                throw new RuntimeException("close resource fail", e2);
            }
        }

    }

}
