package inf112.skeleton.app.sprites;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.skeleton.app.screens.PlayScreen;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerModelTest {
    private static PlayScreen playScreen;
    private PlayerModel player;

    @BeforeAll
    static void setUpBeforeAll() {
        // Mock LibGDX GL20 and PlayScreen to avoid null pointer exceptions
        com.badlogic.gdx.Gdx.gl = mock(GL20.class);
        com.badlogic.gdx.Gdx.gl20 = mock(GL20.class);
        playScreen = mock(PlayScreen.class);
    }

    @BeforeEach
    void setUp() {
        // Initialize headless application as before
        new HeadlessApplication(mock(inf112.skeleton.app.GameCreate.class), new HeadlessApplicationConfiguration());

        // Mock the TextureAtlas and TextureRegion
        TextureAtlas mockAtlas = mock(TextureAtlas.class);
        TextureRegion mockRegion = mock(TextureRegion.class);

        // When getAtlas is called on playScreen, return mockAtlas
        when(playScreen.getAtlas()).thenReturn(mockAtlas);
        // When findRegion is called on mockAtlas with any String, return mockRegion
        when(mockAtlas.findRegion(anyString())).thenReturn((AtlasRegion) mockRegion);

        // Now, initialize your PlayerModel
        player = new PlayerModel(playScreen, 100, 1f, 10);
    }

    @Test
    void testInitialHealth() {
        assertEquals(100, player.getHealth(), "Initial health should be 100");
    }

    @Test
    void testHealthSetter() {
        player.setHealth(-10); // Assuming this decreases health
        assertEquals(90, player.getHealth(), "Health should decrease by 10");
    }

    @Test
    void testInitialSpeed() {
        assertEquals(1f, player.getSpeed(), "Initial speed should be 1f");
    }

    @Test
    void testSpeedSetter() {
        player.setSpeed(0.5f); // Assuming this increases speed
        assertEquals(1.5f, player.getSpeed(), "Speed should increase by 0.5f");
    }

    @Test
    void testInitialAttackDamage() {
        assertEquals(10, player.getAttackDamage(), "Initial attack damage should be 10");
    }

    @Test
    void testAttackDamageSetter() {
        player.setAttack(5); // Assuming this increases attack damage
        assertEquals(15, player.getAttackDamage(), "Attack damage should increase by 5");
    }
}
