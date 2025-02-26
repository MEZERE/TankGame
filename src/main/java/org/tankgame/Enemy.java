package org.tankgame;

import java.util.Vector;

/**
 * @author mzr
 * @version 1.0
 */
public class Enemy extends Tank{
    //使用vector保存bullet
    Vector<Bullet> bullets = new Vector<>();
    public Enemy(int x, int y) {
        super(x, y);
    }
}
