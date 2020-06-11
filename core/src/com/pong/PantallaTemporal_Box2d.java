package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Entities.Bola;
import Entities.StickJugador;

public class PantallaTemporal_Box2d extends Pantallas {

    private World world;
    private Stage stage;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private Bola bola;

    private StickJugador stickJugador;

    public PantallaTemporal_Box2d(MainGame mainGame) {
        super(mainGame);
        Texture bolaTexture = mainGame.getManager().get("bola_Test.png");
        Texture stickTexture = mainGame.getManager().get("stick.png");

        world = new World(new Vector2(0 ,0), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(16, 9);
        stage= new Stage(new FitViewport(640,360));

        bola = new Bola(world, bolaTexture, new Vector2(10, 7));
        stickJugador = new StickJugador(world, stickTexture, new Vector2(5, 5));
        stage.addActor(bola);
        stage.addActor(stickJugador);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }
}
