package com.skyworth.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread pool utility class
 * 
 * @author Ni-Guanhua
 * 
 */
public final class ThreadPoolUtil {

    /**
     * number of core threads
     */
    private static final int CORE_THREAD_SIZE = 5;

    /**
     * max threads number
     */
    private static final int MAX_THREAD_SIZE = 100;

    /**
     * max alive time of one thread in spare time
     */
    private static final long KEEP_ALIVE_TIME = 10000l;

    /**
     * blocking thread queue
     * 
     * no more thread will be created util core threads are consumed and the
     * blocking queue is full
     */
    private static BlockingQueue<Runnable> mWorkQueue = new ArrayBlockingQueue<Runnable>(
            10);

    /**
     * thread factory used to create thread instance
     */
    private static ThreadFactory mThreadFactory = new ThreadFactory() {
        final AtomicInteger atomicInteger = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "skyworthThread"
                    + atomicInteger.getAndIncrement());
        }
    };

    /**
     * thread pool
     */
    private static ThreadPoolExecutor mThreadPool;

    static {
        mThreadPool = new ThreadPoolExecutor(CORE_THREAD_SIZE, MAX_THREAD_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, mWorkQueue, mThreadFactory);
    }

    private ThreadPoolUtil() {
    }

    /**
     * execute the asynchronized threads
     * 
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        mThreadPool.execute(runnable);
    }

}
