package com.nocountry.cashier.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.configuration
 * @license Lrpa, zephyr cygnus
 * @since 15/10/2023
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {
    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // Set the initial number of threads in the pool
        executor.setMaxPoolSize(8); // Set the maximum number of threads in the pool
        executor.setQueueCapacity(25); // Set the queue capacity for holding pending tasks
        executor.setThreadNamePrefix("AsyncTask-"); // Set a prefix for thread names
        executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor e) ->
            log.error("Task Rejected: Thread pool is full. Increase the thread pool size."));

        executor.initialize();
        executor.initialize();
        return executor;
    }
}
