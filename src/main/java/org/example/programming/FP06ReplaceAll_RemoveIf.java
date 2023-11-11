package org.example.programming;

import java.util.ArrayList;
import java.util.List;

public class FP06ReplaceAll_RemoveIf {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices", "AWS", "PCF","Azure", "Docker", "Kubernetes");

        //Tüm listeyi büyük harfe çevir
        //List.of ile listeyi ürettiğimiz için bu ummatable collection'dur bunu yeni bir arrayList ile değiştirmeliyiz.
        List<String> modifableCourses = new ArrayList<>(courses);
        System.out.println("Before change: "+modifableCourses);
        modifableCourses.replaceAll(String::toUpperCase);
        System.out.println("After change: "+modifableCourses);

        //Output
        //Before change: [Spring, Spring Boot, API, Microservices, AWS, PCF, Azure, Docker, Kubernetes]
        //After change: [SPRING, SPRING BOOT, API, MICROSERVICES, AWS, PCF, AZURE, DOCKER, KUBERNETES]

        //6 harften az olanları çıkaralım listemizden
        modifableCourses.removeIf(course -> course.length()<6);
        System.out.println("After remove: "+modifableCourses);
        //After remove: [SPRING, SPRING BOOT, MICROSERVICES, DOCKER, KUBERNETES]

    }
}
