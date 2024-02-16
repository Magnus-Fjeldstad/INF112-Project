package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.lwjgl.opengl.GL20;

import inf112.skeleton.app.enteties.Player;

public class Play implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gameCam;
    private Player player;

    @Override
    public void show() {
        map = new TmxMapLoader().load("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam = new OrthographicCamera();
        player = new Player(new Sprite(new Texture("blackCircle.png")));
    }

    @Override
    public void resize(int width, int height) {
        gameCam.viewportWidth = width;
        gameCam.viewportHeight = height;
        gameCam.update();
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        
        //Updated the gameCam to follow the sprite
        gameCam.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        gameCam.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(gameCam);
        renderer.render();

        // Renders the player sprite
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    private void handleInput(float delta) {
        float moveSpeed = 100;
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            player.setPosition(player.getX() - moveSpeed * delta, player.getY());
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            player.setPosition(player.getX() + moveSpeed * delta, player.getY());
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            player.setPosition(player.getX(), player.getY() + moveSpeed * delta);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            player.setPosition(player.getX(), player.getY() - moveSpeed * delta);
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
    }
}