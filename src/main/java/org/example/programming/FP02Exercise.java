package org.example.programming;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP02Exercise {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        int sum = squareNumbersFindSum(numbers);
        System.out.println(sum);

        int sumofOddNumbers = findSumOfOddNumbers(numbers);
        System.out.println(sumofOddNumbers);

        List<Integer> numbersToNewList = filterEvenNumbersToNewList(numbers);   //collect(Collectors.toList()
        System.out.println("--- numbersToNewList: "+numbersToNewList);

        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices", "AWS", "PCF","Azure", "Docker", "Kubernetes");
        printDistintSortedCourses(courses);
        List<String> sortWithLenghtOfCourses = sortWithLenghtOfCourses(courses);   //sorted(Comparator.comparing(s -> s.length())
        System.out.println("--sortWithLenghtOfCourses--"+sortWithLenghtOfCourses);
    }

    private static int squareNumbersFindSum(List<Integer> numbers) {
        System.out.println("--Square every number in a list and find the sum of squares--");
        return numbers.stream()
                .map(number -> number*number)
                .reduce(0,(a,b) -> a+b);
    }

    private static int findSumOfOddNumbers(List<Integer> numbers) {
        System.out.println("--Find Sum of Odd Numbers in a list--");
        return numbers.stream()
                .filter(number -> number % 2 ==1)
                //.reduce(0,(a,b) -> a+b);
                .reduce(0,Integer::sum);
    }

    private static void printDistintSortedCourses(List<String> courses) {
        System.out.println("--printDistintSortedCourses--");
        courses.stream().distinct().sorted().forEach(System.out::println);
    }

    //Create a List with Even Numbers Filtered from the Numbers List
    private static List<Integer> filterEvenNumbersToNewList(List<Integer> numbers) {
        return numbers.stream().filter(number -> number %2 == 0).collect(Collectors.toList());
    }

    //Create a List with lengths of all course titles.
    private static List<String> sortWithLenghtOfCourses(List<String> courses) {
        return courses.stream().sorted(Comparator.comparing(s -> s.length())).collect(Collectors.toList());
    }


}
