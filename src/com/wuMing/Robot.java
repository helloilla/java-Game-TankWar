package com.wuMing;

import java.awt.*;
import java.util.Random;

public class Robot extends Tank {

    static Random random = new Random();
    static Direction[] directions = {Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT};
    int count;

    public Robot(String path, int x, int y, GameFrame gameFrame, Direction direction) {
        super(path, x, y, gameFrame, direction);
        setImage(downImg);
        count = 0;
    }

    public void go() {
        if (count != 20) {
            count++;
            return;
        }
        left=false;
        up=false;
        right=false;
        down=false;
        count=0;
        this.direction = directions[random.nextInt(4)];
        switch (direction) {
            case LEFT:
                left = true;
                break;
            case RIGHT:
                right = true;
                break;
            case UP:
                up = true;
                break;
            case DOWN:
                down = true;
                break;
        }

    }

    @Override
    public void attack() {
        int p = random.nextInt(500);

        if(p>4)
            return;
        Point point=getHeadPoint();
        getGameFrame().enemyBullets.add(new Bullet(bulletImg[1],point.x,point.y,this.direction,getGameFrame()));
    }

    @Override
    public void paintSelf(Graphics g) {
        attack();
        g.drawImage(getImage(), x, y, null);
        go();
        move();
    }
}
