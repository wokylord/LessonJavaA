package telran.streams.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {
    @Test
    void arrayStreamTest() {
        int[] arr = {10, 13, 8, 7, 3, 5, 6};
        int[] empty ={};
        // проверка на сумму четных
        assertEquals(24, Arrays.stream(arr)
                .filter(n -> n % 2 == 0)// фильтрую четные
                .sum()); //складываю
        //проверка на максимальное нечетное число
        assertEquals(13,Arrays.stream(arr)
                .filter(n-> n%2 !=0)
                .max()
                .getAsInt());// нужно для .max

        assertEquals(0,Arrays.stream(empty)
                .filter(n-> n%2 !=0)
                .max()
                .orElse(0));// если ничего нет то ставит 0
    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }
}