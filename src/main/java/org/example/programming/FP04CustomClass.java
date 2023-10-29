package org.example.programming;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Course {
        private String name;
        private String category;
        private int reviewScore;
        private int noOfStudents;

        public Course(String name, String category, int reviewScore, int noOfStudents) {
            super();
            this.name = name;
            this.category = category;
            this.reviewScore = reviewScore;
            this.noOfStudents = noOfStudents;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getReviewScore() {
            return reviewScore;
        }

        public void setReviewScore(int reviewScore) {
            this.reviewScore = reviewScore;
        }

        public int getNoOfStudents() {
            return noOfStudents;
        }

        public void setNoOfStudents(int noOfStudents) {
            this.noOfStudents = noOfStudents;
        }

        public String toString() {
            return name + ":" + noOfStudents + ":" + reviewScore;
        }
    }
public class FP04CustomClass {
    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000));

        // allMatch, içine yazılan predicate ifadenin tüm stream içerisinde varsa truw yoksa false.
        System.out.println(
                courses.stream().allMatch(course -> course.getReviewScore() > 90) //true
        );
        Predicate<Course> reviewScoreGreaterThan95Predicate = course -> course.getReviewScore() > 90;
        System.out.println(courses.stream().allMatch(reviewScoreGreaterThan95Predicate));  //true


        // noneMatch, içine yazılan predicate ifadenin tüm stream içerisinde yoksa true varsa false
        Predicate<Course> reviewScoreLessThan90Predicate = course -> course.getReviewScore() < 90;
        System.out.println(courses.stream().noneMatch(reviewScoreLessThan90Predicate));  //true


        // anyMatches, içine yazılan predicate ifadeden herhangi bir eşleşme true döndürür.
        System.out.println(courses.stream().anyMatch(reviewScoreLessThan90Predicate));  //false


        //Sorting courses with sorted and creating comperators

        //getNoOfStudents sayısına göre nesneleri küçükten büyüğe doğru sıraladık.
        Comparator<Course> comparingByNoOfStudentsIncreasing = Comparator.comparing(Course::getNoOfStudents);
        System.out.println("comparingByNoOfStudents ASC");
        System.out.println(
                courses.stream().sorted(comparingByNoOfStudentsIncreasing).collect(Collectors.toList()));
        //[FullStack:14000:91, Spring Boot:18000:95, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, AWS:21000:92, Azure:21000:99, API:22000:97, Microservices:25000:96]

        //getNoOfStudents sayısına göre nesneleri reversed() ile büyükten küçüğe doğru sıraladık.
        Comparator<Course> comparingByNoOfStudentsDecreasing = Comparator.comparing(Course::getNoOfStudents).reversed();
        System.out.println("comparingByNoOfStudents DESC");
        System.out.println(
                courses.stream().sorted(comparingByNoOfStudentsDecreasing).collect(Collectors.toList()));
        //[Microservices:25000:96, API:22000:97, AWS:21000:92, Azure:21000:99, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]


        //getNoOfStudents sayıları aynı olan verilerde ek bir sıralama yapabilmek için
        Comparator<Course> comparingByNoOfStudentsAndNoOfReviews
                = Comparator.comparing(Course::getNoOfStudents).thenComparing(Course::getReviewScore);
        System.out.println("comparingByNoOfStudentsAndNoOfReviews "+
                courses.stream().sorted(comparingByNoOfStudentsAndNoOfReviews).collect(Collectors.toList())
        );
        //[FullStack:14000:91, Spring Boot:18000:95, Kubernetes:20000:91, Docker:20000:92, Spring:20000:98, AWS:21000:92, Azure:21000:99, API:22000:97, Microservices:25000:96]

        //Sorting işlemlerinde gereksiz autoboxing işlemleri yapmamak ve daha verimli olması için aşağıdaki gibi düzenleme yapabiliriz.
        Comparator<Course> comparingByNoOfStudentsAndNoOfReviewsNonBoxing
                = Comparator.comparingInt(Course::getNoOfStudents).thenComparingInt(Course::getReviewScore);
        System.out.println("comparingByNoOfStudentsAndNoOfReviews Efficent Way "+
                courses.stream().sorted(comparingByNoOfStudentsAndNoOfReviewsNonBoxing).collect(Collectors.toList())
        );
        //[FullStack:14000:91, Spring Boot:18000:95, Kubernetes:20000:91, Docker:20000:92, Spring:20000:98, AWS:21000:92, Azure:21000:99, API:22000:97, Microservices:25000:96]


        //limit(), içerisine yazılan miktar kadar veriyi getirir.
        System.out.println("comparingByNoOfStudentsAndNoOfReviews with limit(5) "+
                courses.stream().sorted(comparingByNoOfStudentsAndNoOfReviewsNonBoxing).limit(5).collect(Collectors.toList())
        );
        //[FullStack:14000:91, Spring Boot:18000:95, Kubernetes:20000:91, Docker:20000:92, Spring:20000:98]


        //skip(), içine yazılan sayı kadarını atlayıp işleme devam edecektir.
        System.out.println("comparingByNoOfStudentsAndNoOfReviews with Skip(3) "+
                courses.stream().sorted(comparingByNoOfStudentsAndNoOfReviewsNonBoxing).skip(3).collect(Collectors.toList())
        );
        //[Docker:20000:92, Spring:20000:98, AWS:21000:92, Azure:21000:99, API:22000:97, Microservices:25000:96]


        // takeWhile, içine yazılan logic kuralına kadar olan verileri yazar uymayan geldiğinde durur.
        System.out.println("comparingByNoOfStudentsAndNoOfReviews with takeWhile() "+
                courses.stream()
                        .takeWhile(course -> course.getReviewScore()>=95)
                        .collect(Collectors.toList())
        );
        //[Spring:20000:98, Spring Boot:18000:95, API:22000:97, Microservices:25000:96]


        //dropWhile(), akışın başlangıcından itibaren belirli bir koşulu sağlayan elemanları atlar ve koşulu sağlamayan
        // ilk elemandan itibaren geri kalan tüm elemanları içeren yeni bir koleksiyon veya akış döndürür.
        System.out.println("comparingByNoOfStudentsAndNoOfReviews with dropWhile() "+
                courses.stream()
                        .dropWhile(course -> course.getReviewScore()>=95)
                        .collect(Collectors.toList())
        );
        //[FullStack:14000:91, AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91]


    }
}

