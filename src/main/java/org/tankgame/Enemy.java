package org.tankgame;

import java.util.Vector;

/**
 * @author mzr
 * @version 1.0
 */
public class Enemy extends Tank implements Runnable{
    //使用vector保存bullet
    Vector<Bullet> bullets = new Vector<>();
    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){
            if(isLive && bullets.isEmpty()){
                Bullet bullet = null;
                switch (getDirect()){
                    case 0:
                        bullet = new Bullet(getX() + 20,getY(),0);
                        break;
                    case 1:
                        bullet = new Bullet(getX() + 60,getY() + 20,1);
                        break;
                    case 2:
                        bullet = new Bullet(getX() + 20,getY() + 60,2);
                        break;
                    case 3:
                        bullet = new Bullet(getX(),getY() + 20,3);
                        break;
                }
                bullets.add(bullet);
                new Thread(bullet).start();
            }
            //根据方向进行移动
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            setDirect((int) (Math.random() * 4));
            if(!isLive){
                break;
            }
        }
    }
}
