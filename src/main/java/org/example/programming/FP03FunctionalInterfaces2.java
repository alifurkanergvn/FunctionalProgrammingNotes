package org.example.programming;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FP03FunctionalInterfaces2 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> isEvenPredicate = (Integer x) -> x % 2 == 0;

        Function<Integer, Integer> squareFunction = x -> x * x;

        Function<Integer, String> stringOutpuFunction = x -> x + " ";

        Consumer<Integer> sysoutConsumer = x -> System.out.println(x);

        BinaryOperator<Integer> sumBinaryOperator = (x, y) -> x + y;

        //No input > Return Something
        Supplier<Integer> randomIntegerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };
        //System.out.println(randomIntegerSupplier.get());  //Supplier sadece get() methoduna sahip
        Supplier<Integer> integerSupplier = () -> 2;
//      System.out.println(integerSupplier.get());


        UnaryOperator<Integer> unaryOperator = x -> 3 * x;
        System.out.println(unaryOperator.apply(10));


        //Predice tek input iken bunda iki input alabilir.Boolean döner.
        BiPredicate<Integer, String> biPredicate = (number, str) -> {
            return number<10 && str.length()>5;
        };
        System.out.println(biPredicate.test(10, "AliFurkan"));

        //2 input alır, 1 output döner. Output tipini de 3. olarak belirtmelisin
        BiFunction<Integer, String, String> biFunction = (number, str) -> {
            return number + " " + str;
        };
        System.out.println(biFunction.apply(15, "AliFurkan"));


        //2 input alır void değer döndür.
        BiConsumer<Integer, String> biConsumer = (s1, s2) -> {
            System.out.println(s1);
            System.out.println(s2);
        };
        biConsumer.accept(25, "in28Minutes");


        //Inputların int(primitive) olacağından eminsen IntBinaryOperator kullanman daha effective olur.
        //Gereksiz yere arka planda boxing ve unboxing işleminden kurltulmuş olursun.
        BinaryOperator<Integer> sumBinaryOperator2 = (x, y) -> x + y;

        IntBinaryOperator intBinaryOperator = (x, y) -> x + y;

        //IntBinaryOperator
        //IntConsumer
        //IntFunction
        //IntPredicate
        //IntSupplier
        //IntToDoubleFunction
        //IntToLongFunction
        //IntUnaryOperator

        //Long, Double, Int


        //numbers.stream().filter(isEvenPredicate).map(squareFunction).forEach(sysoutConsumer);

        //int sum = numbers.stream().reduce(0, sumBinaryOperator);
    }
}
