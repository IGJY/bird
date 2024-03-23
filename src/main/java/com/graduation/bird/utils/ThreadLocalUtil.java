package com.graduation.bird.utils;

public class ThreadLocalUtil {

    //提供ThreadLocal对象
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    //提供get方法
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    //提供set方法
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    //提供remove方法,防止内存泄露
    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
