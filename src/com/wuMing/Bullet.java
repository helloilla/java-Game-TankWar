package com.wuMing;

import java.awt.*;

public class Bullet extends GameObject {
    //    private Image image;
//    private int x,y;
//    private GameFrame gameFrame;
    static final int with = 15, height = 15;
    final int speed = 8;
    Direction direction;

    public Bullet(String img, int x, int y, Direction direction, GameFrame frame) {
        setImage(Toolkit.getDefaultToolkit().getImage(img));
        this.x = x-7;
        this.y = y-7;
        this.direction = direction;
        setGameFrame(frame);
    }

    public void go() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(getImage(), x, y, null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, with, height);
    }
}
