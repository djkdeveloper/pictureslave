package com.djk.pic.bean;

import com.djk.pic.utils.LogUtils;
import com.djk.pic.utils.ZookeeperUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ZookeeperTasks
 * Zookeeper 上报的定时任务
 *
 * @author dujinkai
 * @date 2016/5/4
 */
@Component
public class ZookeeperTasks {

    /**
     * 调试日志
     */
    private static final Logger DEBUG = Logger.getLogger(ZookeeperTasks.class);

    /**
     * 注入系统配置
     */
    @Resource
    private SystemConfig systemConfig;

    /**
     * zookeeper 上报的定时任务 每分钟上报一次
     */
    @Scheduled(fixedRate = 60000)
    public void zookeeperReport() {
        LogUtils.debug(DEBUG, () -> "ZookeeperTasks execute....");
        ZookeeperClient.getInstance().updateNodeData(ZookeeperUtils.ROOT + "/" + systemConfig.getLocalIp(), ThreadTask.getInstance().getBlockQueueSize() + "");
    }

}
