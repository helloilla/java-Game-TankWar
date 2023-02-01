package com.wuMing;

import java.awt.*;

public class Base extends GameObject{
    static final int length=40;


    public Base(String img,int x,int y,GameFrame frame){
        this.x=x;
        this.y=y;
        setImage(Toolkit.getDefaultToolkit().getImage(img));
        setGameFrame(frame);
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
