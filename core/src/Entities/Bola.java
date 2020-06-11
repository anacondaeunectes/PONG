package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.pong.Constants.PIXELS_IN_METER;

public class Bola extends Actor {

    private Texture skin;
    private World world;
    private Body bolaBody;
    private Fixture bolaFixture;
    private final float velocity = 5f;
    private float initialAngle = 45;
    private TextureRegion textureRegion;

    public Bola(World world, Texture skin, Vector2 posicion){
        this.skin = skin;
        this.world = world;
        
        //Its defined the Ball as a DinamicBody with an initialPosition. This definition let us
        // create the Body of the Ball.
        BodyDef bolaDef = new BodyDef();
        bolaDef.position.set(posicion);
        bolaDef.type = BodyDef.BodyType.DynamicBody;
        bolaBody = world.createBody(bolaDef);

        //Its defined a CircleShape which let us create the Fixture of the Body of our Ball. Once
        //the shape its assigned, it is disposed.
        CircleShape bolaShape = new CircleShape();
        bolaShape.setRadius(0.25f);
        bolaFixture = bolaBody.createFixture(bolaShape, 1);
        bolaShape.dispose();

        //Its defined the size of the Actor using PIXELS_IN_METER relation
        setSize(PIXELS_IN_METER/2, PIXELS_IN_METER/2);

        //Its assigned an initial Velocity (as a vector) randomly using {@link initialMovement()}.
        bolaBody.setLinearVelocity(initialMovement(initialAngle));

        //Just used to be able to rotate the ball (¿DO I WANT TO MAKE THE BALL ABLE TO ROTATE?¿DELETE?)
        this.textureRegion = new TextureRegion(skin);
    }

    @Override
    public void act(float delta) {
        //Just used to be able to rotate the ball (¿DO I WANT TO MAKE THE BALL ABLE TO ROTATE?¿DELETE?)
        setRotation(bolaBody.getAngle() * 180/(float)Math.PI);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((bolaBody.getPosition().x - 0.25f)* PIXELS_IN_METER,
                (bolaBody.getPosition().y - 0.25f) * PIXELS_IN_METER);
        //batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
        batch.draw(textureRegion, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation() - 90, false);
    }

    /**
     * Destroy both Fixture and Body of this Actor
     */
    public void detach() {
        bolaBody.destroyFixture(bolaFixture);
        world.destroyBody(bolaBody);
    }

    /**
     * Generates a random Vector which module keep up {@link velocity} variable
     * @param maxDegrees Maximum degrees of the initial movement
     * @return Vector2 which represents a Velocity vector.
     */
    private Vector2 initialMovement(float maxDegrees){

        /*float randomX = (float) (Math.random() * (velocity + velocity) - velocity);
        float randomY = (float) Math.sqrt(Math.pow(velocity, 2) - Math.pow(randomX, 2));
        if (Math.random() <= 0.5){
            randomY = -randomY;
        }
        return new Vector2(randomX, randomY);
         */
        //
        float randomDegrees =  MathUtils.random(maxDegrees);

        System.out.println(randomDegrees);

        float compX = (velocity * MathUtils.cosDeg(randomDegrees));
        if (Math.random() <= 0.5){
            compX = -compX;
        }

        float compY = (velocity * MathUtils.sinDeg(randomDegrees));
        if (Math.random() <= 0.5){
            compY = -compY;
        }

        System.out.println("compX: " + compX);
        System.out.println("compY: " + compY);
        System.out.println(compX * compX + compY * compY);

        return new Vector2(compX, compY);
    }

}
