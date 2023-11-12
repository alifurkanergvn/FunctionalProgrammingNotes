package org.example.programming;

import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class FP06Threads {
    public static void main(String[] args) {

        Runnable runnableStrucured = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getId()+":"+ i);
                }
            }
        };

        //lambda expression yardımıyla yazmış olduk.
        Runnable runnableFunctional = () -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getId()+":"+ i);
            }
        };

        //Functional version 2
        Runnable runnableFunctional2 = ()->
        IntStream.range(0,10000).forEach(value -> System.out.println(Thread.currentThread().getId()+":"+value));

        Thread thread = new Thread( runnableFunctional2);
        thread.start();

        Thread thread1 = new Thread( runnableFunctional2);
        thread1.start();

        Thread thread2 = new Thread( runnableFunctional2);
        thread2.start();

        //14:0
        //15:0
        //16:0
        //15:1
        //14:1
        //15:2
        //16:1
        //15:3
        //14:2
        //15:4

    }
}
