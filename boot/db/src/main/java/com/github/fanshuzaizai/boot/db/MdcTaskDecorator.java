
package com.github.fanshuzaizai.boot.db;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class MdcTaskDecorator implements TaskDecorator {

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

}