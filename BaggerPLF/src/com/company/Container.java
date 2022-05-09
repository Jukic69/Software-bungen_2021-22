package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


class Container {
   private Queue<Stone> queue = new LinkedList<>();
   private final int MAX_WEIGHT = 1000;
   private int totalWeight;

    public Container() {
        totalWeight = 0;
    }

    public int totalweight() {
        return totalWeight;
    }

    public Stone remove() {
        Stone removed = queue.remove();
        totalWeight -= removed.getWeight();
        return removed;
    }

    public void add (Stone s) {
        queue.add(s);
        totalWeight += s.getWeight();
    }
}
