package com.zzxx.fly;

import com.zzxx.game.I;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class FlyMain extends JPanel {
    public static BufferedImage background;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage airplane1;
    public static BufferedImage bee1;
    public static BufferedImage bigplane1;
    public static BufferedImage bullet1;
    public static BufferedImage pause1;
    public static BufferedImage start1;
    public static BufferedImage ending1;
    public static BufferedImage airplane_ember0;




    static{
        try {
            background = ImageIO.read(FlyMain.class.getResourceAsStream("scr/background.png"));
            hero0 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/hero0.png"));
            hero1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/hero1.png"));
            airplane1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/airplane.png"));
            bee1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/bee.png"));
            bigplane1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/bigplane.png"));
            bullet1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/bullet.png"));
            pause1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/pause.png"));
            start1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/start.png"));
            ending1 = ImageIO.read(FlyMain.class.getResourceAsStream("scr/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static final int WIDTH = 450;
    public static final int HEIGHT = 600;

    private java.util.Timer timer = new java.util.Timer();
    public void action(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state == RUNING){
                creatFlyingObject();
                flyingObjectMove();
                creatBullet();
                bulletShoot();
                flyingOutBounds();
                boomDisapper();
                heroDisappear();
                bee.move();
                airplane.move();
                bigplane.move();
                hero.move();
                repaint();}
            }
        },3000,100);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(state == START){
                    state = RUNING;
                }else if(state == ENDING){
                    state = START;
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == RUNING){
                    repaint();
                }else if(state == PAUSE){
                    state = RUNING;
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(state == RUNING){
                state = PAUSE;
                repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNING){
                int mouse_x = e.getX();
                int mouse_y = e.getY();
                hero.x = mouse_x - hero.width / 2;
                hero.y = mouse_y;
                repaint();
                }
            }
        };
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);

    }
    private int flyingIndex = 0;
    private int bulletCount = 0;
    private ArrayList<FlyingObject> flys = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Buried> burieds = new ArrayList<>();
    private final int START = 0;
    private final int RUNING = 1;
    private final int PAUSE = 2;
    private final int ENDING = 3;
    private int state = START;


    private void creatFlyingObject(){
        flyingIndex++;
        if(flyingIndex % 10 == 0){
            int ran = (int)(Math.random() * 20);
            FlyingObject fly;
            if(ran == 0){
                fly = new Bee();
            }else if(ran == 1 || ran == 2){
                fly = new BigPlane();
            }else {
                fly = new Airplane();
            }flys.add(fly);
        }
    }
    private void creatBullet(){
        bulletCount++;
        if(bulletCount % 5 == 0){
            Bullet[] b = hero.shoot();
            bullets.addAll(Arrays.asList(b));
            }
        }



    Hero hero = new Hero();
    Airplane airplane = new Airplane();
    Bee bee = new Bee();
    BigPlane bigplane = new BigPlane();
    Buried buried = new Buried();
    @Override
    public void paint(Graphics g) {
        if(state == RUNING){
            super.paint(g);
            g.drawImage(background, 0, 0, WIDTH,HEIGHT,this);
            g.drawImage(hero.image, hero.x, hero.y, this);
            g.drawString((""+ hero.getScore()),25,25);
            g.drawString(("" + hero.getLife()),25,75);
            flyingPaint(g);
            bulletPaint(g);
        }
        if(state == PAUSE){
            g.drawImage(pause1,0,0,this);
        }
        if(state == START){
            g.drawImage(start1,0,0,this);
        }
        if(state == ENDING){
            g.drawImage(ending1,0,0,this);
            hero.setLife(5);
            hero.setScore(0);
        }
    }
    public void flyingPaint(Graphics g){
        if(state == RUNING){
        for (int i = 0; i < flys.size(); i++) {
            FlyingObject fly = flys.get(i);
            g.drawImage(fly.image,fly.x,fly.y,fly.width,fly.height,this);
        }}
    }
    public void bulletPaint(Graphics g){
        if(state == RUNING){
        for (int i = 0; i < bullets.size(); i++){
                g.drawImage(bullets.get(i).image, bullets.get(i).x, bullets.get(i).y, bullets.get(i).width, bullets.get(i).height, this);
             }
        }
    }
    public void flyingObjectMove(){
        for (int i = 0; i <  flys.size(); i++) {
            flys.get(i).move();
        }
    }
    public void bulletShoot(){
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
        }
    }
    public void flyingOutBounds(){
        for (int i = 0; i < flys.size(); i++) {
            FlyingObject fly = flys.get(i);
            if(fly.y - fly.height > FlyMain.HEIGHT){
                flys.remove(i);
                i--;
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
                if(bullet.y <= -bullet.height){
                    bullets.remove(i);
                    i--;
                }

        }
    }
    public void boomDisapper(){
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < flys.size(); j++) {
                if(hero.knock(bullets.get(i).x,bullets.get(i).y,flys.get(j).x,flys.get(j).y,flys.get(j).width,flys.get(j).height)){
                    flys.get(j).blood --;
                    bullets.remove(i);
                    i--;
                    if(flys.get(j).blood == 0){
                        if(flys.get(j) instanceof Enemy) {
                            Enemy enemy = (Enemy) (flys.get(j));
                            hero.addScore(enemy.getscore());
                        }
                        if(flys.get(j) instanceof Award){
                            Award award = (Award)(flys.get(j));
                            if(award.getAwardType() == Award.ADD_LIFE){
                            hero.addLife();
                            }
                        else{hero.addDoubleFire();
                        }
                            }
                    flys.remove(j);
                    j--;
                    }
                   break;
                }

            }
        }
    }

    public void heroDisappear(){

        for (int i = 0; i < flys.size(); i++) {
            if((flys.get(i).x <= hero.x + hero.width)&&(flys.get(i).x + flys.get(i).width >= hero.x)
                    && (flys.get(i).y + flys.get(i).height >= hero.y) && (flys.get(i).y <= hero.y + hero.height)){
                 hero.setLife(hero.getLife() - 1 );
                 flys.remove(i);
                 i--;
                 if(hero.getLife() == 0){
                     state = ENDING;
                 }
            }
        }
    }



    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(FlyMain.WIDTH,FlyMain.HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlyMain main = new FlyMain();
        window.add(main);
        main.action();
        window.setVisible(true);
    }
}
