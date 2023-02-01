package com.wuMing;

import java.awt.*;


public class Wall extends GameObject{
//    static final  String img="imges";
    static final int length=55;

    public Wall(int x, int y, GameFrame gameFrame) {
        setImage(Toolkit.getDefaultToolkit().getImage("./images/walls.gif"));
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
