package game2d;

import java.util.Random;

public class GameEvents {

    public static double multipler = 1.0;
    public static long lastMobSpawn = 0;

    public static void calc() {
        if (GameListener.lastAttack + 200 < System.currentTimeMillis()) {
            double imageAngleRad = Math.atan2(GameListener.mouseY - Game.player.y, GameListener.mouseX - Game.player.x);
            Bullet b = new Bullet(Game.player, Game.player.x, Game.player.y, imageAngleRad);

            GameListener.lastAttack = System.currentTimeMillis();
        }

        if (lastMobSpawn + (5000 * multipler) < System.currentTimeMillis()) {
            if (multipler > 0.1) {
                multipler -= 0.1;
            }

            lastMobSpawn = System.currentTimeMillis();
            if (Game.mobs.size() < 10) {
                Random rand = new Random();

                int direction = rand.nextInt(0, 3);
                if (direction == 0) {
                    Entity mob = new Entity(Entity.EntityType.MOB, rand.nextInt(-50, 850), -100);
                    Game.mobs.add(mob);
                } else if (direction == 1) {
                    Entity mob = new Entity(Entity.EntityType.MOB, 800, rand.nextInt(-50, 650));
                    Game.mobs.add(mob);
                } else if (direction == 2) {
                    Entity mob = new Entity(Entity.EntityType.MOB, rand.nextInt(-50, 850), 650);
                    Game.mobs.add(mob);
                } else if (direction == 3) {
                    Entity mob = new Entity(Entity.EntityType.MOB, -50, rand.nextInt(-50, 650));
                    Game.mobs.add(mob);
                }
            }
        }


    }
}
