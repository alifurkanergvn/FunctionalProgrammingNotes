package org.example.programming;

import java.util.List;
import java.util.function.Predicate;

public class FP03BehaviorParameterization {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        //numbers.stream().filter(evenPredicate).forEach(System.out::println);
        filterAndPrint(numbers,evenPredicate);

        Predicate<Integer> oddPredicate = x -> x % 2 != 0;
        //Tek ve çift sayıları filtrelerken gereksiz kod tekrarı yapıyoruz. Filtrer içindeki logic'i parametre olarak yollayabiliriz.
        //filter içini seç sağ tık, Refactor, Introduce Variable diyip oddPredicate oluşturduk.
        //numbers.stream().filter(evenPredicate).forEach(System.out::println);// tamamını seçip sağ tık Refactor, Extract Method diyerek filterAndPrint methodunu oluşturduk.
        filterAndPrint(numbers, oddPredicate);
        filterAndPrint(numbers, i -> i % 3 ==0); //3'ün katı olan sayılar
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers.stream().distinct().filter(predicate).forEach(System.out::println);
    }
}
