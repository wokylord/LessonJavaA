package telran.intrview.test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.intrview.tasks.myStack;

class MyStackTest {
    private static final long N_NUMBERS = 10000;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    myStack stack = new myStack();
    LinkedList<Integer> list = new LinkedList<>();
    myStack stackEmpty = new myStack();
    Random gen = new Random();
    @BeforeEach
    void setUp() throws Exception {
        gen.ints(N_NUMBERS, MIN_NUMBER, MAX_NUMBER).
                forEach(n -> {
                    stack.push(n);
                    list.add(n);
                });
    }
    @Test
    void testpull() {
        assertEquals(list.removeLast(), stack.pull());

        assertThrowsExactly(NoSuchElementException.class, ()-> stackEmpty.pull());
    }

    @Test
    void testPush() {
        stack.push(-10);
        assertNotEquals(list.removeLast(), stack.pull());
    }

    @Test
    void testIsEmpty() {
        assertFalse(stack.isEmpty());
        assertTrue(stackEmpty.isEmpty());
    }

    @Test
    void testGetMaxNumber() {
        testRandom();
        predefinedGetMaxTest();
    }
    void testRandom() {
        for (int i = 0; i < N_NUMBERS; i++) {
            if (Math.random() * 100 < 50) {
                try {
                    stack.pull();
                    list.removeLast();
                } catch (Exception e) {

                }

            }
            else {
                int number =  gen.nextInt(MIN_NUMBER, MAX_NUMBER);

                stack.push(number);
                list.add(number);
            }
        }
        assertEquals((int)Collections.max(list), stack.getMax());
    }
    void predefinedGetMaxTest() {
        int ar[]= {100000,50000,100000,20,20,20,2000000};
        myStack myStack = new myStack();
        myStack stack = myStack;
        for (int i = 0; i < ar.length; i++) {
            stack.push(ar[i]);
        }
        assertEquals(2000000, stack.getMax());
        stack.pull();stack.pull();stack.pull();
        assertEquals(100000, stack.getMax());
        while(!stack.isEmpty()) {
            stack.pull();
        }
        assertThrowsExactly(NoSuchElementException.class, () -> stack.getMax());
    }


}