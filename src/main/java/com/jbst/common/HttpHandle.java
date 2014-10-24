package com.jbst.common;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpHandle {

    private ThreadPoolExecutor threadPoolExecutor = null;
    private CompletionService<Object> completionService = null;
    private static HttpHandle threadPoolService = new HttpHandle();

    private HttpHandle() {
        this.threadPoolExecutor = new ThreadPoolExecutor(20, 30, 500,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy());
        this.completionService = new ExecutorCompletionService<Object>(
            this.threadPoolExecutor);
    }

    public static HttpHandle getInstance() {
        return HttpHandle.threadPoolService;
    }

    public Future<Object> submit(Callable<Object> pCallable) {
        return this.completionService.submit(pCallable);
    }

    @SuppressWarnings("unchecked")
    public Future<Object> submit(Runnable pRunnable) {
        return this.completionService.submit((Callable<Object>) pRunnable);
    }

    public void shutdown() {
        this.threadPoolExecutor.shutdown();
    }

    public Collection<Runnable> shutdownNow() {
        return this.threadPoolExecutor.shutdownNow();
    }
}
