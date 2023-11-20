package io.ispacc.orion.example.design_pattern.singleton;

/**
 * 饿汉式
 * <p> 优点：线程安全，调用效率高 </p>
 * <p> 缺点：不能延时加载 </p>
 * <p> 适用场景：单例对象较少的情况 </p>
 */
public class Singleton1 {
    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
