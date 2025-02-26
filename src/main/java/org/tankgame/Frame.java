package org.tankgame;

import javax.swing.*;

/**
 * @author mzr
 * @version 1.0
 */
public class Frame extends JFrame {
    //定义Panel
    Panel mp = null;
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
    public Frame(){
        mp = new Panel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);   //面板
        this.setSize(1000,750);
        this.addKeyListener(mp);    //监听
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
