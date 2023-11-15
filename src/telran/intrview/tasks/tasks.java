package telran.intrview.tasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class tasks {
    /**
     *
     * @param ar array of integer numbers
     * @param sum integer number
     * @return true if the given array contains two numbers, the sum of which equals the given sum value
     */
    public static boolean isSum2(int [] ar, int sum) {

        boolean running = true;
        HashSet<Integer> setHelper = new HashSet<>();
        int i = 0;
        while(i < ar.length && running) {
            if(setHelper.contains(sum - ar[i])) {
                running = false;
            } else {
                setHelper.add(ar[i]);
                i++;
            }
        }
        return !running;
    }
    /**
     *
     * @param ar array of integer numbers
     * @param sum integer number
     * @return true if the given array contains two numbers, the sum of which equals the given sum value
     */
    public static boolean isSum2N2(int[] ar, int sum) {
        int i = 1;
        int j = 0;
        boolean running = true;
        while(i < ar.length && running) {
            j = i - 1;
            while(j >= 0 ) {
                if(ar[j] + ar[i] == sum) {
                    running = false;
                    break;
                }
                j--;
            }
            i++;
        }
        return !running;
    }
    /**
     *
     * @param ar array of integer numbers
     * @return maximal positive number having negative number with the same absolute value
     */
    public static int getMaxPositiveWithNegativeValue (int ar[]) {
        int res = -1;
        HashSet<Integer> setHelper = new HashSet<>();

        for(int num: ar) {
            if(setHelper.contains(-num)) {
                res = Math.max(res, Math.abs(num));
            } else {
                setHelper.add(num);
            }
        }
        return res;
    }

    /**
     *
     * @param strings - array of strings
     * @return Map where key - string, value - number of occurrences in the array
     */
    public static HashMap<String, Long> getMapOccurrences(String[] strings) {

        //Optionally, additional bonus if you apply the method "merge" of the interface Map
        //try to understand this method https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#merge-K-V-java.util.function.BiFunction-
        //it uses Functional interface BiFunction
        HashMap<String, Long> res = new HashMap<>();
        for(String string: strings) {
            res.merge(string, 1l, Long::sum);
        }
        return res;
    }
    /**
     * Anagram of a string is another string containing all the symbols
     * from the string, but in different order (see the test cases)
     * @param string
     * @param anagram
     * @return true if the second parameter presents an anagram
     *  of the string from the first parameter, otherwise false
     */
    public static boolean isAnagram(String string, String anagram) {
        boolean res = false;
        if(!string.equals(anagram) && string.length() == anagram.length()) {
            HashMap<Character, Integer> mapOccurrences = getStringMap(string);
            res = anagramCheck(anagram, mapOccurrences);
        }
        return res;
    }
    private static boolean anagramCheck(String anagram, HashMap<Character, Integer> mapOccurrences) {
        boolean res = true;
        char[] symbols = anagram.toCharArray();
        int i = 0;
        while (i < symbols.length && res) {
            if(mapOccurrences.compute(symbols[i], (k, v) -> v==null ? -1 : v - 1) < 0) {
                res = false;
            } else {
                i++;
            }
        }
        return res;
    }
    private static HashMap<Character, Integer> getStringMap(String string) {
        HashMap<Character, Integer> res = new HashMap<>();
        for(char symbol: string.toCharArray()) {
            res.merge(symbol, 1, Integer::sum);
        }
        return res;
    }
    //Task for streams /grouping
    public static void displayDigitsDistribution() {
        int nNumbers = 1_000_000;
        //TODO
        /*
        //create stream of random int's (nNumbers), each int number in range [1, Integer.Max_VALUE)
        //conversion to stream of Strings
        //extracting separate char's from Strings
        //grouping with counting of occurrences
        //sorting in descending order of occurrences
        //printing

         */
        Random gen = new Random();
        // запускааем поток на 1кк от 1 до МАКС рандомных чисел
        Map<Integer,Long> map = gen.ints(nNumbers,1,Integer.MAX_VALUE)
                // из примитива в объект, но сначала в строку
                .mapToObj(n->Integer.toString(n))
                // раскрываем каждую строку в коды аске "12" "34" --> 49 50 51 52
                // .flatMap потому что из одного мы делаем несколько, какбы раскрываем коробку
                .flatMapToInt(s -> s.chars())
                .boxed()
                // записываем в map           ключ   значение
                .collect(Collectors.groupingBy(s->s,Collectors.counting()));

        // изменяем для получения String
        Map<String,Long> map2 = gen.ints(nNumbers,1,Integer.MAX_VALUE)
                // из примитива в объект, но сначала в строку
                .mapToObj(n->Integer.toString(n))
                // раскрываем каждую строку в коды аске "12" "34" --> 49 50 51 52
                // .flatMap потому что из одного мы делаем несколько, какбы раскрываем коробку
                .flatMapToInt(s -> s.chars())
                .mapToObj(n-> ""+(char)n)
                // записываем в map           ключ   значение
                .collect(Collectors.groupingBy(s->s,Collectors.counting()));

        // другим способом
        Map<String,Long> map3 = gen.ints(nNumbers,1,Integer.MAX_VALUE)
                // раскрываем каждый int в коды аске
                // int        String      char              int
                // 12 34 >>"12" "34" >> '1' '2' '3' '4' >> 49 50 51 52
                .flatMap(n->Integer.toString(n).chars())
                .mapToObj(n-> ""+(char)n)
                // записываем в map           ключ   значение
                .collect(Collectors.groupingBy(s->s,Collectors.counting()));

        map2.entrySet().stream()// Stream<Entry<Integer,Long>>
                // сортируем по значениям в обратном порядке
                .sorted((e1,e2)->Long.compare(e2.getValue(),e1.getValue()))
                .forEach(e-> System.out.printf("%s - %d\n", e.getKey(), e.getValue()));

    }

}