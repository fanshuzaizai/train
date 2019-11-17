package com.github.fanshuzaizai.boot.db;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;

@MapperScan
@Slf4j
@SpringBootApplication
public class DBApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class, args);

    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        TaskDecorator taskDecorator = new TaskDecorator() {
            @Override
            public Runnable decorate(Runnable runnable) {
                try {
                    // Right now: Web thread context !
                    // Grab the current thread MDC data
                    Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();

                    return () -> {
                        // Right now: @Async thread context !
                        // Restore the Web thread context's MDC data
                        System.out.println("------ decorate ------ ");
                        MDC.setContextMap(copyOfContextMap);
                        runnable.run();
                    };
                } finally {
                    MDC.clear();
                }
            }
        };


        executor.setTaskDecorator(taskDecorator);
    }
}
