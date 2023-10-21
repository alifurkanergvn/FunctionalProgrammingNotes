package org.example.programming;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP02Functional {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        int sum = addListFunctional(numbers);  // reduce()
        System.out.println(sum);
        distinctListFunctional(numbers);  // distinct()
        sortedListFunctional(numbers);  // sorted()

        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices", "AWS", "PCF","Azure", "Docker", "Kubernetes");
        sortWithReverseOrderComparator(courses);  // Comparator.reverseOrder()
        sortWithLenghtComparator(courses);  // Comparator.comparing(s -> s.length()

        List<Integer> numberToNewList = takeSquareNumberToNewList(numbers);  // collect(Collectors.toList())
        System.out.println("New List: "+numberToNewList);
    }

    private static int sum(int aggregate, int nextNumber) {
        System.out.println(aggregate + " " + nextNumber);
        // 0 12
        // 12 9
        // 21 13 bu şekilde 0 ilk değer 12 sıradaki sayı 0+12 sonucu aggregate olarak devam ediyor.
        return aggregate + nextNumber;
    }


    //Reduce
    private static int addListFunctional(List<Integer> numbers) {
        // sayının Stream'i -> Tek değer olmalıysa bunu .reduce() ile yaprız
        //Tek bir sonuç içerisine birleştirmelisin -> Tek değer
        // 0 ve (a,b) -> a + b  FP02Functional::sum
       return numbers.stream()
               //.reduce(0,FP02Functional::sum); //Method Referance
               //.reduce(0, (x,y)-> x+y); //x+y = return olarak dönecek değer. (x,y)= x=aggregate y=nextNumber
                //0 ilk x'in yerine giriyor y ise next number ilk aşamada x=0 y=12 aggregate 12 oluyor.
                //bir sonraki aşamada 0'ın yerinde 12 oluyor 12+9 = 21 olarak gidiyor

               //.reduce(0, (x,y)-> x); //Sonuç 0 olur. 0 x in yerine giriyor y=12 return edilen sadece x oda 0
               .reduce(Integer.MIN_VALUE, (x,y) -> x>y ? x:y); // En büyük sayıyı bulmanı sağlar.

    }

    //Distinct - Listedeki tüm sayılar farklı olsun tekrarlı olanları bir tane kabul et.

    private static void distinctListFunctional(List<Integer> numbers){
        System.out.println("Distinct Ex.");
        numbers.stream().distinct().forEach(System.out::println);
    }
    private static void sortedListFunctional(List<Integer> numbers) {
        System.out.println("Sorted Ex.");
        numbers.stream().sorted().forEach(System.out::println);
    }

    //Comparator ile Sort yapmak

    private static void sortWithReverseOrderComparator(List<String> numbers){
        System.out.println("-- Reversed Sorted Ex.--");
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
    //Comparator ile kelime uzunluğuna göre sorting yapmak

    private static void sortWithLenghtComparator(List<String> numbers){
        System.out.println("-- Lenght Sorted Ex.--");
        numbers.stream().sorted(Comparator.comparing(s -> s.length())).forEach(System.out::println);
    }

    private static List<Integer> takeSquareNumberToNewList(List<Integer> numbers) {
        return numbers.stream().map(i -> i*i).collect(Collectors.toList());
    }
}
