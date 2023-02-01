package com.wuMing;

import java.awt.*;

public abstract class GameObject {
    private Image image;
    int x,y;
    private GameFrame gameFrame;

//    public GameObject(Image image, int x, int y, GameFrame gameFrame) {
//        this.image = image;
//        this.x = x;
//        this.y = y;
//        this.gameFrame = gameFrame;
//    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public abstract void paintSelf(Graphics g);
    public abstract Rectangle getRec();

}
