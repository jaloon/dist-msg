package com.pltone.distmsg.job;

import com.pltone.distmsg.entity.BaseData;
import com.pltone.distmsg.entity.DistMsg;
import com.pltone.distmsg.ws.ElockClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 配送下发
 *
 * @author chenlong
 * @version 1.0 2018-11-22
 */
@Slf4j
@Async
@Component
@EnableScheduling
public class DistIssue {
    private static final String addr = "http://192.168.7.20:3008/Elock_Service.asmx";

    @Scheduled(initialDelay = 1000L, fixedRate = 20 * 60 * 1000L)
    public void execute() {
        DistMsg distMsg = new DistMsg();
        String invoice = null;
        try {
            String massage = distMsg.generate();
            invoice = distMsg.getInvoice();
            log.info("发送配送单【3G00-{}】新增报文：\n{}", invoice, massage);
            String reply = ElockClient.setPlan(addr, massage);
            log.info("配送单【3G00-{}】新增报文应答：\n{}", invoice, reply);

            delayChange(distMsg, invoice);
        } catch (Exception e) {
            log.error("配送单【3G00-{}】新增报文异常：\n{}", invoice, e.toString());
        }
    }

    private void delayChange(DistMsg distMsg, String invoice) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String massage = distMsg.change();
                    log.info("发送配送单【3G00-{}】换站报文：\n{}", invoice, massage);
                    String reply = ElockClient.setPlan(addr, massage);
                    log.info("配送单【3G00-{}】换站报文应答：\n{}", invoice, reply);
                } catch (Exception e) {
                    log.error("配送单【3G00-{}】换站报文异常：\n{}", invoice, e.toString());
                }

            }
        }, (BaseData.random().nextInt(30) + 5) * 60 * 1000L);
    }
}
