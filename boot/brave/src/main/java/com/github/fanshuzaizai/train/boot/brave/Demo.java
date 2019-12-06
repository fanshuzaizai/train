package com.github.fanshuzaizai.train.boot.brave;

import brave.ScopedSpan;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.sampler.DeclarativeSampler;
import brave.sampler.Sampler;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

/**
 * @author Jiangzy
 * @date 2019/12/4
 */
public class Demo {


    public static void main(String[] args) {


//http发送工具
        Sender sender = OkHttpSender.create("http://localhost:9411/api/v2/spans");
        //异步发送
        AsyncReporter<Span> asyncReporter = AsyncReporter.builder(sender)
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


        brave.Span span = tracer.newTrace();
        brave.Span start = span.start();
        // Start a new trace or a span within an existing trace representing an operation
        ScopedSpan span_a = tracer.startScopedSpan("encode");
        System.out.println(span_a.context().spanId());
        ScopedSpan span_b = tracer.startScopedSpan("decode");
        System.out.println(span_b.context().parentId());
        ScopedSpan span_c = tracer.startScopedSpan("decode2");
        System.out.println(span_c.context().parentId());

        tracer.withSpanInScope(tracer.toSpan(span_a.context()));
        ScopedSpan span_d = tracer.startScopedSpan("decode2");
        System.out.println(span_d.context().parentId());

        brave.Span span2 = tracer.nextSpan();
        brave.Span span3 = tracer.nextSpan();

        try {
            Thread.sleep(3000);
        } catch (RuntimeException | Error | InterruptedException e) {
            span.error(e); // Unless you handle exceptions, you might not know the operation failed!
            e.printStackTrace();
        } finally {
            span.finish(); // always finish the span
        }
    }

}
