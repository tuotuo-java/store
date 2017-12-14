package com.tuotuo.snaker;

import sun.awt.SunHints;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by 12130 on 2017/8/1.
 */
public class Yard extends Frame {
    PaintThread paintThread=new PaintThread();
    private boolean gameOver=false;

    public static final int ROWS=30;
    public static final int COLS=30;
    public static final int BLOCK_SIZE=15;
    private Font fontGameOver=new Font("宋体",Font.BOLD,50);

    private int score=0;

    Snake s1=new Snake(this);
    Snake s2=new Snake(this);
    Egg e=new Egg();

    Image offScreenImage=null;

    public void launch(){
        this.setLocation(200,200);
        this.setSize(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
        this.addKeyListener(new KeyMonitor());
        new Thread(paintThread).start();
    }

    public void stop() {

    }


    private class PaintThread implements Runnable{
        private boolean running=true;
        private boolean pause=false;


        @Override
        public void run() {
            while (running){
                if(pause) continue;
                else repaint();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
        public void pause() {
            this.pause=true;
        }
        public void reStart() {
            this.pause=false;
            s1=new Snake(Yard.this);
            s2=new Snake(Yard.this);
            gameOver=false;
        }
        public void gameOver(){
            running=false;
        }
    }

    private class KeyMonitor extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key =e.getKeyCode();
            if(key==KeyEvent.VK_F2) {
                paintThread.reStart();
            }
            s1.keyPressed1(e);
//            s2.keyPressed2(e);
        }

    }
}
