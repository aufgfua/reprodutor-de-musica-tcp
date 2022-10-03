package br.ufrgs.inf.tcp.trabalho.controller;

import br.ufrgs.inf.tcp.trabalho.view.UIView;

import java.awt.event.ActionEvent;

public class PlayerUI extends UIView {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private PlayerController controller;

    public PlayerUI() {
        super(PlayerUI.WIDTH, PlayerUI.HEIGHT);

        this.controller = new PlayerController();

    }

    public PlayerController getController() {
        return controller;
    }

    public void setController(PlayerController controller) {
        this.controller = controller;
    }

    @Override
    protected void playButtonClick(ActionEvent ev) {
        controller.getTextReader().setBaseText(getMusicText());
        controller.run();
    }
}
