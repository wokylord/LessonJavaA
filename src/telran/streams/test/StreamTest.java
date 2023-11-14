package telran.streams.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {
    @Test
    void arrayStreamTest() {
        int[] arr = {10, 13, 8, 7, 3, 5, 6};
        int[] empty = {};
        // проверка на сумму четных
        assertEquals(24, Arrays.stream(arr)
                .filter(n -> n % 2 == 0)// фильтрую четные
                .sum()); //складываю
        //проверка на максимальное нечетное число
        assertEquals(13, Arrays.stream(arr)
                .filter(n -> n % 2 != 0)
                .max()
                .getAsInt());// нужно для .max

        assertEquals(0, Arrays.stream(empty)
                .filter(n -> n % 2 != 0)
                .max()
                .orElse(0));// если ничего нет то ставит 0
    }

    @Test
    void displaySportloto() {
        Random gen = new Random();
        gen.ints(1, 50)
                .distinct() // убираем повторяющиеся
                .limit(7) // ставим ограничение на 7
                .forEach(n -> System.out.print(n + " "));
    }

    @Test
    @DisplayName("group odd even")
    void evenOddGrouping() {
        int[] arr = {10, 13, 8, 7, 3, 5, 6};
        Map<String, List<Integer>> mapOddEven= Arrays.stream(arr)
                // преобразуем из примитива int в объект Integer
                .boxed()
                //                                            if         else
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
        System.out.println(mapOddEven);
    }

    @Test
    void displaySort() {
        String[] strings = {"lpm", "y", "a", "lpm", "aa", "yy", "yy", "aa", "lpm"};
        /*
        lpm -> 3
        aa -> 2
        yy -> 2
        a -> 1
        y -> 1
         */
        Map<String, List<String>> map = Arrays.stream(strings)
                .collect(Collectors.groupingBy(s-> s));
        System.out.println(map);

        Map<String, Long> map1 = Arrays.stream(strings)
                .collect(Collectors.groupingBy(s-> s, Collectors.counting()));
        System.out.println(map1);
        map1.entrySet().stream()
                .sorted((e1, e2)-> Long.compare(e2.getValue(), e1.getValue()))
                .forEach(e-> System.out.printf("%s => %d\n", e.getKey(), e.getValue()));
        System.out.println("=============");

        map1.entrySet().stream()
                .sorted((e1, e2)-> {
                    int res = Long.compare(e2.getValue(), e1.getValue());
                    if (res==0){
                        res = e1.getKey().compareTo(e2.getKey());
                    }
                    return res;
                })
                .forEach(e-> System.out.printf("%s => %d\n", e.getKey(), e.getValue()));
    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }
}