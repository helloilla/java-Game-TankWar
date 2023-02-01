package com.wuMing;

import com.sun.deploy.panel.ITreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GameFrame extends JFrame {
    private Image offStreamImage;
    private final int with = 1000, height = 800;
    final int gameWith = 1000, gameHeight = 800;
    private Image select = Toolkit.getDefaultToolkit().getImage("images/player.png");
    private int gameMode = 1;
    private boolean isPlaying = false;

    // 游戏元素列表
    List<PlayerOne> players;
    List<Bullet> playerBullets;
    List<Bullet> enemyBullets;
    List<Robot> enemy;
    List<Wall> walls;
    List<Base> bases;

    private void hitEnemy() {
        Iterator it = playerBullets.iterator();
        while (it.hasNext()) {
            Iterator it2 = enemy.iterator();
            Bullet bullet = (Bullet) it.next();
            while (it2.hasNext()) {
                if (((Robot) it2.next()).getRec().intersects(bullet.getRec())) {
                    it2.remove();
                    it.remove();
                    break;
                }
            }
        }
    }

    private void hitCheck() {
        hitPlayers();
        hitEnemy();
        hitWalls();
    }

    private void hitWalls() {
        Iterator it1 = playerBullets.iterator();
        while (it1.hasNext()) {

            Iterator it2 = walls.iterator();
            Bullet bullet = (Bullet) it1.next();

            while (it2.hasNext()) {
                if (bullet.getRec().intersects(((Wall) it2.next()).getRec())) {
                    it2.remove();
                    it1.remove();
                    break;
                }
            }
        }
        it1 = enemyBullets.iterator();
        while (it1.hasNext()) {

            Iterator it2 = walls.iterator();
            Bullet bullet = (Bullet) it1.next();

            while (it2.hasNext()) {
                if (bullet.getRec().intersects(((Wall) it2.next()).getRec())) {
                    it2.remove();
                    it1.remove();
                    break;
                }
            }
        }


    }

    private void clearOutBorderBullets() {
        Rectangle rectangle = new Rectangle(0 + Bullet.with, 0 + Bullet.height, with, height);
        List<Bullet> lists[] = new List[2];
        lists[0] = enemyBullets;
        lists[1] = playerBullets;
        int count = 0;
        for (int i = 0; i < 2; i++) {
            Iterator<Bullet> it = lists[i].iterator();
            while (it.hasNext()) {
                if (!it.next().getRec().intersects(rectangle)) {
                    it.remove();
//                    System.out.println(i == 1 ? "玩家子弹出界" : "子弹出界");
                }
            }
            count += lists[i].size();
        }
//        System.out.println("子弹总数:" + count);
    }


    private void initData() {
//        int x = 40;
        enemy = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        bases=new ArrayList<>();
        walls=new ArrayList<>();
        players=new ArrayList<>();
        //创建游戏画布
        offStreamImage = this.createImage(with, height);


        initEnemy();

        initPlayers();

        initWalls();

        initBases();
    }

    private void initBases() {
        bases.add(new Base("./images/star.gif", with / 2 - Base.length / 2, height - Base.length, this));
        bases.add(new Base("./images/symbol.gif", with / 2 - Base.length / 2, 0, this));

    }

    private void initWalls() {
//        for (int i = 0; i < 15; i++) {
////            if (i % 2 == 0)
//                walls.add(new Wall(40 + i * 60, 120, this));
////                walls.add(new Wall(40 + i * 60, 340, this));
//        }

        int x=with/2-60-30,y=0;
        walls.add(new Wall(x,y,this));
        walls.add(new Wall(x,y+60,this));
        y=height-60;
        walls.add(new Wall(x,y,this));
        walls.add(new Wall(x,y-60,this));
        x+=60;y=height-120;
        walls.add(new Wall(x,y,this));
        walls.add(new Wall(x+60,y,this));
        y=60;
        walls.add(new Wall(x,y,this));
        walls.add(new Wall(x+60,y,this));

        walls.add(new Wall(x+60,0,this));
        walls.add(new Wall(x+60,height-60,this));
    }

    private void initPlayers() {

        players.add(new PlayerOne("images/tank1", 200, 500, this, Direction.UP));
    }

    private void initEnemy(){
        for (int i = 0; i < 4; i++) {
            enemy.add(new Robot("./images/tank3", 40 + i * 280, 20, this, Direction.DOWN));
        }
    }

    private void hitPlayers() {
        Iterator<Bullet> it = enemyBullets.iterator();
        while (it.hasNext()) {
            Bullet next = it.next();
            Iterator<PlayerOne> player = players.iterator();
            while (player.hasNext()) {
                if (next.getRec().intersects(player.next().getRec())) {
                    it.remove();
                    player.remove();
                }
            }
        }
    }

    private void launch() {
        this.setSize(with, height + 41);
        setTitle("坦克大战1.0");
        // 窗口居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        // 添加键盘监听
        addKeyListener(new KeyMonitor());
    }

    public GameFrame() {
        // 窗口绘制
        launch();

        initData();

        // 游戏画面重绘
        while (true) {
            repaint();
            try {
                Thread.sleep(25); //间隔毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void paintMenu(Graphics g) {


        g.setColor(Color.black);
        g.fillRect(0, 0, with, height);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("宋体", Font.BOLD, 50));
        g.drawString("选择游戏模式", 220, 100);
        g.drawString("单人模式", 250, 300);
        g.drawString("双人模式", 250, 400);

        int y = 0;
        if (gameMode == 1) {
            y = 254;
        } else if (gameMode == 2) {
            y = 355;
        }
        g.drawImage(select, 180, y, null);



    }

    @Override
    public void paint(Graphics g) {
        if(offStreamImage==null){
            offStreamImage=createImage(with,height);
        }

        Graphics graphics = offStreamImage.getGraphics();

        if (!isPlaying) {
            paintMenu(graphics);
        } else {
            paintGame(graphics);
        }
        g.drawImage(offStreamImage, 0, 36, null);
    }

    private void paintGame(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, with, height);
        g.drawString("正在游戏", 200, 200);

        clearOutBorderBullets();

        hitCheck();

        for (PlayerOne player : players) {
            player.paintSelf(g);
        }

        for (Bullet bullet : playerBullets) {
            bullet.paintSelf(g);
        }
        for (Bullet bullet : enemyBullets) {
            bullet.paintSelf(g);
        }
        for (Robot robot : enemy) {
            robot.paintSelf(g);
        }

        for (Wall wall : walls) {
            wall.paintSelf(g);
        }

        for (Base basis : bases) {
            basis.paintSelf(g);
        }


    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
//            System.out.println(keyCode);
            if (!isPlaying) {
                switch (keyCode) {
                    case 87:
                        gameMode = 1;
                        break;
                    case 83:
                        gameMode = 2;
                        break;
                    case 10:
                        isPlaying = true;
                        break;
                    default:
                        break;
                }
            } else {

                if (players.size() == 0)
                    return;

                for (PlayerOne player : players) {
                    player.keyPressed(e);
                }

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (players.size() == 0)
                return;

            for (PlayerOne player : players) {
                player.keyReleased(e);
            }
        }

    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

    }


}
