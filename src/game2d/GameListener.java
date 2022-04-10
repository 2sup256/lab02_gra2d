package game2d;

import java.awt.event.*;

public class GameListener implements KeyListener, MouseMotionListener, MouseListener {
    public static boolean UP;
    public static boolean DOWN;
    public static boolean RIGHT;
    public static boolean LEFT;
    public static boolean ATTACK = true;
    public static long lastAttack;
    public static int mouseX = 0;
    public static int mouseY = 0;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            UP = true;
        }
        if (e.getKeyChar() == 's') {
            DOWN = true;
        }
        if (e.getKeyChar() == 'd') {
            RIGHT = true;
        }
        if (e.getKeyChar() == 'a') {
            LEFT = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            UP = false;
        }
        if (e.getKeyChar() == 's') {
            DOWN = false;
        }
        if (e.getKeyChar() == 'd') {
            RIGHT = false;
        }
        if (e.getKeyChar() == 'a') {
            LEFT = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        lastAttack = System.currentTimeMillis();
//        ATTACK = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        double imageAngleRad = Math.atan2(GameListener.mouseY - Game.player.y, GameListener.mouseX - Game.player.x);
//        Bullet b = new Bullet(Game.player, Game.player.x, Game.player.y, imageAngleRad);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        ATTACK = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
