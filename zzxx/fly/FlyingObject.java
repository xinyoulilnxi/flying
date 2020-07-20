package com.zzxx.fly;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed;
    protected int blood;
    protected BufferedImage image;
    protected abstract void move();
}
