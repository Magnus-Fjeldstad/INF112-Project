package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.screens.PlayScreen;

public abstract class AbstractEnemy extends Sprite  {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    public int health;
    public int attackDamage;
    public float movementSpeed;

    public AbstractEnemy(PlayScreen screen, float x, float y, int health, float movementSpeed, int attackDamage){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        velocity = new Vector2(-(this.movementSpeed), -(this.movementSpeed)*2);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    
}   
