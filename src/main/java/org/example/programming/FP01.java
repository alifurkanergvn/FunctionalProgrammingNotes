package org.example.programming;

import java.util.List;

public class FP01 {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        //printAllNumbersInListStrucreted(List.of(12,9,13,4,6,2,4,12,15)); //Eski usul
        //printAllNumbersInListFunctional(List.of(12,9,13,4,6,2,4,12,15)); //Functional şekli
        //printEvenNumbersInListStructured(numbers); //Eski usul
        //printEvenNumbersInListFunctional(numbers); //Filter
        printSquaredOfEvenNumbersInListFunctional(numbers); //Map
    }

    private static void printAllNumbersInListStrucreted(List<Integer> numbers) {
        //Eski usul
        for(int number:numbers){
            System.out.println(number);
        }
    }

//    private static void print(int number) {
//        System.out.println(number);
//    }

    //Method Referance
    private static void printAllNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .forEach(System.out::println);//.forEach(FP01Structered::print);
    }
    private static void printEvenNumbersInListStructured(List<Integer> numbers) {
        // Eski usul
        for (int number : numbers) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
        }
    }

    //Filter Kullanımı

    private static void printEvenNumbersInListFunctional(List<Integer> numbers){
        numbers.stream()
                //.filter(FP01Structered::isEven) //Burası true gelirse devam eder forEach'e
                .filter(number -> number%2==0) //Burası true gelirse devam eder forEach'e
                //filter içinde ilk number'ı isEven methodunun parametresi, oktan sonrası method içinde parametre ile yapılacak işlem gibi düşünebiliriz
                //Ayrıca bunu sağlayan Lamda Expressiondur. Method içinde yapılabileceğimiz işlemi onun sayesinde kısa bir şekilde yapmış oluyoruz.
                .forEach(System.out::println); //Method Reference kullandık
    }

//    private static boolean isEven(int number){
//        return number%2==0;
//    }

    //Map kullanımı -- Bir değeri başka değere çevirirken kullandık
    private static void printSquaredOfEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream().map(number -> number * number)
                .forEach(System.out::println);
    }
}
