package org.example.programming;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FP03Exercise {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);


//        List<Integer> squaredNumbers =  numbers.stream()
//                .map(x -> x*x)
//                .collect(Collectors.toList());
        //Yukarıdaki halini Behavior Parameterization'a uygun olacak şekilde yeniden düzenle.

        Function<Integer, Integer> squaredFunction = x -> x * x;
        List<Integer> squaredNumbers = mapAndCreateNewList(numbers, squaredFunction);
        List<Integer> cubedNumbers = mapAndCreateNewList(numbers, x -> x * x * x);
        System.out.println(squaredNumbers);
        System.out.println(cubedNumbers);

    }

    private static List<Integer> mapAndCreateNewList(List<Integer> numbers, Function<Integer, Integer> mappingFunction) {
        return numbers.stream()
                .map(mappingFunction)
                .collect(Collectors.toList());
    }
}
