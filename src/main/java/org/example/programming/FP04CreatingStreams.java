package org.example.programming;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FP04CreatingStreams {
    //Şu zamana kadar Stream'leri kullanmak için
    //List<Integer> numbers = List.of(12,14,10,9,21,16,22,31) şeklinde liste oluşturduk.
    //Java'da bunu direkt oluşturabilmemiz için Stream'i kullanabiliriz.

    public static void main(String[] args) {

        System.out.println(
                Stream.of(12, 14, 10, 9, 21, 16, 22, 31, 18).count()
        );//9

        System.out.println("sum numbers= " +
                Stream.of(12, 14, 10, 9, 21, 16, 22, 31, 18).reduce(0, Integer::sum)
        );//153

        //Yukarıdaki örneklerde primitive sayıları kullanmamıza rağmen Integer wrapper class elementleri oluşuyor.
        //reduce operasyonu ile boxing ve unboxing olması verimli değildir.
        //Stream'lerimizi primitive değerler ile oluşturmalıyız.

        //Bunun için array oluşturmak bir seçenektir.
        int[] numberArray = {12, 14, 10, 9, 21, 16, 22, 31, 18};

        Arrays.stream(numberArray);
        System.out.println(Arrays.stream(numberArray)); //java.util.stream.IntPipeline$Head@3498ed ,artık stream. ReferancePipeline değil. ReferancePipeline wrapper class'lar veya custom classes kullanıldığında oluşur

        Arrays.stream(numberArray).sum();
        System.out.println("sum numberArray= " + Arrays.stream(numberArray).sum()); //153

        System.out.println("max numberArray= " + Arrays.stream(numberArray).max()); //OptionalInt[31]
        System.out.println("average numberArray= " + Arrays.stream(numberArray).average()); //OptionalDouble[17.0]


        //primitve streamleri array kullanarak oluşturduk ama bu pek dinamik değil.
        //Daha dinamik ve spesifik stream'ler oluşturmak için
        System.out.println(IntStream.range(1, 10)); //java.util.stream.IntPipeline$Head@29ee9faa, primitive stream olduğunu gördük
        System.out.println("sum dynamic stream= " + IntStream.range(1, 10).sum()); //sum dynamic stream= 45

        //range() içerisine yazılan 10'a kadar anlamında dır 10 dahil değildir. Dahil olamsını istesen rangeClosed()
        System.out.println("sum dynamic stream= " + IntStream.rangeClosed(1, 10).sum()); //sum dynamic stream= 55

        //Daha dinamik stream'ler oluşturmak için iterate()  başlangıç değeri (seed) ve bir işlemi (UnaryOperator) alır ve başlangıç değeri üzerinde ardışık işlemler gerçekleştirerek bir akış oluşturur.
        // Yani, bu işlem, bir başlangıç değerinden başlayarak ardışık olarak bir işlemi uygulayarak bir akış oluşturur. bu sonsuza gider içindeki logic sınırlandır .limit() ile
        //peek(), akışı değiştirmez ve öğeler üzerinde dönüş yapmaz. Bu nedenle, genellikle hata ayıklama veya günlükleme gibi işlemler için kullanılır.Her stream adımında ne yapıldığını gördük
        int sumDynamicValueWithIterate = IntStream.iterate(1, e -> e + 2).limit(10).peek(num -> System.out.println("Processing: " + num)).sum();
        System.out.println(sumDynamicValueWithIterate);//100

        //her sayı bir sonraki sayının 2 ile çarpımı olacak şekilde bir akış oluşturalım
        int sumMultiplyTwoValue = IntStream.iterate(1, e -> e * 2).limit(10).peek(num -> System.out.println("Processing: " + num)).sum();
        System.out.println(sumMultiplyTwoValue);//1023

        //Şimdiye kadar stream'lerimizi primitive olarak oluşturduk peki bunu ihtiyaç halinde List'e çevirmek istersek.
        //boxed() kullanarak primitive halinden tiplerini nesne türüne çevirir.Örneğin, int bir ilkel veri türdür, ancak Integer bir kutulu (boxed) veri türdür.
        List<Integer> multiplyTwo = IntStream.iterate(1, e -> e * 2).limit(10).boxed().collect(Collectors.toList());
        System.out.println("multiply Two List= " + multiplyTwo);


        //Büyük veriler ile işimiz olduğunda nasıl stream'ler oluşturmalıyız
        //Integer.MAX_VALUE=214748647'dir bu sınıra aşan işlemlerde aşağıdaki gibi yapmalıyız
        //Mesela 50! işleminin sonucunu bulmak isteyelim
        int factorielOfFive = IntStream.rangeClosed(1, 5).peek(System.out::println).reduce(1, (x, y) -> x * y);
        System.out.println(factorielOfFive);//120

        int factorialResult = IntStream.rangeClosed(1, 50).reduce(1, (x, y) -> x * y);
        System.out.println(factorialResult); //int 50! için yetmedi bu sebeple 0 sonuç verdi

        //Long için denersek
        long longFactorial = LongStream.rangeClosed(1, 50).reduce(1, (x, y) -> x * y);
        System.out.println("long "+longFactorial); //-3258495067890909184 verdi buda yetmedi

        //BigInteger ile denersek, büyük numerik operasyonlarda kullanım örneğidir.
        BigInteger fiftyFactorial = LongStream.rangeClosed(1, 50).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
        System.out.println("BigInteger "+fiftyFactorial);
        //BigInteger 30414093201713378043612608166064768844377641568960512000000000000

    }
}
