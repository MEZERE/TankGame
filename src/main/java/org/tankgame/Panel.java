package org.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author mzr
 * @version 1.0
 */
public class Panel extends JPanel implements KeyListener,Runnable {
    Hero hero = null;
    //存放敌人
    Vector<Enemy> enemys = new Vector<>();
    //存放炸弹
    Vector<Bomb> bombs = new Vector<>();
    int enemtNum = 3;
    //爆炸图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public Panel(){
        hero = new Hero(200,100);   //初始化
        hero.setSpeed(5);
        for (int i = 0; i < enemtNum; i++) {
            Enemy enemy = new Enemy(100 * (i + 1), 0);
            //方向
            enemy.setDirect(2);
            //启动
            new Thread(enemy).start();
            //bullet
            Bullet bullet = new Bullet(enemy.getX()+20,enemy.getY()+60,enemy.getDirect());
            enemy.bullets.add(bullet);
            //敌人射击
            new Thread(bullet).start();
            enemys.add(enemy);
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        //初始化图片
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_3.png"));
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        //我方子弹
        for(Bullet bullet : hero.bullets){
            if(bullet != null && bullet.isLive){
                drawBullet(bullet.x,bullet.y,g,0);
            }else {
                hero.bullets.remove(bullet);
            }
        }
        //我方坦克
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        //敌方坦克
        for (Enemy enemy : enemys) {
            if(enemy.isLive){
                drawTank(enemy.getX(),enemy.getY(),g,enemy.getDirect(),1);
                for (int i = 0; i < enemy.bullets.size(); i++) {
                    Bullet bullet = enemy.bullets.get(i);
                    if(bullet != null && bullet.isLive){
                        //敌方子弹
                        drawBullet(bullet.x,bullet.y,g,1);
                    } else {
                        enemy.bullets.remove(bullet);
                    }
                }
            }else {
                enemys.remove(enemy);
            }
        }
        //炸弹特效
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            } else if (bomb.life > 3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            } else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }
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
                g.setColor(Color.YELLOW);
                break;
            case 1: //敌方
                g.setColor(Color.CYAN);
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

    public void drawBullet(int x,int y,Graphics g,int type){
        switch(type){
            case 0: //我方
                g.setColor(Color.YELLOW);
                break;
            case 1: //敌方
                g.setColor(Color.CYAN);
                break;
        }
        g.draw3DRect(x,y,1,1,false);
    }

    public void hitTankBullets(Vector<Bullet> bullets){
        for(Bullet bullet : bullets){
            //判读是否击中
            if(bullet !=null && bullet.isLive){
                for (Enemy enemy : enemys){
                    hitTank(bullet,enemy);
                }
            }
        }
    }
    //判断子弹是否击中敌人
    public void hitTank(Bullet b,Enemy enemy){
        switch (enemy.getDirect()){
            case 0: //向上向下
            case 2:
                if (b.x > enemy.getX() && b.x < enemy.getX() + 40 && b.y > enemy.getY() && b.y < enemy.getY() + 60){
                    b.isLive = false;
                    enemy.isLive = false;
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: //向右向左
            case 3:
                if (b.x > enemy.getX() && b.x < enemy.getX() + 60 && b.y > enemy.getY() && b.y < enemy.getY() + 40){
                    b.isLive = false;
                    enemy.isLive = false;
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //处理wasd键
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
        //处理J键
        if(e.getKeyCode() == KeyEvent.VK_J){
            hero.shot();
        }
        //this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        //每100ms刷新
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判读是否击中
            hitTankBullets(hero.bullets);
            this.repaint();
        }
    }
}
