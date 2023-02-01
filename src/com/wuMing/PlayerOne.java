package com.wuMing;


import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerOne extends Tank implements KeyListener {

    boolean attackCool=false;
    static final int attackCoolTime=1000;

    class AttackCoolThread extends Thread{
        @Override
        public void run() {
            attackCool=true;
            try {
                Thread.sleep(attackCoolTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attackCool=false;
            this.stop();
        }
    }


    public PlayerOne(String path, int x, int y, GameFrame gameFrame, Direction direction) {
        super(path, x, y, gameFrame, direction);
        setImage(upImg);
    }


    @Override
    public void paintSelf(Graphics g){

        super.paintSelf(g);
    }
    @Override
    public Rectangle getRec(){
        return super.getRec();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();


        switch (key){
            case KeyEvent.VK_W:
                up=true;
                direction=Direction.UP;
                break;
            case KeyEvent.VK_A:
                left=true;
                direction=Direction.LEFT;
                break;
            case KeyEvent.VK_S:
                down=true;
                direction=Direction.DOWN;
                break;
            case KeyEvent.VK_D:
                right=true;
                direction=Direction.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                attack();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();


        switch (key){
            case KeyEvent.VK_W:
                up=false;
//                move(Direction.UP);
                break;
            case KeyEvent.VK_A:
                left=false;
//                move(Direction.LEFT);
                break;
            case KeyEvent.VK_S:
                down=false;
//                move(Direction.DOWN);
                break;
            case KeyEvent.VK_D:
                right=false;
//                move(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void attack(){
        if(attackCool==true){
            return;
        }
        Point point=getHeadPoint();

        try {
            this.getGameFrame().playerBullets.add
                    (new Bullet(bulletImg[0],point.x,point.y,this.direction,this.getGameFrame()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("有错");
        }
        new AttackCoolThread().start();
    }
}
