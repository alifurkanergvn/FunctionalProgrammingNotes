package org.example.programming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FP05JoiningStrings {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        List<String> courses2 = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        //Her List elemanını boşluk ile ayırma
        String seperateWithSpacee = courses.stream().collect(Collectors.joining(""));
        System.out.println("Seperating with space, " + seperateWithSpacee);
        //SpringSpring BootAPIMicroservicesAWSPCFAzureDockerKubernetes

        //Her List elemanını boşluk ile ayırma
        String seperateWithSpace = courses.stream().collect(Collectors.joining(" "));
        System.out.println("Seperating with space, " + seperateWithSpace);
        //Seperating with space, Spring Spring Boot API Microservices AWS PCF Azure Docker Kubernetes

        String seperatingWithComma = courses.stream().collect(Collectors.joining(", "));
        System.out.println(seperatingWithComma);
        //Spring, Spring Boot, API, Microservices, AWS, PCF, Azure, Docker, Kubernetes

        //.split("") kullanımı
        String[] splitArray = "Spring".split("");
        //String[6] {"S", "p", "r", "i", "n", "g"}

        List<String[]> splitList = courses.stream().map(course -> course.split("")).collect(Collectors.toList());
        System.out.println(splitList);
        //[[Ljava.lang.String;@3fb6a447, [Ljava.lang.String;@79b4d0f,
        //Output'un yukarıdaki gibi olmasının sebebi map işlemi ile geriye string arrayin streamini döndürmesidir.
        //Bunun çıktısı ["S","p","r","i","n","g"],["A","W","S"] olacaktı ama biz tüm bunları tek array içerisinde
        //olmasını istiyoruz.["S","p","r","i","n","g","A","W","S"] bunu sağlayan flatMap'tir.

        //flatMap() işlemi, iç içe geçmiş koleksiyonlar veya yapıları işlerken özellikle kullanışlıdır.
        // İç içe geçmiş koleksiyonlardan veya yapıları tek bir düzleştirilmiş Stream haline getirmek istediğinizde kullanabilirsiniz
        List<String> splitListWithFlatMap = courses.stream().map(course -> course.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(splitListWithFlatMap);
        //[S, p, r, i, n, g, S, p, r, i, n, g,  , B, o, o, t, A, P, I, M, i, c, r, o, s, e, r, v, i, c, e, s, A, W, S, P, C, F, A, z, u, r, e, D, o, c, k, e, r, K, u, b, e, r, n, e, t, e, s]


        //Courses ve courses2 yi  birleştiren bir Stream yazalım.
        List<List<String>> collectTuplesInList =
                courses.stream().flatMap(s -> courses2.stream().map(course2 -> List.of(s, course2))).collect(Collectors.toList());
        System.out.println(collectTuplesInList);
        //[[Spring, Spring], [Spring, Spring Boot], [Spring, API], ... , [Kubernetes, Kubernetes]]

        //Yukarıdaki örnekte aynı elementin 2 kez tekrar ettiğini gördük örneğin [Spring, Spring], [AWS, AWS]
        //ilk liste elementinin ikici linste elementi ile aynı olduğu durumları filtreleyelim.

        List<List<String>> collectInListFilteredSameTuples =
                courses.stream()
                        .flatMap(s -> courses2.stream().map(course2 -> List.of(s, course2)))
                        .filter(list -> !list.get(0).equals(list.get(1)))
                        .collect(Collectors.toList());
        System.out.println("\n" +collectInListFilteredSameTuples);
        //[[Spring, Spring Boot], [Spring, API], ..., [Kubernetes, Docker]]


        //Sadece aynı lenght() değerine sahip olan Tuples'ları yazdıralım.
        List<List<String>> collectInListFilteredSameLenghtTuples =
                courses.stream()
                        .flatMap(courses1 -> courses2.stream()
                                .filter(course2 -> course2.length()==courses1.length())
                                .map(course2 -> List.of(courses1, course2)))
                        .filter(list -> !list.get(0).equals(list.get(1)))
                        .collect(Collectors.toList());
        System.out.println("\n" +collectInListFilteredSameLenghtTuples);
        //[[Spring, Docker], [API, AWS], [API, PCF], [AWS, API], [AWS, PCF], [PCF, API], [PCF, AWS], [Docker, Spring]]
    }
}
