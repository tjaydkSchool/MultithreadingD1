/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import static java.lang.Thread.sleep;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dennis
 */
public class MultiThreading {

    public static void main(String[] args) {

        try {
            Task1 t1 = new Task1();
            Task2 t2 = new Task2();
            Task3 t3 = new Task3();
            
            t1.start();
            t2.start();
            t3.start();
            sleep(10000);
            t3.running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiThreading.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static class Task1 extends Thread {

        @Override
        public void run() {
            long number = 0;

            for (int i = 1; i <= 1000000000; i++) {
                number += i;
            }
            
                System.out.println("Task1: " + number);

        }

    }

    public static class Task2 extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    System.out.println("Task2: " + i);
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiThreading.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static class Task3 extends Thread {

        
            int number = 10;
            public volatile boolean running = true;

            @Override
            public void run() {
                try {
                    while(running) {
                    System.out.println("Task3: " + number);
                    number++;
                    sleep(3000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiThreading.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    }

}
