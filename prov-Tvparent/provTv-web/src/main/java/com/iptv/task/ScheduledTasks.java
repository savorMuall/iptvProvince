package com.iptv.task;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Created by w_z91 on 2017/5/6.
 */

public class ScheduledTasks implements InitializingBean,DisposableBean, SchedulingConfigurer {

    public void job1() {
        System.out.println("任务进行中");
    }

    @Override
    public void destroy() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void configureTasks(final ScheduledTaskRegistrar scheduledTaskRegistrar) {
    }

    /**
     * 每日凌晨三点计算合伙人收益 // 同时插入合伙人交易明细
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void compute() {
        System.out.println("**************************************************");
    }



}
