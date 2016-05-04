package com.djk.pic.utils;

import com.djk.pic.bean.SystemConfig;

/**
 * FileUtils
 * 文件工具类
 *
 * @author dujinkai
 * @date 2016/5/4
 */
public final class FileUtils {


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

    public void savePic(byte[] bytes) {
    }

}
