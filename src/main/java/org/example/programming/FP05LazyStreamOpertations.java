package org.example.programming;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FP05LazyStreamOpertations {
    public static void main(String[] args) {
        // Foksiyonel programlama kavramı yaklaşık 50 yıl önce çıkmasına rağmen bugünde populer olmasının sebeplerinden
        //biri performans olarak karşımıza çıkıyor. Functional programlama ile performanslı kod yazmak çok daha kolay.
        //Ayrıca prallelize işlemlerde functional kod yazmak structured koda göre daha kolay.

        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        // İncelemek için stream içerisinde istenilen uzunlukta veri varsa onu uppercase halini veren ve bu kurala uyan
        //ilk veriyi dönen bir kod yazalım.
        //Başına ve sonuna peek() methodu koyarak arka planda streamin değişimini inceleyelim.

        Optional<String> isLazyStream = courses.stream()
                .peek(System.out::println)
                .filter(i -> i.length() > 11)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .findFirst();

        System.out.println(isLazyStream);
        //Spring
        //Spring Boot
        //API
        //Microservices
        //MICROSERVICES
        //Optional[MICROSERVICES]

        // Yukarıdaki sonuca bakarak kurala uyan ilk element bulunduğuda diğer elementlere bakmadan bitiyor.
        //Tüm intermadiate operasyonlar peek, filter, map, gibi geriye bir stream döndürür bu ara operasyonları,
        //collect gibi terminal operasyon ile  çağrıldığında çalışacaktır:
        //Tüm stream intermadiate(ara) operasyonlarda Java Lazy'dir.

        // Java'da "lazy" kavramı, bir nesnenin veya değerin ihtiyaç duyulduğu anda oluşturulması veya hesaplanması
        //anlamına gelir. Lazy evaluation (tembel değerlendirme) olarak da bilinir. Bu, bir nesnenin veya değerin
        //başlangıçta oluşturulup hesaplanmaması, ancak ilk defa kullanıldığında oluşturulup hesaplanması anlamına gelir.

        //Sondaki findFirst() terminal operation'u çıkartırsak bir sonuç döndürmeyecektir.
        //Stream execute edilmeyecektir. Sadece stream'de terminal bir operasyon olduğunda execute olacaktır.
        //Java bu kodu sadece tam olarak beklenen sonucu bildiği zaman execute edecektir.
        Stream<String> stringStream = courses.stream()
                .peek(System.out::println)
                .filter(i -> i.length() > 11)
                .map(String::toUpperCase)
                .peek(System.out::println);

        System.out.println(stringStream);
        //java.util.stream.ReferencePipeline$11@26f67b76


        //Ara operasyonlar(Intermediate Operations
        //filter,map, flatMap, distinct, sorted

        //(Terminal Operasyonlar)
        //forEach(), toArray(), collect(), reduce(),count(),anyMatch(),allMatch(),noneMatch() ,findAny() ,findFirst()

    }
}
