package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.pong.Constants.PIXELS_IN_METER;

public class StickJugador extends Actor{

    private Texture texture;
    private TextureRegion textureRegion;
    private World world;
    private Body stickBody;
    private Fixture fixture;

    public StickJugador(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture, 0, 0, 32,128);

        BodyDef stickDef = new BodyDef();
        stickDef.type = BodyDef.BodyType.StaticBody;
        stickDef.position.set(position);
        stickBody = world.createBody(stickDef);

        PolygonShape stickShape = new PolygonShape();
        stickShape.setAsBox(0.25f, 1f);
        fixture = stickBody.createFixture(stickShape, 1);
        stickShape.dispose();

        //Width entre 2 ya que el X del setAsBox es 0.5 en realidad
        //Height por 2 ya que la Y del setAsBox es 2 en realidad
        //Parten desde el centro geometrico de la figura
        setSize(PIXELS_IN_METER/2, PIXELS_IN_METER * 2);
        System.out.println("nodno");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((stickBody.getPosition().x - 0.25f) * PIXELS_IN_METER,
                (stickBody.getPosition().y - 1f) * PIXELS_IN_METER);
        batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
    }


    public void detach() {
        stickBody.destroyFixture(fixture);
        world.destroyBody(stickBody);
    }

    /**
     * Makes the Stick controlled by this class moves down
     */
    public void StickMoveDown() {
        getStickBody().setTransform(getStickBody().getPosition().x, getStickBody().getPosition().y - 0.125f, (int) getRotation());
    }

    /**
     * Makes the Stick controlled by this class moves up
     */
    public void StickMoveUp() {
        getStickBody().setTransform(getStickBody().getPosition().x, getStickBody().getPosition().y + 0.125f, (int) getRotation());
    }

    /**
     * Getter of the Body of this Actor
     * @return Body
     */
    public Body getStickBody() {
        return stickBody;
    }
}
