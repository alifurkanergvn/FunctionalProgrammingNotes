package org.example.programming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FP06Files {
    public static void main(String[] args) throws IOException {
        //File'dan veri okuma
        Files.lines(Paths.get("TestFile.txt")).forEach(System.out::println);
        //Some Text
        //Some Text that will be saved
        //A lot of Text Present in here

        //File içerisindeki  tekrarsız kelimeleri alfabetik olarak yazdırmak.
        Files.lines(Paths.get("TestFile.txt"))
                .map(str -> str.split(" ")) // Her boşluk sonrası her satır için String array'e döndürmüş olduk. Böylece elimizde string arraylerin streami olmuş oldu
                .flatMap(Arrays::stream)//Tüm string arrayleri bir stream string arrayine çeviriyoruz
                .distinct()
                .sorted()
                .forEach(System.out::println);
        //A
        //Present
        //Some
        //Text
        //be
        //here
        //in
        //lot
        //of
        //saved
        //that
        //will

        //Proje root'undaki tüm files ve klasörleri yazdıralım
        System.out.println("\r");
        Files.list(Paths.get(".")).forEach(System.out::println);
        //.\.git
        //.\.idea
        //.\pom.xml
        //.\src
        //.\target
        //.\TestFile.txt

        System.out.println("\r"+"Write only directory: ");
        //Sadece directory(dosyaları) yazdırmak için
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
        //Write only directory:
        //.\.git
        //.\.idea
        //.\src
        //.\target

        //Root klasöründen bir kez geri gidince olan tüm dosyaları yazdır.
        System.out.println("\r");
        Files.list(Paths.get(".."))
                .forEach(System.out::println);
        //..\oz-functional-prog
        //..\stream-tests
        //..\Udemy_Project

        //..\Udemy_Project içerisnde bulunan dosyaları yazdır
        System.out.println("\r");
        Files.list(Paths.get("../Udemy_Project"))
                .forEach(System.out::println);

    }
}
