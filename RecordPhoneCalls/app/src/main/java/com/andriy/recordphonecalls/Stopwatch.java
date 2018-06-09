package com.andriy.recordphonecalls;

public class Stopwatch implements Runnable {

    public static Boolean stop;
    public static String time;

    @Override
    public void run() {
        int seconds = 0;

        while (!stop) {
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int second = seconds % 60;

            seconds++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            time = String.format("%02d:%02d:%02d", hours, minutes, second);
        }

        if (stop) {
            seconds = 0;
        }
    }
}
