package org.tankgame;

/**
 * @author mzr
 * @version 1.0
 */
public class Bomb {

    int x,y;                //坐标
    int life = 9;           //生命周期
    boolean isLive = true;  //存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown(){
        if(life > 0){
            life --;
        } else {
            isLive = false;
        }
    }
}
