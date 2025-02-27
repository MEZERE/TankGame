package org.tankgame;

/**
 * @author mzr
 * @version 1.0
 */
public class Bullet implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 5;
    boolean isLive = true;

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据方向改变坐标
            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            System.out.println("子弹："+ x + y);
            //碰到墙 或者 敌人 结束线程
            if (!(x>=0 && x<=1000 && y>=0 && y <=750 && isLive)){
                isLive = false;
                break;
            }
        }
    }
}
