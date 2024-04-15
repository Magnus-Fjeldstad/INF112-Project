package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.EnemyCollisionHandler;
import inf112.skeleton.app.tools.sound.SoundManager;

public class EnemyManager {
    private final Array<AbstractEnemy> enemies;

    private final PlayScreen screen;

    private final Array<Body> enemiesToRemove;

    private final EnemyCollisionHandler enemyCollisionHandler;
    private final AbstractEnemyFactory enemyFactory;
    private final SoundManager soundManager;

    private int waveSize = 5;
    private int enemiesKilled;



    /**
     * Constructor for EnemyManager
     * @param screen
     */
    public EnemyManager(PlayScreen screen) {
        this.enemies = new Array<>();
        this.enemiesToRemove = new Array<>();
        this.enemyFactory = new AbstractEnemyFactory(screen);
        this.soundManager = new SoundManager();

        this.screen = screen;
        this.enemiesKilled = 0;

        enemyCollisionHandler = new EnemyCollisionHandler();
    }

    /**
     * Updates all AbstractEnemies
     * @param dt
     */
    public void update(float dt) {
        for (AbstractEnemy enemy : enemies) {
            enemy.update(dt);
        }
        handleCollision();
        spawnEnemies();
        
        if (enemiesKilled == waveSize) {
            spawnEnemies();
        }
    }

    /**
     * Deals damage to enemies and removes dead AbstractEnemies
     */
    private void handleCollision() {
        for (AbstractEnemy enemy : enemies) {
            if (enemyCollisionHandler.getBodiesToRemove().contains(enemy.b2body, true)) {
                if (enemy.getHealth() > 0) {
                    enemy.setHealth(-screen.getPlayerModel().getAttackDamage());
                    soundManager.enemy_hit.play();
                }
                else {
                    enemies.removeValue(enemy, false);
                    enemiesToRemove.add(enemy.b2body);
                    enemy.dispose();
                    soundManager.death.play();
                    enemiesKilled++;
                }
            }
        }
        enemyCollisionHandler.clearBodiesToRemove(); 
    }


    /**
     * Spawns enemies if there are less than 5 enemies
     */
    private void spawnEnemies() {
        if (enemies.size == 0) {
            for (int i = 0; i < waveSize; i++) {
                enemies.add(enemyFactory.spawnRandom());
            }
            waveSize += 5; // Increase the wave size for the next wave
        }
    }


    public EnemyCollisionHandler getEnemyCollisionHandler() {
        return enemyCollisionHandler;
    }

    /**
     * Returns a list of AbstractEnemies that are dead
     * @return Array<Body>
     */
    public Array<Body> getEnemiesToRemove() {
        return enemiesToRemove;
    }

    /**
     * Returns a list of all AbstractEnemies
     * @return Array<Body>
     */
    public Array<AbstractEnemy> getEnemies() {
        return enemies;
    }
}
