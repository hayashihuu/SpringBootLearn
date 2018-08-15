package com.syun.springboottest08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @EnbaleScheduling 开启调度任务
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Springboottest08Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboottest08Application.class, args);
    }


    /**
     * 自定义线程池
     */
    @EnableAsync
    @Configuration
    class TaskPoolConfig {

        @Bean("taskExecutor")
        public ThreadPoolTaskExecutor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            /**
             * 高并发时防止错误
             *用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
             *这样这些异步任务的销毁就会先于Redis线程池的销毁
             */
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.setAwaitTerminationSeconds(60);
            return executor;
        }
    }
}
