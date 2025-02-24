package org.tankgame;

/**
 * @author mzr
 * @version 1.0
 */
public class Tank {
    //坦克坐标
    int x;
    int y;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
