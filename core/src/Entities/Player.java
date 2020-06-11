package Entities;

import Entities.StickJugador;
import InputController.PlayerAssets;
import InputController.StickProccesor;
import InputController.VirtualController;

public class Player {

    private StickJugador stickJugador;

    private PlayerAssets playerAssets;

    private VirtualController virtualController;
    private StickProccesor stickProccesor;

    public Player(StickJugador stickJugador, PlayerAssets playerAssets) {
        this.stickJugador = stickJugador;
        this.virtualController = new VirtualController();
        this.stickProccesor = new StickProccesor(this);
        this.playerAssets = playerAssets;
    }

    public PlayerAssets getPlayerAssets() {
        return playerAssets;
    }

    public StickJugador getStickJugador() {
        return stickJugador;
    }

    public VirtualController getVirtualController() {
        return virtualController;
    }

    public StickProccesor getStickProccesor() {
        return stickProccesor;
    }

    public void remove(){
        this.stickJugador.detach();
        this.stickJugador.remove();
    }
}
