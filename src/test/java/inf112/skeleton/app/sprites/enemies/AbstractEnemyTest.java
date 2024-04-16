package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.screens.PlayScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AbstractEnemyTest {
    private PlayScreen mockPlayScreen;
    private World world;

    private AbstractEnemyFactory enemyFactory;

    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    public int health;
    public int attackDamage;
    public float movementSpeed;

    @BeforeEach
    public void setUp() {
        // Manual creation of a World object without mocking.
        world = new World(new Vector2(0, 0), true);

        // Mock the PlayScreen to return the manually created World object.
        mockPlayScreen = mock(PlayScreen.class);
        when(mockPlayScreen.getWorld()).thenReturn(world);

        // Initialize Fireball with the mocked PlayScreen.
        enemyFactory = new AbstractEnemyFactory(mockPlayScreen);
    }

    @Test
    public void testGetDamage() {
        assertEquals(10, this.enemyFactory.spawnRedEnemy().getAttackDamage(), "Damage should initially be 10.");
    }

}
