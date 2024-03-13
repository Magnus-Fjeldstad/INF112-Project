package inf112.skeleton.app.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerModelTest {

    @Mock
    private PlayScreen playScreen;

    private PlayerModel player;

    @BeforeEach
    void setUp() {
        // Mock the essential graphics components
        playScreen = mock(PlayScreen.class);
        TextureAtlas mockAtlas = mock(TextureAtlas.class);
        TextureAtlas.AtlasRegion mockAtlasRegion = mock(TextureAtlas.AtlasRegion.class);
        Texture mockTexture = mock(Texture.class);

        // Setup mocks to prevent null access
        when(playScreen.getAtlas()).thenReturn(mockAtlas);
        when(mockAtlas.findRegion(anyString())).thenReturn(mockAtlasRegion);
        when(mockAtlasRegion.getTexture()).thenReturn(mockTexture);
        when(mockTexture.getWidth()).thenReturn(100);
        when(mockTexture.getHeight()).thenReturn(100);

        // Ensure the player model avoids graphical initialization that leads to null
        // pointer access
        player = new PlayerModel(playScreen, 100, 100, 2.0f);
    }

    // Example test case for a setter/getter
    @Test
    void testHealthSetterAndGetter() {
        player.setHealth(80); // Assuming this is how you'd adjust health
        assertEquals(80, player.getHealth(), "Player health should be updated to 80.");
    }
}
