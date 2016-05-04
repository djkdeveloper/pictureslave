package com.djk.pic;

import com.djk.pic.bean.SystemConfig;
import com.djk.pic.service.PicDownLoadService;
import com.djk.pic.servlet.ZookeeperInitServlet;
import com.djk.pic.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

/**
 * Application
 * 工程启动类
 *
 * @author dujinkai
 * @date 2016/5/3
 */
@SpringBootApplication
@EnableConfigurationProperties({SystemConfig.class})
@EnableScheduling
public class Application implements EmbeddedServletContainerCustomizer {

    /**
     * 调试日志
     */
    private static final Logger DEBUG = Logger.getLogger(Application.class);

    @Resource
    private PicDownLoadService picDownLoadService;

    /**
     * 启动服务
     *
     * @param args
     */
    public static void main(String[] args) {

        LogUtils.info(DEBUG, () -> "Begin to start service...");

        SpringApplication.run(Application.class, args);

        LogUtils.info(DEBUG, () -> "Start service success...");
    }


    /**
     * Customize the specified {@link org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer}.
     *
     * @param container the container to customize
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        // 设置启动端口
        container.setPort(8999);
    }

    @Bean
    public ServletRegistrationBean zookeeperServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ZookeeperInitServlet(), "/zookeeperServlet/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    /**
     * 对外暴露出图片下载的hession 接口
     *
     * @return 返回图片下载的hession 接口
     */
    @Bean(name = "/downService")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(picDownLoadService);
        exporter.setServiceInterface(PicDownLoadService.class);
        return exporter;
    }

}
