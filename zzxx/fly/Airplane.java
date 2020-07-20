package com.zzxx.fly;

import javax.imageio.ImageIO;
import java.io.IOException;

public  class Airplane extends FlyingObject implements Enemy {
    public void setScore(int score) {
        this.score = score;
    }

    int score;

    public Airplane() {
        image = FlyMain.airplane1;
        height = image.getHeight();
        width = image.getWidth();
        x = (int) ((FlyMain.WIDTH - width) * Math.random());
        y = -height;
        blood = 1;
        speed = 15;
        score = 1;
    }

    @Override
    protected void move() {

        y = y + speed;

    }


    @Override
    public int getscore() {
        return score;
    }
}

