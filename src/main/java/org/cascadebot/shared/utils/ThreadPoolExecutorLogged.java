/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.shared.utils;

import org.slf4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorLogged extends ThreadPoolExecutor {

    private Logger logger;
    public ThreadPoolExecutorLogged(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                    TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory factory, Logger logger) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, factory);
        this.logger = logger;
    }

    @Override
    public void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t == null && r instanceof Future<?>) {
            try {
                Object result = ((Future<?>) r).get();
            } catch (CancellationException e) {
                t = e;
            } catch (ExecutionException e) {
                t = e.getCause();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            logger.error("Unhandled exception in thread: " + Thread.currentThread().getName() + "!", t);
        }
    }

    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory, Logger logger) {
        return new ThreadPoolExecutorLogged(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                threadFactory, logger);
    }

    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory, Logger logger) {
        return new ThreadPoolExecutorLogged(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory, logger);
    }

}
