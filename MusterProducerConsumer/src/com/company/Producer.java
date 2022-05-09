package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread{
    private String name;
    private Queue<Integer> queue;
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;
    private int maxQueueSize;
    private Random r;


    public Producer(String name, Queue<Integer> queue, ReentrantLock lock, Condition notFull, Condition notEmpty, int maxQueueSize) {
        this.name = name;
        this.queue = queue;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
        this.maxQueueSize = maxQueueSize;
        r = new Random();
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){
            work();
        }
    }

    private int produceBall() {
        int ballID;

        //Produktion
        ballID = r.nextInt(10);

        //Simuliere Produktionsdauer
        try {
            Thread.sleep(r.nextInt(501));
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("("+name+"): Wer wagt es?");
            interrupt();
        }
        return ballID;
    }

    private void work(){
        int ballID = produceBall();

        System.out.println("("+name+"): fertig produziert: " + ballID);

        //Zugriff auf Förderband -> kritischer Abschnitt
        lock.lock();

        //warte, bis Platz am Förderband ist
        while(queue.size() >= maxQueueSize){
            //Förderband voll
            System.out.println("("+name+"): Fließband voll! - Pause!");
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("("+name+"): Wer wagt es?");
            }
        }

        //Lege produzierten Ware aufs Band
        queue.add(ballID);
        System.out.println("("+name+"): am Band abgelegt: "+ballID);
        System.out.println("("+name+"): am Band: "+queue);

        //Aufwecken ALLER Consumer
        notEmpty.signalAll();
        lock.unlock();
    }
}