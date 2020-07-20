package com.zzxx.fly;

import java.util.Arrays;

public class Bee extends FlyingObject implements Award{
    private int awardtype;
    int x_speed;
    public Bee(){
        image = FlyMain.bee1;
        height = image.getHeight();
        width = image.getWidth();
        x = (int)((FlyMain.WIDTH  - width) * Math.random());
        y = -height;
        blood = 3;
        speed = 20;
        x_speed = 20;
        awardtype = (int)(Math.random() * 2);
    }
    @Override
    protected void move() {
        y = y + speed;
        x = x + x_speed;
        if(x + width >= FlyMain.WIDTH){
            x_speed = -20;
        }else if(x <= 0) {
            x_speed = 20;
        }
    }
    public void reward(FlyingObject flyingObject){
        Arrays a[] = new Arrays[2];
    }

    @Override
    public int getAwardType() {
        return awardtype;
    }
}