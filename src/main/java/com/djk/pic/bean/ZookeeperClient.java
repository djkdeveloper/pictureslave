package com.djk.pic.bean;

import com.djk.pic.utils.ApplicationContextHelper;
import com.djk.pic.utils.LogUtils;
import com.djk.pic.utils.StringUtils;
import com.djk.pic.utils.ZookeeperUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.log4j.Logger;

/**
 * ZookeeperClient
 * Zookeeper 客户端对象
 *
 * @author dujinkai
 * @date 2016/4/28
 */
public final class ZookeeperClient {

    /**
     * 调试日志
     */
    private static final Logger DEBUG = Logger.getLogger(ZookeeperClient.class);

    private static final ZookeeperClient instance = new ZookeeperClient();

    private ZookeeperClient() {
    }

    public static ZookeeperClient getInstance() {
        return instance;
    }


    /**
     * zk客户端
     */
    private CuratorFramework curatorFramework;


    /**
     * 统配置文件
     */
    private SystemConfig systemConfig = ApplicationContextHelper.getBean(SystemConfig.class);

    /**
     * 初始化zookeeper服务
     */
    public void initZkService() throws Exception {

        LogUtils.info(DEBUG, () -> "Begin to start zk and connection is :" + systemConfig.getZkConnection());

        curatorFramework = ZookeeperUtils.createSimpleZkClient(systemConfig.getZkConnection());

        curatorFramework.start();
        // 首先判断根节点是否存在
        if (!ZookeeperUtils.isNodeExist(curatorFramework, ZookeeperUtils.ROOT)) {
            LogUtils.debug(DEBUG, () -> ZookeeperUtils.ROOT + "is not exist and begin to create...");
            ZookeeperUtils.create(curatorFramework, ZookeeperUtils.ROOT, "".getBytes());
        }

        // 注册自己的节点
        ZookeeperUtils.createEphemeral(curatorFramework, ZookeeperUtils.ROOT + "/" + systemConfig.getLocalIp(), "0".getBytes());
    }

    /**
     * 更新节点的数据
     *
     * @param nodeName 节点名称
     * @param value    节点数据
     */
    public void updateNodeData(String nodeName, String value) {
        if (StringUtils.isEmpty(nodeName) || StringUtils.isEmpty(value)) {
            LogUtils.error(DEBUG, () -> "UpdateNodeData Fail...due to nodeName or value is null...");
            return;
        }

        LogUtils.info(DEBUG, () -> "Begin to updateNodeData and nodeName:" + nodeName + " value:" + value);

        if (null == curatorFramework) {
            LogUtils.error(DEBUG, () -> "Zookeeper hasnot init ....please wait...");
            return;
        }

        try {
            ZookeeperUtils.setData(curatorFramework, nodeName, value.getBytes());
        } catch (Exception e) {
            LogUtils.error(DEBUG, () -> "UpdateNodeData Fail...", e);
        }
    }


    /**
     * 关闭zookeeper客户端
     */

    public void closeZookeeperClient() {
        try {
            curatorFramework.close();
        } catch (Exception e) {
            LogUtils.error(DEBUG, () -> "Close zk fail...", e);
        }
    }

}
