package com.company;

import java.util.Random;

class Stone {
    private int w;
    Random r;

    public Stone(){
        r = new Random();
        this.w = r.nextInt(491) + 10;
    }

    public Stone(int weight) {
        w = weight;
    }

    public int getWeight() {
        return w;
    }
}
