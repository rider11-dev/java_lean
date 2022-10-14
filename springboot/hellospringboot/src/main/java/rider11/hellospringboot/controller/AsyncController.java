package rider11.hellospringboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

//https://www.w3cschool.cn/article/55411104.html
@Slf4j
@RestController
@RequestMapping("/async")
@Api("测试异处理")
public class AsyncController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @GetMapping("/callable")
    @ApiOperation("测试Callable,方法'执行后'拦截器会失效")
    public Callable<String> testCallable() {
        log.debug("主线程开始:{}", Thread.currentThread().getName());
        Callable<String> result = () -> {
            log.debug("副线程开始:{}", Thread.currentThread().getName());
            Thread.sleep(1000);
            log.debug("副线程结束:{}", Thread.currentThread().getName());
            return sdf.format(new Date());
        };
        log.debug("主线程返回:{}", Thread.currentThread().getName());
        return result;
    }

    @GetMapping("/webasynctask")
    @ApiOperation("测试WebAsyncTask,方法'执行后'拦截器会失效")
    public WebAsyncTask<String> testWebAsyncTask(@RequestParam(required = false) int sleep) {
        log.debug("主线程开始:{}", Thread.currentThread().getName());
        // 设置3s超时
        WebAsyncTask<String> result = new WebAsyncTask<>(3 * 1000L, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("副线程开始:{}", Thread.currentThread().getName());
                try {
                    Thread.sleep(Math.max(1, sleep) * 1000);
                } catch (Exception ex) {
                    log.debug("副线程处理异常:{},{}", Thread.currentThread().getName(), ex.getMessage());
                }
                log.debug("副线程结束:{}", Thread.currentThread().getName());
                return sdf.format(new Date());
            }
        });
        result.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("处理timeout回调:{}", Thread.currentThread().getName());
                return "请求处理超时";
            }
        });
        result.onError(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("处理error回调:{}", Thread.currentThread().getName());
                return "请求处理错误";
            }
        });
        result.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.debug("处理complate回调:{}", Thread.currentThread().getName());
            }
        });

        log.debug("主线程返回:{}", Thread.currentThread().getName());
        return result;
    }
}
