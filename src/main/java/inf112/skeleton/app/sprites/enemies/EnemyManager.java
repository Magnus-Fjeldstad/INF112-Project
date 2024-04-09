package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.audio.Ogg.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.EnemyCollisionHandler;

public class EnemyManager {
    private Array<AbstractEnemy> enemies;

    private Array<Body> enemiesToRemove;

    private EnemyCollisionHandler enemyCollisionHandler;
    private AbstractEnemyFactory enemyFactory;

    private PlayScreen screen;

    public EnemyManager(PlayScreen screen) {
        this.enemies = new Array<>();
        this.enemiesToRemove = new Array<>();
    
        this.screen = screen;
        this.enemyFactory = new AbstractEnemyFactory(screen);

        this.enemies.add(enemyFactory.spawnRandom());
        this.enemies.add(enemyFactory.spawnRandom());
        this.enemies.add(enemyFactory.spawnRandom());
        this.enemies.add(enemyFactory.spawnRandom());

        enemyCollisionHandler = new EnemyCollisionHandler();
    }

    public void update(float dt) {
        for (AbstractEnemy enemy : enemies) {
            enemy.update(dt);
        }

        handleCollision();
    }

    private void handleCollision() {
        for (AbstractEnemy enemy : enemies) {
            if (enemyCollisionHandler.getBodiesToRemove().contains(enemy.b2body, true)) {
                if (enemy.getHealth() > 0) {
                    enemy.setHealth(-10);
                    System.out.println("Enemy health: " + enemy.getHealth());
                }
                else {
                    enemies.removeValue(enemy, false);
                    enemiesToRemove.add(enemy.b2body);
                    enemy.b2body.getWorld().destroyBody(enemy.b2body);
                    Sound sound = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/death.ogg"));
                    sound.play();
                }
            }
        }
        enemyCollisionHandler.clearBodiesToRemove(); 
    }

    public EnemyCollisionHandler getEnemyCollisionHandler() {
        return enemyCollisionHandler;
    }

    public Array<Body> getEnemiesToRemove() {
        return enemiesToRemove;
    }

    public Array<AbstractEnemy> getEnemies() {
        return enemies;
    }
}
