package io.ispacc.orion.example.threadlocal;

import org.assertj.core.util.Lists;

import java.util.List;

public class ThreadLocalTest {
    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);
    private List<String> messages = Lists.newArrayList();

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalTest.add("ThreadLocal 1");
        System.out.println(holder.get().messages);
        ThreadLocalTest.clear();
    }
}
