package inf112.skeleton.app.sprites.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.screens.PlayScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for PlayerModel.
 */
public class PlayerModelTest {

    private PlayScreen mockPlayScreen;
    private World world;

    private PlayerModel playerModel;

    @BeforeEach
    public void setUp() {
        // Manual creation of a World object without mocking.
        world = new World(new Vector2(0, 0), true);

        // Mock the PlayScreen to return the manually created World object.
        mockPlayScreen = mock(PlayScreen.class);
        when(mockPlayScreen.getWorld()).thenReturn(world);

        // Initialize PlayerModel with the mocked PlayScreen and default values.
        playerModel = new PlayerModel(mockPlayScreen);
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, playerModel.getHealth(), "Health should initially be 100.");
    }

    @Test
    public void testSetHealth() {
        playerModel.setHealth(-10); // Assuming this reduces health by 10
        assertEquals(90, playerModel.getHealth(), "Health should be 90 after reducing by 10.");
    }

    @Test
    public void testGetMaxHealth() {
        assertEquals(100, playerModel.getMaxHealth(), "Max health should initially be 100.");
    }

    @Test
    public void testSetMaxHealth() {
        playerModel.setMaxHealth(20); // Assuming this increases max health by 20
        assertEquals(120, playerModel.getMaxHealth(), "Max health should be 120 after increasing by 20.");
    }

    @Test
    public void testGetSpeed() {
        assertEquals(4, playerModel.getSpeed(), "Speed should initially be 5.0f.");
    }

    @Test
    public void testSetSpeed() {
        playerModel.setSpeed(1); // Assuming this increases speed by 1.0f
        assertEquals(5, playerModel.getSpeed(), "Speed should be 6.0f after increasing by 1.0f.");
    }
}
