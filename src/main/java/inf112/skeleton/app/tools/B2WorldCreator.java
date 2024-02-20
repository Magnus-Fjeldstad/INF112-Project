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
import inf112.skeleton.app.GameTest;

public class B2WorldCreator {
    public B2WorldCreator (World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Create object for ground
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/GameTest.PPM, (rect.getY() + rect.getHeight()/2)/GameTest.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 /GameTest.PPM, rect.getHeight()/2 /GameTest.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        // //Create object for pipe
        // for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
        //     Rectangle rect = ((RectangleMapObject) object).getRectangle();

        //     bdef.type = BodyDef.BodyType.StaticBody;
        //     bdef.position.set((rect.getX()+rect.getWidth()/2)/MarioBros.PPM, (rect.getY() + rect.getHeight()/2)/MarioBros.PPM);

        //     body = world.createBody(bdef);

        //     shape.setAsBox(rect.getWidth()/2 /MarioBros.PPM, rect.getHeight()/2 /MarioBros.PPM);
        //     fdef.shape = shape;
        //     body.createFixture(fdef);
        // }

        // //Create object for brick
        // for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
        //     Rectangle rect = ((RectangleMapObject) object).getRectangle();

        //     bdef.type = BodyDef.BodyType.StaticBody;
        //     bdef.position.set((rect.getX()+rect.getWidth()/2)/MarioBros.PPM, (rect.getY() + rect.getHeight()/2)/MarioBros.PPM);

        //     body = world.createBody(bdef);

        //     shape.setAsBox(rect.getWidth()/2 /MarioBros.PPM, rect.getHeight()/2 /MarioBros.PPM);
        //     fdef.shape = shape;
        //     body.createFixture(fdef);
        // }

        // //create object for coin
        // for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
        //     Rectangle rect = ((RectangleMapObject) object).getRectangle();

        //     bdef.type = BodyDef.BodyType.StaticBody;
        //     bdef.position.set((rect.getX()+rect.getWidth()/2)/MarioBros.PPM, (rect.getY() + rect.getHeight()/2)/MarioBros.PPM);

        //     body = world.createBody(bdef);

        //     shape.setAsBox(rect.getWidth()/2 /MarioBros.PPM, rect.getHeight()/2 /MarioBros.PPM);
        //     fdef.shape = shape;
        //     body.createFixture(fdef);
        // }
    }
}
