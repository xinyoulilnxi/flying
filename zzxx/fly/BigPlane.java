package com.zzxx.fly;

public class BigPlane extends FlyingObject implements Enemy,Award{
    private int score;
    private int awardtype;
    public BigPlane(){
        image = FlyMain.bigplane1;
        height = image.getHeight();
        width = image.getWidth();
        x = (int)((FlyMain.WIDTH  - width) * Math.random());
        y = -height;
        blood = 5;
        speed = 12;
        score = 5;
        awardtype = (int)(Math.random() * 2);
    }

    @Override
    protected void move() {

        y = y + speed;


    }

    @Override
    public int getscore() {
        return score;
    }

    @Override
    public int getAwardType() {
        return awardtype;
    }
}
