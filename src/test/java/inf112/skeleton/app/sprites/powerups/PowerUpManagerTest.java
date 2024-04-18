package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for PowerUpManager.
 */
public class PowerUpManagerTest {
    private PlayScreen mockPlayScreen;
    private World world;

    private PlayerModel mockPlayerModel;

    private PowerUpManager powerUpManager;

    @BeforeEach
    public void setUp() {
        // Mock the PlayScreen and PlayerModel.

        world = new World(new Vector2(0, 0), true);
        
        mockPlayScreen = mock(PlayScreen.class);
        when(mockPlayScreen.getWorld()).thenReturn(world);
        mockPlayerModel = mock(PlayerModel.class);

        powerUpManager = new PowerUpManager(mockPlayScreen, mockPlayerModel);
    }

    @Test
    public void createRandomPowerUpTest() {
        powerUpManager.createRandomPowerUp();

        assertFalse(powerUpManager.getPowerUps().isEmpty());
    }

    @Test
    public void getPowerUpCollisionHandlerTest() {
        assertFalse(powerUpManager.getPowerUpCollisionHandler() == null);
    }

    @Test
    public void getPowerUpsTest () {
        assertFalse(powerUpManager.getPowerUps() == null);
    }
}
