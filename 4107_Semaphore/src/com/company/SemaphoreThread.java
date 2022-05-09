package com.company;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreThread extends Thread{
    private final Semaphore semaphore;

    public SemaphoreThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        while (!isInterrupted()){
            //Semaphore anfordern
            try {
                semaphore.acquire();
                try {
                    System.out.println("Thread = " + currentThread().getName() + ", available permits= " + semaphore.availablePermits());
                    //Hier könnte InterruptedEx auftreten!
                    TimeUnit.SECONDS.sleep(2);
                }finally {
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt(); //interrupt zurücksetzen -> Schleifenabbruch
            }
        }
    }
}
