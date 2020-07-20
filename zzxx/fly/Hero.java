package com.zzxx.fly;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero extends FlyingObject{
    private int life;
    private int score;
    private Bullet[] bullets;
    private int count = 0;
    private int doubleFire = 0;
    private BufferedImage[]  heroImg = {FlyMain.hero0,FlyMain.hero1};
    public Hero(){
        x = 175;
        y = 300;
        image = FlyMain.hero0;
        height = image.getHeight();
        width = image.getWidth();
        life = 5;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected void move() {
        count++;
        image = heroImg[count % 2];
    }
    public Bullet[] shoot(){
        Bullet[] bullets;
        if(doubleFire == 0){
            bullets = new Bullet[1];
            bullets[0] = new Bullet(this.x + this.width / 2 - 3,this.y - 10);
        }else{
            bullets = new Bullet[2];
            bullets[0] = new Bullet(this.x + this.width / 4 - 3,this.y - 10);
            bullets[1] = new Bullet(this.x + (this.width * 3 ) / 4  - 3,this.y - 10);
            doubleFire --;
        }
        return bullets;
    }
    public void addScore(int score){
        this.setScore(this.getScore() + score);
    }
    public void addLife(){
        this.setLife(this.getLife() + 1);
    }
    public void addDoubleFire(){
        doubleFire += 10;
    }
    public boolean knock(int bullet_x,int bullet_y,int flys_x,int flys_y,int flys_width,int flys_height){
        if((bullet_x <= (flys_x + flys_width)) &&
                (bullet_x >= flys_x)  && (bullet_y <= flys_y + flys_height) && (bullet_y >= flys_y)){
            return true;
        }
        else {
            return false;
        }}
}
