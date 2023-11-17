package ch.epfl.cs107.play.tuto1;

import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.tuto1.actor.SimpleGhost;
import ch.epfl.cs107.play.tuto1.area.maps.Ferme;
import ch.epfl.cs107.play.tuto1.area.maps.Village;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

public class Tuto1 extends AreaGame {

    private SimpleGhost player;

    private void createAreas() {
        addArea(new Ferme());
        addArea(new Village());
    }

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            this.setCurrentArea("zelda/Village", true);

            player = new SimpleGhost(new Vector(18, 7), "ghost.1");
            this.getCurrentArea().registerActor(player);

            this.getCurrentArea().setViewCandidate(player);

            return true;
        } else return false;
    }

    public void end() {
        TextGraphics endText = new TextGraphics("coucou", 0.5f, Color.BLUE);
        endText.draw(this.getWindow());
    }

    public void update(float deltaTime) {
        super.update(deltaTime);

        Keyboard keyboard = getWindow().getKeyboard();
        Button upKey = keyboard.get(Keyboard.UP);
        if (upKey.isDown()) {
            this.player.moveUp(deltaTime);
        }
        Button downKey = keyboard.get(Keyboard.DOWN);
        if (downKey.isDown()) {
            this.player.moveDown(deltaTime);
        }
        Button leftKey = keyboard.get(Keyboard.LEFT);
        if (leftKey.isDown()) {
            this.player.moveLeft(deltaTime);
        }
        Button rightKey = keyboard.get(Keyboard.RIGHT);
        if (rightKey.isDown()) {
            this.player.moveRight(deltaTime);
        }

        if (this.player.isWeak()) {
            this.switchArea();
        }
    }

    public void switchArea () {
        this.getCurrentArea().unregisterActor(this.player);

        if (this.getCurrentArea().getTitle().equals("zelda/Ferme")) {
            this.setCurrentArea("zelda/Village", false);
            this.getCurrentArea().registerActor(this.player);
            this.getCurrentArea().setViewCandidate(this.player);
            this.player.strengthen();
        } else if (this.getCurrentArea().getTitle().equals("zelda/Village")) {
            this.getCurrentArea().unregisterActor(this.player);
            this.setCurrentArea("zelda/Ferme", false);
            this.getCurrentArea().registerActor(this.player);
            this.getCurrentArea().setViewCandidate(this.player);
            this.player.strengthen();
        }
    }

    public String getTitle() {
        return "Tuto1";
    }

}
