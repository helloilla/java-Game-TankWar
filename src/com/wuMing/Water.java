package com.wuMing;

import java.awt.*;

public class Water extends GameObject{
    static final int length=55;

    public Water(int x, int y, GameFrame gameFrame) {
        setImage(Toolkit.getDefaultToolkit().getImage("./images/water.gif"));
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
