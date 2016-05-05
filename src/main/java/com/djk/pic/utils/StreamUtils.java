package com.djk.pic.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流转化工具类
 *
 * @author dujinkai
 */
public class StreamUtils {
    private static StreamUtils streamUtils = new StreamUtils();

    private StreamUtils() {

    }

    public static StreamUtils getInstance() {
        return streamUtils;
    }

    /**
     * 将输入流转化成字节数组
     *
     * @param is
     * @return
     * @throws java.io.IOException
     */
    public final byte[] stream2Bytes(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1204 * 100];

        int length = 0;

        while (-1 != (length = is.read(buffer))) {
            baos.write(buffer, 0, length);
        }

        buffer = null;

        return baos.toByteArray();
    }

    /**
     * 根据字节数组获得输入流
     *
     * @param bytes 字节数组
     * @return 返回输入流
     */
    public final InputStream getInputStreamByBytes(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}
