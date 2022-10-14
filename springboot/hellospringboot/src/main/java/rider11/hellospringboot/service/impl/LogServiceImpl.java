package rider11.hellospringboot.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.bean.RequestLog;
import rider11.hellospringboot.service.LogService;

@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Override
    public void saveLog(RequestLog requestLog) {
        try {
            Thread.sleep(3000);
            log.debug("请求日志保存[同步]成功:{}", requestLog);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveLogAsync(RequestLog requestLog) {
        try {
            Thread.sleep(3000);
            log.debug("请求日志保存[异步]成功:{}", requestLog);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
