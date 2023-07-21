package io.ispacc.orion.example.message;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {

    private final static int MAX_VALUE = 5;

    private static ArrayBlockingQueue<String> list = new ArrayBlockingQueue<>(MAX_VALUE);

    public static void produce(String message) {
        if (list.offer(message)) {
            System.out.println("成功向消息处理中心投递消息：" + message + "，当前暂存的消息数量是：" + list.size());
        } else {
            System.out.println("消息处理中心内暂存的消息达到最大负荷，不能继续放入消息！");
        }
        System.out.println("=======================");
    }

    public static String consume() {
        String message = list.poll();
        if (message != null) {
            System.out.println("已经消费消息：" + message + "，当前暂存的消息数量是：" + list.size());
        } else {
            System.out.println("消息处理中心内没有消息可供消费！");
        }
        System.out.println("=======================");
        return message;
    }
}
