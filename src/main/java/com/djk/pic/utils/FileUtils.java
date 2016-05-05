package com.djk.pic.utils;

import com.djk.pic.bean.SystemConfig;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * FileUtils
 * 文件工具类
 *
 * @author dujinkai
 * @date 2016/5/4
 */
public final class FileUtils {

    /**
     * 调试日志
     */
    private static final Logger DEBUG = Logger.getLogger(FileUtils.class);

    private static final FileUtils fileUtils = new FileUtils();

    private FileUtils() {
    }

    public static FileUtils getInstance() {
        return fileUtils;
    }

    /**
     * 系统配置实体
     */
    private SystemConfig systemConfig = ApplicationContextHelper.getBean(SystemConfig.class);

    /**
     * 保存文件
     *
     * @param bytes        文件的字节流
     * @param filePathName 保存文件的路径 包括文件名称
     * @throws IOException
     */
    public void saveFileByBytes(byte[] bytes, String filePathName) throws IOException {

        LogUtils.info(DEBUG, () -> "Being to save file and filePathName:" + filePathName);

        File file = new File(filePathName);

        OutputStream os = null;

        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            os = new FileOutputStream(file);

            os.write(bytes);
        } catch (Exception e) {
            LogUtils.error(DEBUG, () -> "Save File Fail...", e);
            throw new IOException("Save File Fail.", e);
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e2) {

                LogUtils.error(DEBUG, () -> "Close File Fail...", e2);
            }
        }
    }

}
