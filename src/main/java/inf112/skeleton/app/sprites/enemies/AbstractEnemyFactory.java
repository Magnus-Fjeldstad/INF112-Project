package inf112.skeleton.app.sprites.enemies;

import inf112.skeleton.app.screens.PlayScreen;

public class AbstractEnemyFactory {

    private PlayScreen screen;

    /**
     * Constructor for AbstractEnemyFactory
     * @param screen
     */
    public AbstractEnemyFactory(PlayScreen screen) {
        this.screen = screen;
    }

    /**
     * Returns an enemy
     * @return AbstractEnemy
     */
    public AbstractEnemy spawnRandom() {
        AbstractEnemy enemy = new RedEnemy(screen, 200, 200, 20, 0.5f, 0);
        return enemy;
    }
}
