package com.examples.threads.starvation;

import com.examples.threads.ThreadColor;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread firstThread = new Thread(new Runner(ThreadColor.ANSI_PURPLE), "Thread priority 10");
        Thread secondThread = new Thread(new Runner(ThreadColor.ANSI_BLUE), "Thread priority 7");
        Thread thirdThread = new Thread(new Runner(ThreadColor.ANSI_RED), "Thread priority 4");
        Thread fourthThread = new Thread(new Runner(ThreadColor.ANSI_GREEN), "Thread priority 1");

        firstThread.setPriority(10);
        secondThread.setPriority(7);
        thirdThread.setPriority(4);
        fourthThread.setPriority(1);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
    }

    private static class Runner implements Runnable {
        private int runCount = 1;
        private String color;

        public Runner(String color) {
            this.color = color;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 50; i++) {
                    System.out.format(color + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                }
            }
        }
    }
}
