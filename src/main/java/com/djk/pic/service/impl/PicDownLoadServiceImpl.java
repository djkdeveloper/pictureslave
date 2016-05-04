package com.djk.pic.service.impl;

import com.djk.pic.bean.SystemConfig;
import com.djk.pic.bean.ThreadTask;
import com.djk.pic.bean.ZookeeperClient;
import com.djk.pic.service.PicDownLoadService;
import com.djk.pic.utils.FileUtils;
import com.djk.pic.utils.HttpUtils;
import com.djk.pic.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * PicDownLoadServiceImpl
 *
 * @author djk
 * @date 2016/5/3
 */
@Service
public class PicDownLoadServiceImpl implements PicDownLoadService {

    private static final Logger DEBUG = Logger.getLogger(PicDownLoadServiceImpl.class);

    @Resource
    private SystemConfig systemConfig;

    /**
     * 下载图片
     *
     * @param message
     */
    @Override
    public void downLoadPic(String message) {

        LogUtils.info(DEBUG, () -> "Receive downLoadPic request and message:" + message);

        ThreadTask.getInstance().addPicDownLoadTaks(() ->
        {
            FileUtils.getInstance().savePic(HttpUtils.getInstance().downloadPic(message));
        });
    }
}
