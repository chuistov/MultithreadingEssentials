package lesson07_thread_safe_object;

import java.util.ArrayList;
import java.util.List;

public class GetterExampleRunner {

    public static void main(String[] args) {
        GetterExample getterExample = new GetterExample(new ArrayList<>());
        List<Integer> list = getterExample.getList();
        list.add(2);
    }
}
