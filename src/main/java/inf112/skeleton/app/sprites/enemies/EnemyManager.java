package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.EnemyCollisionHandler;
import inf112.skeleton.app.tools.sound.SoundManager;

public class EnemyManager {
    private Array<AbstractEnemy> enemies;

    private PlayScreen screen;

    private Array<Body> enemiesToRemove;

    private EnemyCollisionHandler enemyCollisionHandler;
    private AbstractEnemyFactory enemyFactory;
    private SoundManager soundManager;



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
        
        this.enemies.add(enemyFactory.spawnRandom());
        this.enemies.add(enemyFactory.spawnRandom());
        this.enemies.add(enemyFactory.spawnRandom());
        this.enemies.add(enemyFactory.spawnRandom());

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
                }
            }
        }
        enemyCollisionHandler.clearBodiesToRemove(); 
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
