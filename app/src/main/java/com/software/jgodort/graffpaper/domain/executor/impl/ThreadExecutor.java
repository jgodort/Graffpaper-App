package com.software.jgodort.graffpaper.domain.executor.impl;

import com.software.jgodort.graffpaper.domain.executor.Executor;
import com.software.jgodort.graffpaper.domain.interactors.base.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by javie on 08/04/2017.
 */


/**
 * This singleton class will make sure that each interactor operation gets a background thread.
 * <p/>
 */
public class ThreadExecutor implements Executor {


    private static volatile ThreadExecutor sThreadExecutor;

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();


    private ThreadPoolExecutor mThreadPoolExecutor;


    private ThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;


        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }


    @Override
    public void execute(final AbstractInteractor interactor) {

        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                //run the main logic.
                interactor.run();

                //mark it as finished.
                interactor.onFinished();

            }
        });
    }


    public static Executor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }
        return sThreadExecutor;
    }
}
