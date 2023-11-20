package io.ispacc.orion.example.design_pattern.singleton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class SingletonTest {

    @Test
    public void testEx() {
        ExDemo instance1 = new ExDemo();
        ExDemo instance2 = new ExDemo();
        Assertions.assertEquals(instance1, instance2);
    }

    /**
     * 饿汉式
     */
    @Test
    public void testSingleton1() {
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        Assertions.assertEquals(instance1, instance2);
    }

    @Test
    public void testSingleton2() {
        for (int i = 0; i < 100; i++) {
            Thread.startVirtualThread(() -> {
                Singleton1 instance1 = Singleton1.getInstance();
                log.info("instance1: {}", instance1);
            });
        }

    }


}
