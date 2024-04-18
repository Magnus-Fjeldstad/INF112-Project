package inf112.skeleton.app.sprites.weapons.fireball;

// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.World;
// import inf112.skeleton.app.screens.PlayScreen;
// import inf112.skeleton.app.sprites.player.PlayerModel;
// import inf112.skeleton.app.sprites.weapons.fireball.Fireball;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// public class FireballTest {
//     private PlayScreen mockPlayScreen;
//     private PlayerModel mockPlayerModel;
//     private World world;

//     private Fireball fireball;

//     @BeforeEach
//     public void setUp() {
//         // Manual creation of a World object without mocking.
//         world = new World(new Vector2(0, 0), true);

//         // Mock the PlayScreen to return the manually created World object.
//         mockPlayScreen = mock(PlayScreen.class);
//         when(mockPlayScreen.getWorld()).thenReturn(world);

//         // Mock PlayerModel
//         mockPlayerModel = new PlayerModel(mockPlayScreen);
//         when(mockPlayScreen.getPlayerModel()).thenReturn(mockPlayerModel);

//         // Initialize Fireball with the mocked PlayScreen.
//         fireball = new Fireball(mockPlayScreen);
//     }

//     @Test
//     public void testGetDamage() {
//         assertEquals(10, fireball.getDamage(), "Damage should initially be 10.");
//     }

    // Add more tests here for other properties and methods of Fireball
// }