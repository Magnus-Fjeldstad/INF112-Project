package inf112.skeleton.app.sprites.enemies;

import java.util.Random;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

public class AbstractEnemyFactory {

    private final PlayScreen screen;
    private final Random random;

    /**
     * Constructor for AbstractEnemyFactory
     * @param screen
     */
    public AbstractEnemyFactory(PlayScreen screen) {
        this.screen = screen;
        this.random = new Random();
    }

    /**
     * Returns an enemy
     * @return AbstractEnemy
     */
    public AbstractEnemy spawnRandom() {
        AbstractEnemy enemy = new RedEnemy(screen, random.nextInt(32, GameCreate.V_Width), random.nextInt(32, GameCreate.V_Height), 20, 0.5f, 10);
        return enemy;
    }
}
