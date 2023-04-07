package lesson07_thread_safe_object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetterExample {

    private List<Integer> list;

    public GetterExample(List<Integer> list) {
        /**
         * Неправильно!
         */
//        this.list = list;

        /**
         * Правильно
         */
        this.list = new ArrayList<>(list);

        /**
         * Тоже можно (неизменяемая коллекция)
         */
//        this.list = List.copyOf(list);


    }

    public List<Integer> getList() {
        /**
         * Неправильно!
         */
//        return list;

        /**
         * Правильно
         */
        return new ArrayList<>(list);
    }
}
