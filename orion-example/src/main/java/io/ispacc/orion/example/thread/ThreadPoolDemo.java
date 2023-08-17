package io.ispacc.orion.example.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static ThreadPoolExecutor buildThreadPool() {
        return new ThreadPoolExecutor(10,
                30,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));
    }

    public static void main(String[] args) {
    }
}
