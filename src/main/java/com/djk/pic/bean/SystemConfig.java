package com.djk.pic.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SystemConfig
 * <p>
 * 系统配置文件
 *
 * @author dujinkai
 * @date 2016/5/3
 */
@ConfigurationProperties(prefix = "config")
public class SystemConfig {

    /**
     * zk的链接
     */
    private String zkConnection;

    /**
     * 本机ip地址
     */
    private String localIp;

    /**
     * 图片保存的路径
     */
    private String savePicPath;

    public String getZkConnection() {
        return zkConnection;
    }

    public void setZkConnection(String zkConnection) {
        this.zkConnection = zkConnection;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public String getSavePicPath() {
        return savePicPath;
    }

    public void setSavePicPath(String savePicPath) {
        this.savePicPath = savePicPath;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
                "zkConnection='" + zkConnection + '\'' +
                ", localIp='" + localIp + '\'' +
                ", savePicPath='" + savePicPath + '\'' +
                '}';
    }
}
