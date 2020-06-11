package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import Entities.Bola;
import Entities.Player;
import Entities.StickJugador;
import InputController.PlayerAssets;
import InputController.StickProccesor;
import InputController.VirtualController;

public class MainGameScreen extends Pantallas{

    private Stage stage;
    private World world;
    private Bola bola;
    private Entities.Player player1;
    private Entities.Player player2;

    private InputMultiplexer inputMultiplexer;

    private VirtualController virtualController;
    private StickProccesor stickProccesor;


    public MainGameScreen(MainGame mainGame) {
        super(mainGame);
        stage = new Stage(new FitViewport(640,360));
        world = new World(new Vector2(0, 0), true);
    }

    @Override
    public void show() {
        Texture bolaTexture = mainGame.getManager().get("bola_Test.png");
        Texture stickTexture = mainGame.getManager().get("stick.png");

        bola = new Bola(world, bolaTexture, new Vector2(7, 5));
        player1 = new Entities.Player(new StickJugador(world, stickTexture, new Vector2(1, 4)), new PlayerAssets(Input.Keys.W, Input.Keys.S));
        player2 = new Player(new StickJugador(world, stickTexture, new Vector2(13, 4)), new PlayerAssets(Input.Keys.UP, Input.Keys.DOWN));
        //stickJugador2 = new StickJugador(world, stickTexture, new Vector2(13, 4));

        stage.addActor(bola);
        stage.addActor(player1.getStickJugador());
        stage.addActor(player2.getStickJugador());

        this.inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(player1.getStickProccesor());
        inputMultiplexer.addProcessor(player2.getStickProccesor());
        Gdx.input.setInputProcessor(inputMultiplexer);

       /*
        this.virtualController = new VirtualController();
        this.stickProccesor = new StickProccesor(stickJugador2, virtualController);
        Gdx.input.setInputProcessor(inputMultiplexer);
       */
    }

    @Override
    public void hide() {
        bola.detach();
        bola.remove();
        player1.remove();
        player2.remove();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();

        //OJO - revisar la posicion en la queponer esta llamada, si antes de act, entre act y draw, como esta, etc.
        updateInputs();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }

    /**
     * This method checks if any attribute of the Virtual Controller has been modified and applies the game playability logic.
     */
    private void updateInputs(){
        if (player1.getVirtualController().moveDown){
            player1.getStickJugador().StickMoveDown();
        }
        if (player1.getVirtualController().moveUp){
            player1.getStickJugador().StickMoveUp();
        }
        if (player2.getVirtualController().moveDown){
            player2.getStickJugador().StickMoveDown();
        }
        if (player2.getVirtualController().moveUp){
            player2.getStickJugador().StickMoveUp();
        }
    }
}
