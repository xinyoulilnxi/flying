package com.zzxx.fly;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("飞机大战");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭
        window.setSize(400,650);//窗口大小
   //     window.setLocationRelativeTo(null);//窗口居中
        MyPanel myPanel = new MyPanel();
        window.add(myPanel);
        //window.setAlwaysOnTop(true);//设置置顶
        //window.setUndecorated(true);//设置窗口没有外边框
        window.setVisible(true);//窗口可见


    }

}
class MyPanel extends Panel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font font = new Font("宋体",Font.BOLD,30);
        Color color = new Color(116,0,120);
        g.setFont(font);
        g.setColor(color);
        //g.drawString("zengchenyang",50,50);
        paintBackground(g);
    }

    public void paintBackground(Graphics g){

        BufferedImage img = null;

        try {
           img = ImageIO.read(MyPanel.class.getResourceAsStream("background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img,0,0,this);
        paintHero(g);
    }
    public void paintHero(Graphics g){
        Airplane a = new Airplane();
        BufferedImage img = null;
        img = FlyMain.hero0;
        g.drawImage(img,100,200,this);


    }
}
