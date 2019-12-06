package com.github.fanshuzaizai.train.boot.brave;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

public class TraceDemo {

    public static void main(String[] args) {


        //http发送工具
        Sender sender = OkHttpSender.create("http://localhost:9411/api/v2/spans");
        //异步发送
        AsyncReporter<zipkin2.Span> asyncReporter = AsyncReporter.builder(sender)
//                .closeTimeout(500, TimeUnit.MILLISECONDS)
//                .build(SpanBytesEncoder.JSON_V2)
                .build();

        Tracing tracing = Tracing.newBuilder()
                .localServiceName("tracer-demo")
                .spanReporter(asyncReporter)
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadLocalCurrentTraceContext.create())
//                .currentTraceContext(ThreadContextCurrentTraceContext.create())
                .build();

        Tracer tracer = tracing.tracer();

        /*
        demo1
         */
        //启动
        Span span = tracer.newTrace().name("A.....").start();
        try {
            //执行业务
            doSomethingExpensive();
        } finally {
            //结束
            span.finish();
        }

        /*
        demo2
         */
        //启动
        Span twoPhase = tracer.newTrace().name("B....").start();
        try {
            //启动第1个子任务
            Span prepare = tracer.newChild(twoPhase.context()).name("B...a....").start();
            try {
                prepare();
            } finally {
                prepare.finish();
            }
            //启动第2个子任务
            Span commit = tracer.newChild(twoPhase.context()).name("B...b....").start();
            try {
                commit();
            } finally {
                commit.finish();
            }
        } finally {
            //结束
            twoPhase.finish();
        }


        sleep(1000);

    }

    private static void doSomethingExpensive() {
        sleep(500);
    }

    private static void commit() {
        sleep(500);
    }

    private static void prepare() {
        sleep(500);
    }

    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}