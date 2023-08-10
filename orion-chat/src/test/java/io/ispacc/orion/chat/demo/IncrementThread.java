package io.ispacc.orion.chat.demo;

public class IncrementThread extends Thread {
    private static int counter = 1;

    public static void main(String[] args) {
        final int threadCount = 100;
        Thread[] threads = new Thread[threadCount];

        // 创建并启动线程
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new IncrementThread();
            threads[i].start();
        }

        // 等待所有线程执行完成
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 输出最终结果
        System.out.println("Counter value: " + counter);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter++; // 执行加一操作
        }
    }
}
