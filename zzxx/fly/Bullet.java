package com.zzxx.fly;

public class Bullet extends FlyingObject {
    public Bullet(int x,int y){
        this.x = x;
        this.y = y;
        image = FlyMain.bullet1;
        height = image.getHeight();
        width = image.getWidth();

    }
    @Override
    protected void move() {
        speed = 10;
        y = y - speed;

    }

}
