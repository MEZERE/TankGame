package org.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author mzr
 * @version 1.0
 */
public class Panel extends JPanel {

    Hero hero = null;

    public Panel(){
        hero = new Hero(100,100);   //初始化
    }

    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        //坦克
        drawTank(hero.getX(),hero.getY(),g,0,0);
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
        //方向
        switch (direct){
            case 0: //上
                //轮子
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                //身体
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                //炮筒
                g.drawLine(x+20,y+30,x+20,y);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
