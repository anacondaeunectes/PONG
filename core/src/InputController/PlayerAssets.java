package InputController;

import com.badlogic.gdx.Gdx;

public class PlayerAssets {

    private int keyUp;
    private int keyDown;

    public PlayerAssets(int keyUp, int keyDown) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
    }

    public int getKeyUp() {
        return keyUp;
    }

    public void setKeyUp(int keyUp) {
        this.keyUp = keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public void setKeyDown(int keyDown) {
        this.keyDown = keyDown;
    }
}
