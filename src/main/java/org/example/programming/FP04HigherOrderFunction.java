package org.example.programming;

import java.util.function.Predicate;

public class FP04HigherOrderFunction {
    public static void main(String[] args) {

        Predicate<Course> reviewScoreGreaterThan95Predicate
                = course -> course.getReviewScore() > 95;

        Predicate<Course> reviewScoreGreaterThan90Predicate
                = course -> course.getReviewScore() > 90;

        //Yukarıdaki halinin daha clean versiyonunu yazacağız

        //int cutoffReciewScore = 95; buna sağ tıklayıp direkt predicate'den methoda giden yerin içerisine variable'ı seçip sağ tık Refactor--Inline variable'dedi
        Predicate<Course> reviewScoreGreaterThan95Predicate2
                = createPredicateWithCutoffReviewScore(95);//Bir method yapıp bunu dönecek.
        // Üsttki satırı seç sağ tıkla Refactor--Extract Method de. Böylelikle alttaki method oluştu

        Predicate<Course> reviewScoreGreaterThan95Predicate3
                = createPredicateWithCutoffReviewScore(90);

    }

    //Higher order function döneceği değeri bir başka function'da döner
    private static Predicate<Course> createPredicateWithCutoffReviewScore(int cutoffReciewScore) {
        return course -> course.getReviewScore() > cutoffReciewScore;
    }

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
}
