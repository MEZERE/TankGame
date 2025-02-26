package org.tankgame;

/**
 * @author mzr
 * @version 1.0
 */
public class Hero extends Tank {
    //射击子弹线程
    Bullet bullet = null;
    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shot(){
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
        //启动
        new Thread(bullet).start();
    }
}
