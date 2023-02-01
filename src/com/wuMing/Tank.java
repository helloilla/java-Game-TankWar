package com.wuMing;

import java.awt.*;
import java.util.List;

public class Tank extends GameObject {
    public static final String bulletImg[] = {"./images/bullet/bulletYellow.gif","./images/bullet/bulletGreen.gif"};
    private final int with = 60, height = 60;
    int speed = 5;
    Direction direction;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    Image upImg;
    Image downImg;
    Image leftImg;
    Image rightImg;


    public void attack() {

    }


    public void move() {

        if (up) {
            setImage(upImg);
            if (!touchWall(x, y - speed) && !moveOutBorder(x, y - speed))
                y -= speed;
        } else if (right) {
            setImage(rightImg);
            if (!touchWall(x + speed, y) && !moveOutBorder(x + speed, y))
                x += speed;
        } else if (left) {
            setImage(leftImg);
            if (!touchWall(x - speed, y) && !moveOutBorder(x - speed, y))
                x -= speed;
        } else if (down) {
            setImage(downImg);
            if (!touchWall(x, y + speed) && !moveOutBorder(x, y + speed))
                y += speed;
        }
    }

    public Point getHeadPoint() {
        switch (direction) {
            case LEFT:
                return new Point(x, y + height / 2);
            case UP:
                return new Point(x + with / 2, y);
            case RIGHT:
                return new Point(x + with, y + height / 2);
            case DOWN:
                return new Point(x + with / 2, y + height);
        }
        return new Point(0, 0);
    }

    public Tank(String path, int x, int y, GameFrame gameFrame, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.upImg = Toolkit.getDefaultToolkit().getImage(path + "/up.gif");
        this.downImg = Toolkit.getDefaultToolkit().getImage(path + "/down.gif");
        this.leftImg = Toolkit.getDefaultToolkit().getImage(path + "/left.gif");
        this.rightImg = Toolkit.getDefaultToolkit().getImage(path + "/right.gif");
        this.setGameFrame(gameFrame);

    }


    public void paintSelf(Graphics g) {

        g.drawImage(getImage(), x, y, null);

        move();
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, with, height);
    }

    private boolean touchWall(int x, int y) {
        List<Wall> walls = this.getGameFrame().walls;
        Rectangle rec = new Rectangle(x, y, with, height);
        for (Wall wall : walls) {
            if (wall.getRec().intersects(rec)) {
                return true;
            }
        }
        return false;
    }

    private boolean moveOutBorder(int x, int y) {
        if (x < 0 || y < 0 || x + with > getGameFrame().gameWith || y + height> getGameFrame().gameHeight) {
            return true;
        }
        return false;
    }
}
