/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingq2;

/**
 *
 * @author Dennis
 */
public class MultiThreadingQ2 {

    public static Object lock = new Object();

    public static void main(String[] args) {
        Even even = new Even();

//        UNSYNCHRONIZED THREADS
//        myThreadUnSync t1 = new myThreadUnSync(even);
//        myThreadUnSync t2 = new myThreadUnSync(even);
//        SYNCHRONIZED THREADS
        myThreadSync t1 = new myThreadSync(even);
        myThreadSync t2 = new myThreadSync(even);

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();
        t2.start();
    }

    public static class myThreadUnSync extends Thread {

        private Even even;
        public volatile boolean isEven = true;

        public myThreadUnSync(Even even) {
            this.even = even;
        }

        @Override
        public void run() {
            int numberEven = 0;

            while (isEven) {
                if (numberEven % 2 != 0) {
                    System.out.println(this.getName() + ": Got an uneven number " + numberEven);
                    isEven = false;
                } else {
                    numberEven = even.next();
                }
            }
        }
    }

    public static class myThreadSync extends Thread {

        private Even even;
        public volatile boolean isEven = true;

        public myThreadSync(Even even) {
            this.even = even;
        }

        @Override
        public void run() {
            int numberEven = 0;

            synchronized (lock) {
                while (isEven) {
                    if (numberEven % 2 != 0) {
                        System.out.println(this.getName() + ": Got an uneven number " + numberEven);
                        isEven = false;
                    } else {
                        numberEven = even.next();
                    }
                }
            }
        }
    }

//    THIS IS THE EVEN CLASS
    public static class Even {

        private int n = 0;

        public int next() {
            n++;
            n++;
            return n;
        }
    }

}
