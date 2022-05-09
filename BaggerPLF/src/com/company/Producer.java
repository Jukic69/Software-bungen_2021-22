package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Producer extends Thread{
    private String name;
    private Container container;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;
    //private Random r;

    public Producer(String name, Container container, Lock lock, Condition notFull, Condition notEmpty) {
        super(name);
        this.container = container;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){
            try {
                work();
            } catch (InterruptedException e) {
                interrupt();
                e.printStackTrace();
            }
        }
    }

    private Stone produce() throws InterruptedException{
        Stone s = new Stone();

        //Warten 500ms.
        Thread.sleep(500);
        return s;
    }

    public void work() throws InterruptedException {
        Stone newStone = produce();
        System.out.println("");
        try {
            lock.lock();
            while (container.totalweight() + newStone.getWeight() > 1000){
                notFull.await();
            }
            container.add(newStone);
            System.out.println("["+ this.getName() + "] add:" + newStone.getWeight() + "kg Total weight of Container: " + container.totalweight() + " kg");
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
