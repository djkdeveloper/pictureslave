package com.djk.pic.bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadTask
 * 线程池的管理
 *
 * @author dujinkai
 * @date 2016/5/4
 */
public final class ThreadTask {

    private static final ThreadTask threadTask = new ThreadTask();

    private ThreadTask() {
    }

    public static ThreadTask getInstance() {
        return threadTask;
    }

    /**
     * 固定线程池
     */
    private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(300);


    /**
     * 加入图片下载任务
     *
     * @param runnable 线程
     */
    public void addPicDownLoadTaks(Runnable runnable) {
        executorService.execute(runnable);
    }


    /**
     * 获得队列中等待的线程数
     *
     * @return 返回队列中等待的线程数
     */
    public int getBlockQueueSize() {
        return executorService.getQueue().size();
    }


}
