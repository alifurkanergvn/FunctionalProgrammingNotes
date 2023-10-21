package org.example.programming;

import java.util.List;

public class FP01Exercise {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        printListOnlyOddNumber(numbers);

        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices", "AWS", "PCF","Azure", "Docker", "Kubernetes");
        printAllCoursesIndividually(courses);
        printOnlyContainSpring(courses);
        printCoursesEqualAndGreaterFourLetter(courses);
        printNumberOfCharactersCourses(courses); //Map

    }

    private static void printListOnlyOddNumber(List<Integer> numbers) {
        System.out.println("---Exercise 1---");
        numbers.stream().filter(integer -> integer %2 == 1)
                .forEach(System.out::println);
    }

    private static void printAllCoursesIndividually(List<String> courses) {
        System.out.println("---Exercise 2---");
        courses.stream().forEach(System.out::println);
    }

    private static void printOnlyContainSpring(List<String> courses) {
        System.out.println("---Exercise 3---");
        courses.stream().filter(course-> course.contains("Spring"))
                .forEach(System.out::println);
    }

    private static void printCoursesEqualAndGreaterFourLetter(List<String> courses) {
        System.out.println("---Exercise 4---");
        courses.stream().filter(course->course.length() >= 4)
                .forEach(System.out::println);
    }

    private static void printNumberOfCharactersCourses(List<String> courses) {
        System.out.println("---Print the number of characters in each course name---");
        courses.stream()
                .map(course -> course.length())
                .forEach(System.out::println);
    }





}
