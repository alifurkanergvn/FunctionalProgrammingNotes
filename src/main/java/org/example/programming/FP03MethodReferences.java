package org.example.programming;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class FP03MethodReferences {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices", "AWS", "PCF","Azure", "Docker", "Kubernetes");

        courses.stream()
                //.map(str -> str.toUpperCase())
                .map(String::toUpperCase) //Class ismi String, method ismi toUpperCase. Burada objedeki methodu çağırıyoruz. (Method Referance on an instance method).
                .forEach(System.out::println); //Burada static method'u çağırıyoruz MethodReference ile

        //Method Referans ile constructor references ta yapılabilir.
        //Supplier<String> supplier = () -> new String();
        Supplier<String> supplier = String::new; //Constructor reference ile bu şekilde yeni objeler yapabilirsin.
    }
}
