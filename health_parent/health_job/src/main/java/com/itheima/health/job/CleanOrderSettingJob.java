package com.itheima.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.OrderSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Component
public class CleanOrderSettingJob {

    private static final Logger log = LoggerFactory.getLogger(CleanImgJob.class);

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 定期清理预约信息
     */
    //@Scheduled(fixedDelay = 180000,initialDelay = 1000)
    public void cleanOrderSetting(){
        log.info("清理无效预约设置的任务开始执行...........");

        //移除无效数据
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(now);
        try {
            orderSettingService.deleteOrderSettingByDate(date);
            log.info("清理无效预约设置的任务成功...........");
        } catch (Exception e) {
            log.error("清理无效预约设置的任务失败!",e);
        }
    }
}
