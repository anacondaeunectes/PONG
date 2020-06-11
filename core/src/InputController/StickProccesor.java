package InputController;

import com.badlogic.gdx.InputAdapter;
import Entities.Player;

/**
 * Processor to control the User Input. It just manage to change the state of the associated {@link VirtualController} variables.
 * How the input is managed is controlled in OJO - ¡¡¡¡ FILL !!!!!
 *
 * @author anacondaeunectes
 */
public class StickProccesor extends InputAdapter {

    private Player player;

    public StickProccesor(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        /*while(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            stickJugador.getStickBody().setTransform(stickJugador.getStickBody().getPosition().x, stickJugador.getStickBody().getPosition().y - 0.125f, (int) stickJugador.getRotation());
        }*/

        if (keycode == player.getPlayerAssets().getKeyDown()){
            player.getVirtualController().moveDown = true;
            return true;
        }else if (keycode == player.getPlayerAssets().getKeyUp()){
            player.getVirtualController().moveUp = true;
            return true;
        }else{
            return false;
        }

        /*switch (keycode) {

            case (player.getPlayerAssets().getKeyDown()):
                player.getVirtualController().moveDown = true;
                return true;

            case (player.getPlayerAssets().getKeyUp()):
                player.getVirtualController().moveUp = true;
                return true;

            default:
                return false;
        }*/
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == player.getPlayerAssets().getKeyDown()){
            player.getVirtualController().moveDown = false;
            return true;
        }else if (keycode == player.getPlayerAssets().getKeyUp()){
            player.getVirtualController().moveUp = false;
            return true;
        }else{
            return false;
        }

        /*switch (keycode) {

            case (player.getPlayerAssets().getKeyDown()):
                player.getVirtualController().moveDown = false;
                return true;

            case (Input.Keys.UP):
                player.getVirtualController().moveDown = false;
                return true;

            default:
                return false;
        }

         */
    }
}
