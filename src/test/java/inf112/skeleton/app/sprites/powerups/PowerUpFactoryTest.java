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
 * Test class for PowerUpFactory.
 */
public class PowerUpFactoryTest {
    private PlayScreen mockPlayScreen;
    private World world;

    private PlayerModel mockPlayerModel;

    private PowerUpFactory powerUpFactory;

    @BeforeEach
    public void setUp() {
        world = new World(new Vector2(0, 0), true);

        mockPlayScreen = mock(PlayScreen.class);
        when(mockPlayScreen.getWorld()).thenReturn(world);
        mockPlayerModel = mock(PlayerModel.class);

        powerUpFactory = new PowerUpFactory(mockPlayScreen);
    }

    @Test
    public void createPowerUp() {
        powerUpFactory.createPowerUp(PowerUpEnum.SPEED_BOOST, mockPlayerModel, 0, 0);

        assertFalse(powerUpFactory.createPowerUp(PowerUpEnum.SPEED_BOOST, mockPlayerModel, 0, 0) == null);
    }
}
