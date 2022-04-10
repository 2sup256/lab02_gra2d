package game2d;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Game extends Canvas {
    public static GameListener gml;
    public static Entity player;
    public static Canvas gamecomp;
    public static ArrayList<Entity> mobs = new ArrayList<>();
    public static boolean playing = false;
    public static int score = 0;
    public static int health = 100;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    public Game() {
        gamecomp = this;
        gml = new GameListener();

        this.addKeyListener(gml);
        this.addMouseMotionListener(gml);
        this.addMouseListener(gml);

        player = new Entity(Entity.EntityType.PLAYER, 400-32, 300-32);

        playing = true;
    }


//
//    @Override
//    public void paintComponent(Graphics g1) {
//        Graphics2D g = (Graphics2D) g1;
//
//        g.setColor(new Color(255, 0, 0));
//        g.fillRect(player.x-15, player.y-15, 30, 30);
//
//        System.out.println("OK");
//    }



}
