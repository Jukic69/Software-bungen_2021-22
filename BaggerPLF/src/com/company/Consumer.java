package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Consumer extends Thread{
    private String name;
    private Container container;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;
    //private Random r;


    public Consumer(String name, Container container, Lock lock, Condition notFull, Condition notEmpty) {
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
                e.printStackTrace();
            }
        }
    }

    private void work() throws InterruptedException {
        Stone s;
        try {
            lock.lock();
            while (container.totalweight() < 1){
                System.out.println("[" + this.name + "] Container is empty!");
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }
            }

            s = container.remove();

            notFull.signalAll();
        }finally {
            lock.unlock();
        }
        consume(s);
    }

    private void consume(Stone s) throws InterruptedException {
        System.out.println("[" + this.name + "] remove: " + s.getWeight() + "kg Total weight of Container: " + container.totalweight());
        Thread.sleep(1000);

    }
}
