package game2d;

import java.util.ArrayList;

public class Bullet {
    public static ArrayList<Bullet> list = new ArrayList<>();

    public Entity owner;
    public int x;
    public int y;
    public double angle;

    public Bullet(Entity owner, int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;

        list.add(this);
    }

}
