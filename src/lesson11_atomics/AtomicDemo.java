package lesson11_atomics;

import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * У Atomic под капотом объект класса Unsafe.
 * Он не использует synchronized, а работает напрямую с памятью на очень низком уровне,
 * уровень примерно C++.
 * Работа с этим объектом смахивает на Reflection API.
 * Пользоваться для разработки не рекомендуется.
 * Unsafe работает по методологии Compare and Set (Compare and Swap). Что это?...
 *
 * Атомики есть для int, long, double, boolean, массивов int и long, ссылок (чтобы ссылка не менялась).
 *
 */

public class AtomicDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int value = atomicInteger.incrementAndGet();
        System.out.println(value);

        int newValue = atomicInteger.addAndGet(20);
        System.out.println(newValue);

    }
}
