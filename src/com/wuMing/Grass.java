package com.wuMing;

import java.awt.*;

public class Grass extends GameObject{
    static final int length=55;

    public Grass(int x, int y, GameFrame gameFrame) {
        setImage(Toolkit.getDefaultToolkit().getImage("./images/grass.gif"));
        this.x = x;
        this.y = y;
        setGameFrame(gameFrame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(getImage(),x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
}
