package rider11.hellospringboot.service;

import org.springframework.scheduling.annotation.Async;

import rider11.hellospringboot.bean.RequestLog;

public interface LogService {
    void saveLog(RequestLog requestLog);
    @Async
    void saveLogAsync(RequestLog requestLog);
}
