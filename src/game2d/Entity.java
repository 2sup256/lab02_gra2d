package game2d;

public class Entity {
    public static enum EntityType {
        PLAYER,
        MOB
    }

    public EntityType type;
    public int x;
    public int y;

    public Entity(EntityType et, int x, int y) {
        this.type = et;
        this.x = x;
        this.y = y;
    }
}
