package org.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author mzr
 * @version 1.0
 */
public class Panel extends JPanel implements KeyListener {

    Hero hero = null;

    public Panel(){
        hero = new Hero(100,100);   //初始化
        hero.setSpeed(5);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        //坦克
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
    }

    /**
     *
     * @param x 坦克x坐标
     * @param y 坦克y坐标
     * @param g 画笔
     * @param direct    方向
     * @param type  类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        //类型
        switch(type){
            case 0: //我方
                g.setColor(Color.CYAN);
                break;
            case 1: //敌方
                g.setColor(Color.YELLOW);
                break;
        }
        //方向 0上1右2下3左
        switch (direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);//轮子
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);//身体
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);//炮筒
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);//轮子
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);//身体
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);//炮筒
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);//轮子
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);//身体
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);//炮筒
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);//轮子
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);//身体
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);//炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wasd键
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirect(0);
            hero.moveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirect(1);
            hero.moveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirect(2);
            hero.moveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirect(3);
            hero.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
