package game2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class GameLogic implements Runnable {

    public static long startFrame;
    public static long startFPS;
    public static int frames;

    public static BufferedImage playerimage;
    public static BufferedImage akimage;
    public static BufferedImage enemy;

    public void init() {
        try {
            playerimage = ImageIO.read(new File("C:\\Users\\Czepeel\\Desktop\\game2d\\img\\icon.png"));
            akimage = ImageIO.read(new File("C:\\Users\\Czepeel\\Desktop\\game2d\\img\\ak47.png"));
            enemy = ImageIO.read(new File("C:\\Users\\Czepeel\\Desktop\\game2d\\img\\enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        startFrame = System.currentTimeMillis();
        startFPS = System.currentTimeMillis();
        frames = 0;

        try {
            while (true) {
                long nowIs = System.currentTimeMillis();


                if (nowIs - 16 > startFrame) {
                    startFrame = nowIs;

                    input();
                    draw();

                    if (nowIs - 1000 > startFPS) {
                        startFPS = nowIs;
                        Main.window.setTitle("2D - "+frames+" FPS");
                        frames = 0;
                    } else {
                        frames++;
                    }
                } else {
                    Thread.sleep(0);
                }
            }
        } catch (InterruptedException e) {
            // oh someone paused the game
        }
    }

    public void draw() {
        if (!Game.playing) {
            return;
        }

//        Game.gamecomp.repaint();

        // Calc everything
        GameEvents.calc();

        // Get buffer
        BufferStrategy buffer = Game.gamecomp.getBufferStrategy();

        if (buffer == null) {
            Game.gamecomp.createBufferStrategy(3);
            buffer = Game.gamecomp.getBufferStrategy();
        }

        // Get context from the JPanel we are going to draw to
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

        // Start with clearing the drawing surface
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Game.gamecomp.getWidth(), Game.gamecomp.getHeight());

        g.setColor(new Color(33, 33, 33));
        // Bullets
        for (Bullet b : Bullet.list) {
            b.x += (int) (8.0 * Math.cos(b.angle));
            b.y += (int) (8.0 * Math.sin(b.angle));

            g.fillArc(b.x - 4, b.y - 4, 8, 8, 0, 360);
        }

        for (Entity ent : Game.mobs) {
            double MobRad = Math.atan2(Game.player.y - ent.y, Game.player.x - ent.x);

            ent.x += (int) (2.0 * Math.cos(MobRad));
            ent.y += (int) (2.0 * Math.sin(MobRad));
        }


        double distance = 0.0;
        Iterator<Entity> iterEnt = Game.mobs.iterator();
        while (iterEnt.hasNext()) {
            Entity ent = iterEnt.next();

            if (ent.type == Entity.EntityType.MOB) {
                Iterator<Bullet> iterBullet = Bullet.list.iterator();
                while (iterBullet.hasNext()) {
                    Bullet b = iterBullet.next();

                    distance = Math.sqrt((b.y - ent.y) * (b.y - ent.y) + (b.x - ent.x) * (b.x - ent.x));

                    if (distance <= 32.0) {
                        Game.score++;

                        iterBullet.remove();
                        iterEnt.remove();
                        break;
                    } else {
                        if (b.x > 800 || b.x < 0) {
                            iterBullet.remove();
                            break;
                        }
                        if (b.y > 600 || b.y < 0) {
                            iterBullet.remove();
                            break;
                        }
                    }
                }

                distance = Math.sqrt((Game.player.y - ent.y) * (Game.player.y - ent.y) + (Game.player.x - ent.x) * (Game.player.x - ent.x));

                if (distance <= 64.0) {
                    iterEnt.remove();

                    Game.health -= 10;

                    if (Game.health <= 0) {
                        Game.playing = false;
                    }
                }

            }
        }



        for (Entity ent : Game.mobs) {
            double imageMobRad = Math.atan2(Game.player.y - ent.y, Game.player.x - ent.x) - 1.2;
            AffineTransform oldAT2 = g.getTransform();
            g.translate(ent.x, ent.y);
            g.rotate(imageMobRad);
            g.translate(-32, -32);
            g.drawImage(enemy, 0, 0, null);
            g.setTransform(oldAT2);
        }


        // Player weapon
        double imageAngleRad = Math.atan2(GameListener.mouseY - Game.player.y, GameListener.mouseX - Game.player.x);

        AffineTransform oldAT = g.getTransform();
        g.translate(Game.player.x, Game.player.y);
        g.rotate(imageAngleRad);
        g.translate(-32, -32);
        g.drawImage(akimage, 0, 0, null);
        g.setTransform(oldAT);

        //
        AffineTransform oldAT2 = g.getTransform();
        g.translate(Game.player.x, Game.player.y);
        g.rotate(imageAngleRad - 1.6);
        g.translate(-32, -32);
        g.drawImage(playerimage, 0, 0, null);
        g.setTransform(oldAT2);

        // Player model
        g.setColor(new Color(33, 33, 33));
        g.setStroke(new BasicStroke(2));
//        g.drawImage(playerimage, Game.player.x - 32, Game.player.y - 32, 64, 64, null);
        g.drawArc(Game.player.x - 32, Game.player.y - 32, 64, 64, 0, 360);


        // Mobs






//        System.out.println("DRAWING");

        // Menu
        g.setColor(new Color(33, 33, 33));
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: "+Game.score, 10, 25);


        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("HP: "+Game.health, 700, 25);

        if (!Game.playing) {
            g.setColor(new Color(33, 33, 33));
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Koniec gry", 300, 200);
        }

        g.dispose();

        buffer.show();
    }

    public void input() {
        if (GameListener.UP) {
            if (Game.player.y > 0) {
                Game.player.y--;
            }
        }
        if (GameListener.DOWN) {
            if (Game.player.y < 600) {
                Game.player.y++;
            }
        }
        if (GameListener.RIGHT) {
            if (Game.player.x < 800) {
                Game.player.x++;
            }
        }
        if (GameListener.LEFT) {
            if (Game.player.x > 0) {
                Game.player.x--;
            }
        }
    }
}
