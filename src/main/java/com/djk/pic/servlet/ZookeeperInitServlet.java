package com.djk.pic.servlet;

import com.djk.pic.bean.SystemConfig;
import com.djk.pic.bean.ZookeeperClient;
import com.djk.pic.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ZookeeperInitServlet
 * zookeeper 初始化 servlet
 *
 * @author dujinkai
 * @date 2016/5/4
 */
public class ZookeeperInitServlet extends HttpServlet {

    /**
     * 调试日志
     */
    private static final Logger DEBUG = Logger.getLogger(ZookeeperInitServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        LogUtils.info(DEBUG, () -> "Begin to init zookeeper...");
        try {
            ZookeeperClient.getInstance().initZkService();
        } catch (Exception e) {
            LogUtils.error(DEBUG, () -> "Init zookeeper Fail...", e);
            throw new RuntimeException("Init zookeeper Fail...");
        }

        LogUtils.info(DEBUG, () -> "Init zookeeper success...");
    }

    @Override
    public void destroy() {
        super.destroy();
        ZookeeperClient.getInstance().closeZookeeperClient();
    }
}
