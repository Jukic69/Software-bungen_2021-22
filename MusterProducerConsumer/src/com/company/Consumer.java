package com.company;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread{
    private String name;
    private Queue<Integer> queue;
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;
    private Random r;

    public Consumer(String name, Queue<Integer> queue, ReentrantLock lock, Condition notFull, Condition notEmpty) {
        this.name = name;
        this.queue = queue;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
        r = new Random();
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){
            work();
        }
    }

    private void work() {
        int ballID;

        //Zugriff aufs Förderband -> kritischer Abschnitt
        lock.lock();

        //warte, falls Förderband leer ist
        while (queue.size() < 1){
            System.out.println("("+name+"): Förderband ist leer, Pause!");
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("("+name+"): Wer wagt es?");
                interrupt();
            }
        }

        //nimm Ware vom Förderband
        ballID = queue.remove();

        //Produzent aufwecken
        notFull.signalAll();
        lock.unlock();

        consume(ballID);
    }

    private void consume(int ballID) {
        //simuliere Verarbeitung
        System.out.println("("+name+"): "+ballID+" vom Band genommen");
        try {
            Thread.sleep(r.nextInt(501)+500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("("+name+"): Wer wagt es?");
            interrupt();
        }
    }


}
