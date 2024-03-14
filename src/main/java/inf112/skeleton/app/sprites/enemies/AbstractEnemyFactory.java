package inf112.skeleton.app.sprites.enemies;

import inf112.skeleton.app.screens.PlayScreen;

public class AbstractEnemyFactory {

    private PlayScreen screen;

    public AbstractEnemyFactory(PlayScreen screen) {
        this.screen = screen;;
    }

    /**
     * Returns an enemy
     * @return AbstractEnemy
     */
    public AbstractEnemy spawnRandom() {
        // TODO: Make this spawn different enemies in given spawn locations based on time
        return new RedEnemy(screen, 0, 0, 1, 0.5f, 0);
    }
}
