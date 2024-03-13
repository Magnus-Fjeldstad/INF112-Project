package inf112.skeleton.app.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;


public class B2WorldCreator {

    
    public B2WorldCreator(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // Create walls
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameCreate.PPM,
                    (rect.getY() + rect.getHeight() / 2) / GameCreate.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameCreate.PPM, rect.getHeight() / 2 / GameCreate.PPM);
            fdef.shape = shape;
            
            fdef.filter.categoryBits = GameCreate.CATEGORY_WALLS; 
            fdef.filter.maskBits = GameCreate.CATEGORY_FIREBALL | GameCreate.CATEGORY_PLAYER | GameCreate.CATEGORY_ENEMY;
            body.createFixture(fdef);

            body.createFixture(fdef).setUserData(GameCreate.CATEGORY_WALLS);
        }
    }
}
